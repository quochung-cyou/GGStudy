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
    private String id;

    @Column(name="template_type")
    private String templateType;

    @OneToMany()
    @JoinColumn(name="template_id")
    private List<Slide> slides;

    @OneToMany
    @JoinColumn(name="template_id")
    private List<Element> elements;
}
