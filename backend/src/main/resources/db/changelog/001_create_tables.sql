CREATE TABLE IF NOT EXISTS `projects` (
                                          id varchar(36) primary key,
    `title` varchar(255),
    create_by varchar(255),
    create_time timestamp DEFAULT CURRENT_TIMESTAMP,
    modify_by varchar(255),
    modify_time timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_seen_slide int DEFAULT 1
    );

CREATE TABLE IF NOT EXISTS `templates` (
                                           id varchar(36) primary key,
    template_type varchar(255)
    );

CREATE TABLE IF NOT EXISTS slides (
                                      id varchar(36) primary key,
    project_id varchar(36) NOT NULL REFERENCES `projects`(id),
    template_id varchar(36) NOT NULL REFERENCES `templates`(id),
    heading_title text
    );

CREATE TABLE IF NOT EXISTS elements(
                                       id varchar(36) primary key,
    slide_id varchar(36) REFERENCES `slides`(id),
    element_type varchar(255),
    heading_title text,
    content text,
    layer int DEFAULT 1,
    appear_order int DEFAULT 1,
    size_x float DEFAULT 0,
    size_y float DEFAULT 0,
    pos_x float DEFAULT 0,
    pos_y float DEFAULT 0,
    image_url text,
    duration int DEFAULT 0
    );

CREATE TABLE IF NOT EXISTS template_elements(
                                                id varchar(36) primary key,
    template_id varchar(36) NOT NULL REFERENCES `templates`(id),
    element_id varchar(36) NOT NULL REFERENCES `elements`(id)
    );

CREATE TABLE IF NOT EXISTS usernotes(
                                        id varchar(36) primary key,
    slide_id varchar(36) NOT NULL REFERENCES `slides`(id),
    element_id varchar(36) NOT NULL REFERENCES `elements`(id),
    content text,
    create_by varchar(255),
    create_time timestamp DEFAULT CURRENT_TIMESTAMP,
    modify_by varchar(255),
    modify_time timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );