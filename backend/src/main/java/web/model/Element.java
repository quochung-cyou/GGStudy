package web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "elements")
public class Element {
    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private String id;

    @Column(name = "slide_id")
    private String slideId;

    @Column(name = "template_id")
    private String templateId;

    @Column(name = "element_type")
    private String elementType;

    @Column(name = "heading_title")
    private String headingTitle;

    @Column(name = "topic_name", columnDefinition = "TEXT")
    private String topicName;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "layer")
    private int layer;

    @Column(name = "appear_order")
    private int appearOrder;

    @Column(name = "size_x")
    private float sizeX;

    @Column(name = "size_y")
    private float sizeY;

    @Column(name = "pos_x")
    private float posX;

    @Column(name = "pos_y")
    private float posY;

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String imageUrl;

    @Column(name = "duration")
    private int duration;
}