SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS template_elements;

ALTER TABLE slides
    MODIFY COLUMN heading_title text,
    ADD COLUMN topic_name text;

ALTER TABLE elements
    ADD FOREIGN KEY (slide_id) REFERENCES slides(id),
    ADD COLUMN template_id varchar(36),
    ADD COLUMN topic_name text,
    ADD FOREIGN KEY (template_id) REFERENCES `templates`(id);

ALTER TABLE projects
    MODIFY COLUMN title text;

SET FOREIGN_KEY_CHECKS=1;
