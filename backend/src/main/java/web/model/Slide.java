package web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Setter
@Table(name="slides")
public class Slide {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="project_id")
    private UUID projectId;

    @Column(name="template_id")
    private UUID templateId;

    @Column(name="heading_title")
    private String headingTitle;

    @Column(name="topic_name")
    private String topicName;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name="slide_id")
    private List<Element> elements;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name="slide_id")
    private List<Usernote> usernotes;

    public Slide() {
        this.elements = new ArrayList<>();
        this.usernotes = new ArrayList<>();
    }
}
