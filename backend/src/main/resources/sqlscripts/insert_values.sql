INSERT INTO templates
values (1000,'TEXT_ONLY'), (2000, 'ONE_IMAGE_AND_TEXT'), (3000, 'TWO_IMAGES_AND_TEXT');

INSERT INTO elements(`id`,
`element_type`,
`heading_title`,
`content`,
`layer`,
`appear_order`,
`size_x`,
`size_y`,
`pos_x`,
`pos_y`,
`image_url`,
`duration`)
VALUES
('10000','TEXT','','',1,1,5,5,5,5,"example.com/text",3),
('20000','IMAGE','','',1,1,10,10,10,10,"example.com/image1",3),
('20001','IMAGE','','',1,1,10,10,10,10,"example.com/image2",3);

INSERT INTO template_elements
values
(100,1000,10000),
(200,2000,10000),
(201,2000,20001),
(300,3000,10000),
(301,3000,20000),
(302,3000,20001);