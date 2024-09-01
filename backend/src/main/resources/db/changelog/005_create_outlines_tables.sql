CREATE TABLE IF NOT EXISTS `outlines` (
    id varchar(36) primary key,
    project_id varchar(36) NOT NULL REFERENCES `projects`(id),
    title text,
    description text
);