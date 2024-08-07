CREATE TABLE IF NOT EXISTS `projects` (
    id varchar(36) primary key,
    `title` varchar(255),
    create_by varchar(255),
    create_time timestamp DEFAULT CURRENT_TIMESTAMP,
    modify_by varchar(255),
    modify_time timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_seen_slide int DEFAULT 1
)