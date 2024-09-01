package web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import web.common.dto.OutlineInputFormat;
import web.common.dto.ProjectDTO;
import web.common.dto.ProjectInputFormat;
import web.common.dto.SlideInputFormat;
import web.common.exception.NotFoundException;
import web.common.shared.ContentType;
import web.common.shared.SlideType;
import web.common.utils.PageableUtils;
import web.dao.repository.FieldStyleRepository;
import web.dao.repository.OutlineRepository;
import web.dao.repository.ProjectRepository;
import web.dao.repository.TemplateRepository;
import web.dao.spec.ProjectSpecification;
import web.mapper.ProjectMapper;
import web.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static web.common.shared.Constants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    public static final String OUTLINE_PROMPT_PATH = "classpath:data/outlineprompt.txt";
    public static final String CHAT_PROMPT_PATH = "classpath:data/chatprompt.txt";

    private final ProjectRepository projectRepository;
    private final FieldStyleRepository fieldStyleRepository;
    private final OutlineRepository outlineRepository;
    private final OutlineService outlineService;
    public final ExecutorService executorService = Executors.newFixedThreadPool(4);

    private final ProjectMapper projectMapper;

    private final ObjectMapper objectMapper;
    private final ResourceLoader loader;

    private final GeminiClient geminiClient;
    private final ImageService imageService;

    @Override
    public Page<ProjectDTO> findAll(int size, int page, String sortBy, String currentUser) {
        Pageable pageable = PageableUtils.createPageable(size, page, sortBy);
        Specification<Project> specification = ProjectSpecification.fromCreatedBy(currentUser);
        Page<Project> projectList = projectRepository.findAll(specification, pageable);
        return projectList.map(projectMapper::toDTO);
    }

    @Override
    public Project findById(String id) {
        var project = projectRepository.findById(id);
        if (project.isEmpty()) throw new NotFoundException("Project not found with the given ID.");
        var projectGet = project.get();
//        var slides = projectGet.getSlides();
//        for (Slide slide : slides) {
//            var elements = slide.getElements();
//            List<String> elementRootTemplateIds = elements.stream().map(Element::getRootElementTemplateId).collect(Collectors.toList());
//            List<FieldStyle> fieldStyles = fieldStyleRepository.findByEntityIdInAndEntityType(elementRootTemplateIds, "ELEMENT");
//            Map<String, List<FieldStyle>> mapFieldStyle = fieldStyles.stream().collect(Collectors.groupingBy(FieldStyle::getEntityId));
//            for (Element element : elements) {
//                List<FieldStyle> fieldStyle = mapFieldStyle.get(element.getRootElementTemplateId());
//                if (fieldStyle != null) {
//                    element.setFieldStyles(fieldStyle);
//                }
//            }
//        }
        return projectGet;
    }


    @Override
    public Project createProjectsFromOutlines(List<OutlineResponse> outlines) throws IOException {
        Project theProject = new Project();
        List<CompletableFuture<Outline>> futures = new ArrayList<>();
        for (OutlineResponse outlineResponse : outlines) {
            Mono<Outline> mono = Mono.fromCallable(() -> {
                try {
                    Outline outline = outlineService.createOutlineFromGemini(outlineResponse.getTitle(), outlineResponse.getDescription());
                    outline.setProject(theProject);
                    outline.setTitle(outlineResponse.getTitle());
                    outline.setDescription(outlineResponse.getDescription());
                    return outline;
                } catch (Exception e) {
                    log.error("Error searching image for element: {}", e.getMessage(), e);
                    return null;
                }
            }).subscribeOn(Schedulers.fromExecutor(executorService));
            futures.add(mono.toFuture());
        }
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        List<Outline> list = new ArrayList<>();
        CompletableFuture<List<Outline>> allImagesFuture = allOf.thenApply(v -> futures.stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        log.error("Error setting image for element: {}", e.getMessage(), e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
        allImagesFuture.thenAccept(list::addAll).join();
        theProject.getOutlines().addAll(list);
        projectRepository.save(theProject);
        log.info("outline saved successfully");
        return theProject;
    }

    @Override
    public List<OutlineResponse> createProjectOutlines(String topicName) throws IOException {
        String prompt = getPromptFromFile(OUTLINE_PROMPT_PATH);
        prompt = prompt.replace("{{topic_name}}", topicName);
        String response = geminiClient.getDataFromPrompt(prompt);
        var outlineInputFormat = objectMapper.readValue(formatString(response), OutlineInputFormat.class);
        return outlineInputFormat.getOutlines();
    }

    @Override
    public String getAnswerFromGemini(List<String> history, String question) throws IOException {
        String prompt = getPromptFromFile(CHAT_PROMPT_PATH);
        StringBuilder joinOfHistoryList = new StringBuilder();
        for(String chat : history){
            joinOfHistoryList.append(chat).append("\n");
        }
        prompt = prompt.replace("{{history}}", joinOfHistoryList.toString());
        prompt = prompt.replace("{{question}}", question);
        log.info(prompt);
        return geminiClient.getDataFromPrompt(prompt);
    }

    @NotNull
    private String getPromptFromFile(String path) throws IOException {
        Resource resource = loader.getResource(path);
        InputStream input = resource.getInputStream();
        byte[] buffer = FileCopyUtils.copyToByteArray(input);
        return new String(buffer, StandardCharsets.UTF_8);
    }

    private String formatString(String jsonString) throws JsonProcessingException {
        JsonNode rootNode = objectMapper.readTree(jsonString);
        JsonNode text = rootNode.path("candidates").get(0).path("content").path("parts").get(0);
        return text.path("text").asText();
    }


    @Override
    public Project save(Project theProject) {
        return projectRepository.save(theProject);
    }

    @Override
    public void deleteById(String id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) throw new NotFoundException("Project not found with the given ID.");
        projectRepository.delete(project.get());
    }

}


