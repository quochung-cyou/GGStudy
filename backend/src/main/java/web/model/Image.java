package web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "images")
public class Image {
   @Id
   @GeneratedValue
   @UuidGenerator(style = UuidGenerator.Style.TIME)
   private String id;

   @Column(name = "link", columnDefinition = "TEXT")
   private String link;

   public Image(){}

   public Image(String link){
      this.link = link;
   }

}
