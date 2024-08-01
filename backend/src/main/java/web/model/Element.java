package web.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
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

    @Column(name = "element_type")
    private String elementType;

    @Column(name = "heading_title")
    private String headingTitle;

    @Column(name = "content")
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

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "duration")
    private int duration;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="element_id")
    private Usernote usernote;

    public Element() {
    }

    public Element(String elementType, String headingTitle, String content, int layer, int appearOrder, float sizeX, float sizeY, float posX,
                   float posY, String imageUrl, int duration) {
        this.elementType = elementType;
        this.headingTitle = headingTitle;
        this.content = content;
        this.layer = layer;
        this.appearOrder = appearOrder;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.posX = posX;
        this.posY = posY;
        this.imageUrl = imageUrl;
        this.duration = duration;
    }
}
