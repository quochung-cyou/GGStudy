use learning;
set foreign_key_checks=0;
truncate table elements;
truncate table templates;


INSERT INTO templates (id, template_type)
VALUES ('one_image_and_text_template_1', 'ONE_IMAGE_AND_TEXT'),
       ('two_images_and_text_template_2', 'TWO_IMAGES_AND_TEXT'),
       ('text_only_template_3', 'TEXT_ONLY');

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES ('oiatse_1', NULL, 'HEADING', 'Tiêu đề', null, 1, 1, 1139, 310, 108, 269.5, null, 0,
        'one_image_and_text_template_1', null),
       ('oiatse_2', NULL, 'IMAGE', null, null, 0, 2, 1053, 794, 742, 200, 'image_url_1', 0,
        'one_image_and_text_template_1', null),
       ('oiatse_3', NULL, 'TEXT', null, 'Chữ', 1, 3, 592, 175, 108, 584, null, 0,
        'one_image_and_text_template_1', null);
INSERT INTO field_styles (id, entity_id, entity_type,  property_name, property_value)
VALUES ('oiatse_1_style_1', 'oiatse_1', 'ELEMENT', 'fontFamily', 'https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap'),
       ('oiatse_1_style_2', 'oiatse_1', 'ELEMENT', 'fontSize', '101rem'),
         ('oiatse_1_style_3', 'oiatse_1', 'ELEMENT', 'fontWeight', '900'),
         ('oiatse_1_style_4', 'oiatse_1', 'ELEMENT', 'color', '#000000'),
         ('oiatse_1_style_6', 'oiatse_1', 'ELEMENT', 'textTransform', 'uppercase');
INSERT INTO field_styles (id, entity_id, entity_type,  property_name, property_value)
VALUES ('oiatse_3_style_1', 'oiatse_3', 'ELEMENT', 'fontFamily', 'https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap'),
       ('oiatse_3_style_2', 'oiatse_3', 'ELEMENT', 'fontSize', '17rem'),
         ('oiatse_3_style_3', 'oiatse_3', 'ELEMENT', 'fontWeight', '400'),
         ('oiatse_3_style_4', 'oiatse_3', 'ELEMENT', 'color', '#000000');

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES ('oiatse_4', NULL, 'IMAGE', null, null, 1, 1, 1058, 859, -98, 300, 'image_url_2', 0,
        'two_images_and_text_template_2', null),
       ('oiatse_5', NULL, 'HEADING', 'Tiêu đề', null, 1, 2, 729, 230, 1159, 300, null, 0,
        'two_images_and_text_template_2', null),
       ('oiatse_6', NULL, 'TEXT', null, 'Chữ', 1, 3, 729, 172, 1159, 652, null, 0,
        'two_images_and_text_template_2', null);

INSERT INTO field_styles (id, entity_id, entity_type,  property_name, property_value)
VALUES ('oiatse_5_style_1', 'oiatse_5', 'ELEMENT', 'fontFamily', 'https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap'),
       ('oiatse_5_style_2', 'oiatse_5', 'ELEMENT', 'fontSize', '74rem'),
         ('oiatse_5_style_3', 'oiatse_5', 'ELEMENT', 'fontWeight', '900'),
         ('oiatse_5_style_4', 'oiatse_5', 'ELEMENT', 'color', '#000000'),
         ('oiatse_5_style_6', 'oiatse_5', 'ELEMENT', 'textTransform', 'uppercase');

INSERT INTO field_styles (id, entity_id, entity_type,  property_name, property_value)
VALUES ('oiatse_6_style_1', 'oiatse_6', 'ELEMENT', 'fontFamily', 'https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap'),
       ('oiatse_6_style_2', 'oiatse_6', 'ELEMENT', 'fontSize', '19rem'),
         ('oiatse_6_style_3', 'oiatse_6', 'ELEMENT', 'fontWeight', '400'),
         ('oiatse_6_style_4', 'oiatse_6', 'ELEMENT', 'color', '#000000');

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES ('tiate_1', NULL, 'IMAGE', null, null, 1, 1, 468, 604, 819, 267, 'image_url_3', 0,
        'two_images_and_text_template_2', null),
       ('tiate_2', NULL, 'IMAGE', null, null, 2, 2, 468, 604, 1354, 267, 'image_url_4', 0,
        'two_images_and_text_template_2', null),
       ('tiate_3', NULL, 'HEADING', 'Tiêu đề', null, 1, 3, 624, 183, 108, 267, null, 0,
        'two_images_and_text_template_2', null),
       ('tiate_4', NULL, 'TEXT', null, 'Mô tả', 1, 4, 108, 601, 108, 601, null, 0,
        'two_images_and_text_template_2', null);

INSERT INTO field_styles (id, entity_id, entity_type,  property_name, property_value)
VALUES ('tiate_3_style_1', 'tiate_3', 'ELEMENT', 'fontFamily', 'https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap'),
       ('tiate_3_style_2', 'tiate_3', 'ELEMENT', 'fontSize', '59rem'),
         ('tiate_3_style_3', 'tiate_3', 'ELEMENT', 'fontWeight', '900'),
         ('tiate_3_style_4', 'tiate_3', 'ELEMENT', 'color', '#000000'),
         ('tiate_3_style_6', 'tiate_3', 'ELEMENT', 'textTransform', 'uppercase');

INSERT INTO field_styles (id, entity_id, entity_type,  property_name, property_value)
VALUES ('tiate_4_style_1', 'tiate_4', 'ELEMENT', 'fontFamily', 'https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap'),
       ('tiate_4_style_2', 'tiate_4', 'ELEMENT', 'fontSize', '20rem'),
         ('tiate_4_style_3', 'tiate_4', 'ELEMENT', 'fontWeight', '400'),
         ('tiate_4_style_4', 'tiate_4', 'ELEMENT', 'color', '#000000');

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES ('text_only_element_1', NULL, 'HEADING', 'Tiêu đề', null, 1, 1, 0, 0, 127, 308, null, 0,
        'text_only_template_3', null);

set foreign_key_checks=1;