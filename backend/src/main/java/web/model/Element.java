package web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="elements")
public class Element {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="slide_id")
    private UUID slideId;

    @Column(name="template_id")
    private UUID templateId;

    @Column(name = "element_type")
    private String elementType;

    @Column(name = "heading_title")
    private String headingTitle;

    @Column(name = "topic_name")
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

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="id")
    private Usernote usernote;

    public Element() {
    }

    public Element(String elementType, int layer, int appearOrder, float sizeX, float sizeY, float posX,
                   float posY, int duration) {
        this.elementType = elementType;
        this.layer = layer;
        this.appearOrder = appearOrder;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.posX = posX;
        this.posY = posY;
        this.duration = duration;
    }
}
