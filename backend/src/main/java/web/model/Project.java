package web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Table(name="projects")
public class Project {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="title")
    private String title;

    @Column(name="create_by")
    @CreatedBy
    private String createBy;

    @Column(name="modify_by")
    @LastModifiedBy
    private String modifyBy;

    @Column(name="create_time")
    @CreatedDate
    private Timestamp createTime;

    @Column(name="modify_time")
    @LastModifiedDate
    private Timestamp modifyTime;

    @Column(name="last_seen_slide")
    private int lastSeenSlide;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="project_id")
    private List<Slide> slides;
    public Project() {
        this.slides = new ArrayList<>();
    }
    public Project(String title) {
        this.title = title;
        this.slides = new ArrayList<>();
    }
}
