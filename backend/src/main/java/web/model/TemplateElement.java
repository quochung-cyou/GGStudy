package web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name="template_elements")
public class TemplateElement {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="template_id")
    private UUID templateId;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="element_id")
    private Element element;
}
