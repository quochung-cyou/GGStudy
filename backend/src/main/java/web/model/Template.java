package web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="templates")
public class Template {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="template_type")
    private UUID templateType;

    @OneToMany()
    @JoinColumn(name="template_id")
    private List<TemplateElement> templateElements;
}
