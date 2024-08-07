DROP TABLE template_elements;

ALTER TABLE usernotes
DROP FOREIGN KEY usernotes_ibfk_1,
DROP FOREIGN KEY usernotes_ibfk_2,
MODIFY COLUMN id varchar(255),
MODIFY COLUMN slide_id varchar(255),
MODIFY COLUMN element_id varchar(255);

ALTER TABLE elements
DROP FOREIGN KEY elements_ibfk_1,
MODIFY COLUMN id varchar(255),
MODIFY COLUMN slide_id varchar(255);

ALTER TABLE slides
DROP FOREIGN KEY slides_ibfk_1,
DROP FOREIGN KEY slides_ibfk_2,
MODIFY COLUMN id varchar(255),
MODIFY COLUMN project_id varchar(255) NOT NULL,
MODIFY COLUMN template_id varchar(255) NOT NULL,
MODIFY COLUMN heading_title varchar(255),
ADD COLUMN topic_name varchar(255),
ADD FOREIGN KEY (project_id) REFERENCES projects(id),
ADD FOREIGN KEY (template_id) REFERENCES templates(id);

ALTER TABLE usernotes
ADD FOREIGN KEY (slide_id) REFERENCES slides(id),
ADD FOREIGN KEY (element_id) REFERENCES elements(id);

ALTER TABLE elements
ADD FOREIGN KEY (slide_id) REFERENCES slides(id);

ALTER TABLE projects
MODIFY COLUMN id varchar(255);
=======
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
