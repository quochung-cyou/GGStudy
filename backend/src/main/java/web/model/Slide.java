package web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

//@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Setter
@Table(name = "slides")
public class Slide {
    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Project project;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Template template;

    @Column(name = "heading_title")
    private String headingTitle;

    @Column(name = "topic_name", columnDefinition = "TEXT")
    private String topicName;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "slide_id")
    private List<Element> elements;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "slide_id")
    private List<Usernote> usernotes;

    public Slide() {
        this.elements = new ArrayList<>();
        this.usernotes = new ArrayList<>();
    }
}
