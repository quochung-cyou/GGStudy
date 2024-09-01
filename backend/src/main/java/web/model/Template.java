package web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "templates")
public class Template {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "template_type")
    private String templateType;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "template_id")
    private List<Slide> slides;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "template_id")
    private List<Element> elements;
}
