SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS template_elements;

ALTER TABLE usernotes
    MODIFY COLUMN id varchar(255),
    MODIFY COLUMN slide_id varchar(255),
    MODIFY COLUMN element_id varchar(255);

ALTER TABLE elements
    MODIFY COLUMN id varchar(255),
    MODIFY COLUMN slide_id varchar(255);

ALTER TABLE slides
    MODIFY COLUMN id varchar(255),
    MODIFY COLUMN project_id varchar(255),
    MODIFY COLUMN template_id varchar(255),
    MODIFY COLUMN heading_title text,
    ADD COLUMN topic_name text;

ALTER TABLE templates
    MODIFY COLUMN id varchar(255);

ALTER TABLE elements
    ADD FOREIGN KEY (slide_id) REFERENCES slides(id);

ALTER TABLE projects
    MODIFY COLUMN id varchar(255),
    MODIFY COLUMN title text;

ALTER TABLE IMAGE
    MODIFY COLUMN id varchar(255);

SET FOREIGN_KEY_CHECKS=1;
