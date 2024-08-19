package web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "field_styles")
@Getter
@Setter
public class FieldStyle {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "entity_id")
    private String entityId;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "property_name")
    private String propertyName;

    @Column(name = "property_value")
    private String propertyValue;
}