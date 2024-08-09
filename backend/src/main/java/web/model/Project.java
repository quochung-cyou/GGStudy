package web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @JsonView(View.SummaryProject.class)
    private String id;

    @Column(name = "title")
    @JsonView(View.SummaryProject.class)
    private String title;

    @Column(name = "create_by")
    @CreatedBy
    @JsonView(View.SummaryProject.class)
    private String createBy;

    @Column(name = "modify_by")
    @LastModifiedBy
    @JsonView(View.SummaryProject.class)
    private String modifyBy;

    @Column(name = "create_time")
    @CreatedDate
    @JsonView(View.SummaryProject.class)
    private Timestamp createTime;

    @Column(name = "modify_time")
    @LastModifiedDate
    @JsonView(View.SummaryProject.class)
    private Timestamp modifyTime;

    @Column(name = "last_seen_slide")
    @JsonView(View.SummaryProject.class)
    private int lastSeenSlide;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @JsonView(View.FullProject.class)
    private List<Slide> slides;

    public Project() {
        this.slides = new ArrayList<>();
    }
}

