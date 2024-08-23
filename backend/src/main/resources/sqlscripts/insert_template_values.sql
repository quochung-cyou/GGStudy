use learning;
set foreign_key_checks = 0;
truncate table elements;
truncate table templates;
truncate table field_styles;
truncate table projects;


INSERT INTO templates (id, template_type)
VALUES ('one_image_and_text_template_1', 'ONE_IMAGE_AND_TEXT'),
       ('one_image_and_text_template_2', 'ONE_IMAGE_AND_TEXT'),
       ('two_images_and_text_template_2', 'TWO_IMAGES_AND_TEXT'),
       ('text_only_template_3', 'TEXT_ONLY');

# ONE_IMAGE_AND_TEXT 1
INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES ('oiatse_1', NULL, 'HEADING', 'Tiêu đề', null, 1, 1, 1139, 310, 108, 269.5, null, 0,
        'one_image_and_text_template_1', null),
       ('oiatse_2', NULL, 'IMAGE', null, null, 0, 2, 1053, 794, 742, 200, 'image_url_1', 0,
        'one_image_and_text_template_1', null),
       ('oiatse_3', NULL, 'TEXT', null, 'Chữ', 1, 3, 592, 175, 108, 584, null, 0,
        'one_image_and_text_template_1', null);
INSERT INTO field_styles (id, entity_id, entity_type, property_name, property_value)
VALUES ('oiatse_1_style_1', 'oiatse_1', 'ELEMENT', 'fontFamily', 'Poppins'),
       ('oiatse_1_style_2', 'oiatse_1', 'ELEMENT', 'fontSize', '101'),
       ('oiatse_1_style_3', 'oiatse_1', 'ELEMENT', 'fontWeight', '900'),
       ('oiatse_1_style_4', 'oiatse_1', 'ELEMENT', 'color', '#000000'),
       ('oiatse_1_style_6', 'oiatse_1', 'ELEMENT', 'textTransform', 'uppercase'),
       ('oiatse_1_style_7', 'oiatse_1', 'ELEMENT', 'letterSpacing', '95'),
       ('oiatse_1_style_8', 'oiatse_1', 'ELEMENT', 'lineHeight', '1.11');
INSERT INTO field_styles (id, entity_id, entity_type, property_name, property_value)
VALUES ('oiatse_3_style_1', 'oiatse_3', 'ELEMENT', 'fontFamily', '  Poppins'),
       ('oiatse_3_style_2', 'oiatse_3', 'ELEMENT', 'fontSize', '17'),
       ('oiatse_3_style_3', 'oiatse_3', 'ELEMENT', 'fontWeight', '400'),
       ('oiatse_3_style_4', 'oiatse_3', 'ELEMENT', 'color', '#000000'),
       ('oiatse_3_style_5', 'oiatse_3', 'ELEMENT', 'lineHeight', '1.4');

# ONE_IMAGE_AND_TEXT 2

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES ('oiatse_4', NULL, 'IMAGE', null, null, 1, 1, 1058, 859, -98, 300, 'image_url_2', 0,
        'one_image_and_text_template_2', null),
       ('oiatse_5', NULL, 'HEADING', 'Tiêu đề', null, 1, 2, 729, 230, 1159, 300, null, 0,
        'one_image_and_text_template_2', null),
       ('oiatse_6', NULL, 'TEXT', null, 'Chữ', 1, 3, 729, 172, 1159, 652, null, 0,
        'one_image_and_text_template_2', null);

INSERT INTO field_styles (id, entity_id, entity_type, property_name, property_value)
VALUES ('oiatse_5_style_1', 'oiatse_5', 'ELEMENT', 'fontFamily', 'Poppins'),
       ('oiatse_5_style_2', 'oiatse_5', 'ELEMENT', 'fontSize', '74'),
       ('oiatse_5_style_3', 'oiatse_5', 'ELEMENT', 'fontWeight', '900'),
       ('oiatse_5_style_4', 'oiatse_5', 'ELEMENT', 'color', '#000000'),
       ('oiatse_5_style_6', 'oiatse_5', 'ELEMENT', 'textTransform', 'uppercase'),
       ('oiatse_5_style_7', 'oiatse_5', 'ELEMENT', 'letterSpacing', '10'),
       ('oiatse_5_style_8', 'oiatse_5', 'ELEMENT', 'lineHeight', '1.11');

INSERT INTO field_styles (id, entity_id, entity_type, property_name, property_value)
VALUES ('oiatse_6_style_1', 'oiatse_6', 'ELEMENT', 'fontFamily', 'Poppins'),
       ('oiatse_6_style_2', 'oiatse_6', 'ELEMENT', 'fontSize', '19'),
       ('oiatse_6_style_3', 'oiatse_6', 'ELEMENT', 'fontWeight', '400'),
       ('oiatse_6_style_4', 'oiatse_6', 'ELEMENT', 'color', '#000000'),
       ('oiatse_6_style_5', 'oiatse_6', 'ELEMENT', 'lineHeight', '1.4');

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES ('tiate_1', NULL, 'IMAGE', null, null, 1, 1, 468, 604, 819, 267, 'image_url_3', 0,
        'two_images_and_text_template_2', null),
       ('tiate_2', NULL, 'IMAGE', null, null, 2, 2, 468, 604, 1354, 267, 'image_url_4', 0,
        'two_images_and_text_template_2', null),
       ('tiate_3', NULL, 'HEADING', 'Tiêu đề', null, 1, 3, 624, 183, 108, 267, null, 0,
        'two_images_and_text_template_2', null),
       ('tiate_4', NULL, 'TEXT', null, 'Mô tả', 1, 4, 624, 108, 108, 601, null, 0,
        'two_images_and_text_template_2', null);

INSERT INTO field_styles (id, entity_id, entity_type, property_name, property_value)
VALUES ('tiate_3_style_1', 'tiate_3', 'ELEMENT', 'fontFamily', 'Poppins'),
       ('tiate_3_style_2', 'tiate_3', 'ELEMENT', 'fontSize', '59'),
       ('tiate_3_style_3', 'tiate_3', 'ELEMENT', 'fontWeight', '900'),
       ('tiate_3_style_4', 'tiate_3', 'ELEMENT', 'color', '#000000'),
       ('tiate_3_style_6', 'tiate_3', 'ELEMENT', 'textTransform', 'uppercase'),
       ('tiate_3_style_7', 'tiate_3', 'ELEMENT', 'letterSpacing', '10'),
       ('tiate_3_style_8', 'tiate_3', 'ELEMENT', 'lineHeight', '1.11');

INSERT INTO field_styles (id, entity_id, entity_type, property_name, property_value)
VALUES ('tiate_4_style_1', 'tiate_4', 'ELEMENT', 'fontFamily', 'Poppins'),
       ('tiate_4_style_2', 'tiate_4', 'ELEMENT', 'fontSize', '20'),
       ('tiate_4_style_3', 'tiate_4', 'ELEMENT', 'fontWeight', '400'),
       ('tiate_4_style_4', 'tiate_4', 'ELEMENT', 'color', '#000000'),
       ('tiate_4_style_5', 'tiate_4', 'ELEMENT', 'lineHeight', '1.4');

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES ('text_only_element_1', NULL, 'HEADING', 'Tiêu đề', null, 1, 1, 1139, 310, 127, 308, null, 0,
        'text_only_template_3', null);

set foreign_key_checks = 1;