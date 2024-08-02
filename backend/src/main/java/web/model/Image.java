package web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "images")
public class Image {
   public Image(String link){
      this.link = link;
   }

   public Image(){
   }

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.UUID)
   private String id;

   @Column(name = "link")
   private String link;
}
