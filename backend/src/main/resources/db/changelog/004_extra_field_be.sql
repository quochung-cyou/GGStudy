CREATE TABLE IF NOT EXISTS `field_styles`
(
    id varchar(36) primary key,
    entity_id varchar(36) NOT NULL,
    entity_type varchar(255) NOT NULL,
    property_name varchar(255) NOT NULL,
    property_value varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

alter table elements
    add column root_element_template_id varchar(36) null;