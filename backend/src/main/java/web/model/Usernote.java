package web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name="usernotes")
public class Usernote {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name="slide_id")
    private UUID slideId;
    @Column(name="element_id")
    private UUID elementId;
    @Column(name="create_by")
    private String createBy;
    @Column(name="modify_by")
    private String modifyBy;
    @Column(name="create_time")
    private Timestamp createTime;
    @Column(name="modify_time")
    private Timestamp modifyTime;
    @Column(name="content")
    private String content;
}
