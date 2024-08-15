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
VALUES ('one_image_and_text_element_1', NULL, 'HEADING', 'Tiêu đề', null, 1, 1, 0, 0, 108, 269.5, null, 0,
        'one_image_and_text_template_1', null),
       ('one_image_and_text_element_2', NULL, 'IMAGE', null, null, 0, 2, 1053, 794, 742, 200, 'image_url_1', 0,
        'one_image_and_text_template_1', null),
       ('one_image_and_text_element_3', NULL, 'TEXT', null, 'Chữ', 1, 3, 0, 0, 108, 584, null, 0,
        'one_image_and_text_template_1', null);

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES ('one_image_and_text_element_4', NULL, 'IMAGE', null, null, 1, 1, 1058, 859, -98, 300, 'image_url_2', 0,
        'two_images_and_text_template_2', null),
       ('one_image_and_text_element_5', NULL, 'HEADING', 'Tiêu đề', null, 1, 2, 0, 0, 1159, 300, null, 0,
        'two_images_and_text_template_2', null),
       ('one_image_and_text_element_6', NULL, 'TEXT', null, 'Chữ', 1, 3, 0, 0, 1159, 652, null, 0,
        'two_images_and_text_template_2', null);

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES ('two_images_and_text_element_1', NULL, 'IMAGE', null, null, 1, 1, 468, 604, 819, 267, 'image_url_3', 0,
        'two_images_and_text_template_2', null),
       ('two_images_and_text_element_2', NULL, 'IMAGE', null, null, 2, 2, 468, 604, 1354, 267, 'image_url_4', 0,
        'two_images_and_text_template_2', null),
       ('two_images_and_text_element_3', NULL, 'HEADING', 'Tiêu đề', null, 1, 3, 0, 0, 108, 267, null, 0,
        'two_images_and_text_template_2', null),
       ('two_images_and_text_element_4', NULL, 'TEXT', null, 'Mô tả', 1, 4, 0, 0, 108, 601, null, 0,
        'two_images_and_text_template_2', null);

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES ('text_only_element_1', NULL, 'HEADING', 'Tiêu đề', null, 1, 1, 0, 0, 127, 308, null, 0,
        'text_only_template_3', null);

set foreign_key_checks=1;