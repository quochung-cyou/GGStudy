SET FOREIGN_KEY_CHECKS=0;

INSERT INTO templates (id, template_type)
values
    ('text_only_template_2108_1', 'TEXT_ONLY'),
    ('one_image_text_template_2108_2', 'ONE_IMAGE_AND_TEXT'),
    ('one_image_text_template_2108_3', 'ONE_IMAGE_AND_TEXT'),
    ('one_image_text_template_2108_4', 'ONE_IMAGE_AND_TEXT'),
    ('one_image_text_template_2108_5', 'ONE_IMAGE_AND_TEXT'),
    ('one_image_text_template_2108_6', 'ONE_IMAGE_AND_TEXT'),
    ('two_images_two_texts_template_2108_7', 'TWO_IMAGES_AND_TWO_TEXTS'),
    ('two_images_two_texts_template_2108_8', 'TWO_IMAGES_AND_TWO_TEXTS');

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES
    ('text_only_element_2108_1',NULL,'HEADING','TIÊU ĐỀ','',1,1,1200,600,360,240,'',0,
     'text_only_template_2108_1','');

INSERT INTO field_styles (id, entity_id, entity_type, property_name, property_value)
VALUES ('otse_2108_style_1', 'text_only_element_2108_1', 'ELEMENT', 'fontFamily', 'Poppins'),
       ('otse_2108_style_2', 'text_only_element_2108_1', 'ELEMENT', 'fontSize', '101'),
       ('otse_2108_style_3', 'text_only_element_2108_1', 'ELEMENT', 'fontWeight', '900'),
       ('otse_2108_style_4', 'text_only_element_2108_1', 'ELEMENT', 'color', '#000000'),
       ('otse_2108_style_6', 'text_only_element_2108_1', 'ELEMENT', 'textTransform', 'uppercase'),
       ('otse_2108_style_7', 'text_only_element_2108_1', 'ELEMENT', 'letterSpacing', '95'),
       ('otse_2108_style_8', 'text_only_element_2108_1', 'ELEMENT', 'lineHeight', '1.11');

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES
    ('one_image_text_element_2108_1','','TEXT','','CHỮ',1,1,1400,120,260,881,'',0,
     'one_image_text_template_2108_2',''),
    ('one_image_text_element_2108_2','','IMAGE','','',1,1,800,600,560,205,'image_url_1',0,
     'one_image_text_template_2108_2',''),
    ('one_image_text_element_2108_3', NULL, 'HEADING', 'TIÊU ĐỀ', null, 1, 1, 1000, 80, 460, 48.7, '', 0,
     'one_image_text_template_2108_2', null);

INSERT INTO field_styles (id, entity_id, entity_type, property_name, property_value)
VALUES ('oiatse_2108_3_style_1', 'one_image_text_element_2108_3', 'ELEMENT', 'fontFamily', 'Poppins'),
       ('oiatse_2108_3_style_2', 'one_image_text_element_2108_3', 'ELEMENT', 'fontSize', '60'),
       ('oiatse_2108_3_style_3', 'one_image_text_element_2108_3', 'ELEMENT', 'fontWeight', '700'),
       ('oiatse_2108_3_style_4', 'one_image_text_element_2108_3', 'ELEMENT', 'color', '#000000'),
       ('oiatse_2108_3_style_6', 'one_image_text_element_2108_3', 'ELEMENT', 'textTransform', 'uppercase'),
       ('oiatse_2108_3_style_7', 'one_image_text_element_2108_3', 'ELEMENT', 'letterSpacing', '70'),
       ('oiatse_2108_3_style_8', 'one_image_text_element_2108_3', 'ELEMENT', 'lineHeight', '1.11');
INSERT INTO field_styles (id, entity_id, entity_type, property_name, property_value)
VALUES ('oiatse_2108_1_style_1', 'one_image_text_element_2108_1', 'ELEMENT', 'fontFamily', '  Poppins'),
       ('oiatse_2108_1_style_2', 'one_image_text_element_2108_1', 'ELEMENT', 'fontSize', '17'),
       ('oiatse_2108_1_style_3', 'one_image_text_element_2108_1', 'ELEMENT', 'fontWeight', '400'),
       ('oiatse_2108_1_style_4', 'one_image_text_element_2108_1', 'ELEMENT', 'color', '#000000'),
       ('oiatse_2108_1_style_5', 'one_image_text_element_2108_1', 'ELEMENT', 'lineHeight', '1.4');

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES
    ('one_image_text_element_2108_4','','TEXT','','CHỮ',1,1,500,600,250,260,'',0,
     'one_image_text_template_2108_3',''),
    ('one_image_text_element_2108_5','','IMAGE','','',1,1,800,600,900,260,'image_url_1',0,
     'one_image_text_template_2108_3',''),
    ('one_image_text_element_2108_6', NULL, 'HEADING', 'TIÊU ĐỀ', null, 1, 1, 1000, 80, 460, 108, '', 0,
     'one_image_text_template_2108_3', null);

INSERT INTO field_styles (id, entity_id, entity_type, property_name, property_value)
VALUES ('oiatse_2108_6_style_1', 'one_image_text_element_2108_6', 'ELEMENT', 'fontFamily', 'Poppins'),
       ('oiatse_2108_6_style_2', 'one_image_text_element_2108_6', 'ELEMENT', 'fontSize', '60'),
       ('oiatse_2108_6_style_3', 'one_image_text_element_2108_6', 'ELEMENT', 'fontWeight', '700'),
       ('oiatse_2108_6_style_4', 'one_image_text_element_2108_6', 'ELEMENT', 'color', '#000000'),
       ('oiatse_2108_6_style_6', 'one_image_text_element_2108_6', 'ELEMENT', 'textTransform', 'uppercase'),
       ('oiatse_2108_6_style_7', 'one_image_text_element_2108_6', 'ELEMENT', 'letterSpacing', '70'),
       ('oiatse_2108_6_style_8', 'one_image_text_element_2108_6', 'ELEMENT', 'lineHeight', '1.11');
INSERT INTO field_styles (id, entity_id, entity_type, property_name, property_value)
VALUES ('oiatse_2108_4_style_1', 'one_image_text_element_2108_4', 'ELEMENT', 'fontFamily', '  Poppins'),
       ('oiatse_2108_4_style_2', 'one_image_text_element_2108_4', 'ELEMENT', 'fontSize', '17'),
       ('oiatse_2108_4_style_3', 'one_image_text_element_2108_4', 'ELEMENT', 'fontWeight', '400'),
       ('oiatse_2108_4_style_4', 'one_image_text_element_2108_4', 'ELEMENT', 'color', '#000000'),
       ('oiatse_2108_4_style_5', 'one_image_text_element_2108_4', 'ELEMENT', 'lineHeight', '1.4');

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES
    ('one_image_text_element_2108_7','','TEXT','','CHỮ',1,1,500,600,1200,260,'',0,
     'one_image_text_template_2108_4',''),
    ('one_image_text_element_2108_8','','IMAGE','','',1,1,800,600,250,260,'image_url_1',0,
     'one_image_text_template_2108_4',''),
    ('one_image_text_element_2108_9', NULL, 'HEADING', 'TIÊU ĐỀ', null, 1, 1, 1000, 80, 460, 108, '', 0,
     'one_image_text_template_2108_4', null);

INSERT INTO field_styles (id, entity_id, entity_type, property_name, property_value)
VALUES ('oiatse_2108_9_style_1', 'one_image_text_element_2108_9', 'ELEMENT', 'fontFamily', 'Poppins'),
       ('oiatse_2108_9_style_2', 'one_image_text_element_2108_9', 'ELEMENT', 'fontSize', '60'),
       ('oiatse_2108_9_style_3', 'one_image_text_element_2108_9', 'ELEMENT', 'fontWeight', '700'),
       ('oiatse_2108_9_style_4', 'one_image_text_element_2108_9', 'ELEMENT', 'color', '#000000'),
       ('oiatse_2108_9_style_6', 'one_image_text_element_2108_9', 'ELEMENT', 'textTransform', 'uppercase'),
       ('oiatse_2108_9_style_7', 'one_image_text_element_2108_9', 'ELEMENT', 'letterSpacing', '70'),
       ('oiatse_2108_9_style_8', 'one_image_text_element_2108_9', 'ELEMENT', 'lineHeight', '1.11');
INSERT INTO field_styles (id, entity_id, entity_type, property_name, property_value)
VALUES ('oiatse_2108_7_style_1', 'one_image_text_element_2108_7', 'ELEMENT', 'fontFamily', '  Poppins'),
       ('oiatse_2108_7_style_2', 'one_image_text_element_2108_7', 'ELEMENT', 'fontSize', '17'),
       ('oiatse_2108_7_style_3', 'one_image_text_element_2108_7', 'ELEMENT', 'fontWeight', '400'),
       ('oiatse_2108_7_style_4', 'one_image_text_element_2108_7', 'ELEMENT', 'color', '#000000'),
       ('oiatse_2108_7_style_5', 'one_image_text_element_2108_7', 'ELEMENT', 'lineHeight', '1.4');


INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES
    ('one_image_text_element_2108_10','','TEXT','','CHỮ',1,1,650,750,155,222,'',0,
     'one_image_text_template_2108_5',''),
    ('one_image_text_element_2108_11','','IMAGE','','',1,1,1080,1080,960,0,'image_url_1',0,
     'one_image_text_template_2108_5',''),
    ('one_image_text_element_2108_12', NULL, 'HEADING', 'TIÊU ĐỀ', null, 0, 2, 800, 80, 80, 68, '', 0,
     'one_image_text_template_2108_5', null);

INSERT INTO field_styles (id, entity_id, entity_type, property_name, property_value)
VALUES ('oiatse_2108_12_style_1', 'one_image_text_element_2108_12', 'ELEMENT', 'fontFamily', 'Poppins'),
       ('oiatse_2108_12_style_2', 'one_image_text_element_2108_12', 'ELEMENT', 'fontSize', '60'),
       ('oiatse_2108_12_style_3', 'one_image_text_element_2108_12', 'ELEMENT', 'fontWeight', '700'),
       ('oiatse_2108_12_style_4', 'one_image_text_element_2108_12', 'ELEMENT', 'color', '#000000'),
       ('oiatse_2108_12_style_6', 'one_image_text_element_2108_12', 'ELEMENT', 'textTransform', 'uppercase'),
       ('oiatse_2108_12_style_7', 'one_image_text_element_2108_12', 'ELEMENT', 'letterSpacing', '70'),
       ('oiatse_2108_12_style_8', 'one_image_text_element_2108_12', 'ELEMENT', 'lineHeight', '1.11');
INSERT INTO field_styles (id, entity_id, entity_type, property_name, property_value)
VALUES ('oiatse_2108_10_style_1', 'one_image_text_element_2108_10', 'ELEMENT', 'fontFamily', '  Poppins'),
       ('oiatse_2108_10_style_2', 'one_image_text_element_2108_10', 'ELEMENT', 'fontSize', '17'),
       ('oiatse_2108_10_style_3', 'one_image_text_element_2108_10', 'ELEMENT', 'fontWeight', '400'),
       ('oiatse_2108_10_style_4', 'one_image_text_element_2108_10', 'ELEMENT', 'color', '#000000'),
       ('oiatse_2108_10_style_5', 'one_image_text_element_2108_10', 'ELEMENT', 'lineHeight', '1.4');


INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES
    ('one_image_text_element_2108_13','','TEXT','','CHỮ',1,1,650,750,1115,222,'',0,
     'one_image_text_template_2108_6',''),
    ('one_image_text_element_2108_14','','IMAGE','','',1,1,1080,1080,-120,0,'image_url_1',0,
     'one_image_text_template_2108_6',''),
    ('one_image_text_element_2108_15', NULL, 'HEADING', 'TIÊU ĐỀ', null, 1, 1, 800, 80, 1040, 68,'', 0,
     'one_image_text_template_2108_6', null);

INSERT INTO field_styles (id, entity_id, entity_type, property_name, property_value)
VALUES ('oiatse_2108_15_style_1', 'one_image_text_element_2108_15', 'ELEMENT', 'fontFamily', 'Poppins'),
       ('oiatse_2108_15_style_2', 'one_image_text_element_2108_15', 'ELEMENT', 'fontSize', '60'),
       ('oiatse_2108_15_style_3', 'one_image_text_element_2108_15', 'ELEMENT', 'fontWeight', '700'),
       ('oiatse_2108_15_style_4', 'one_image_text_element_2108_15', 'ELEMENT', 'color', '#000000'),
       ('oiatse_2108_15_style_6', 'one_image_text_element_2108_15', 'ELEMENT', 'textTransform', 'uppercase'),
       ('oiatse_2108_15_style_7', 'one_image_text_element_2108_15', 'ELEMENT', 'letterSpacing', '70'),
       ('oiatse_2108_15_style_8', 'one_image_text_element_2108_15', 'ELEMENT', 'lineHeight', '1.11');
INSERT INTO field_styles (id, entity_id, entity_type, property_name, property_value)
VALUES ('oiatse_2108_13_style_1', 'one_image_text_element_2108_13', 'ELEMENT', 'fontFamily', '  Poppins'),
       ('oiatse_2108_13_style_2', 'one_image_text_element_2108_13', 'ELEMENT', 'fontSize', '17'),
       ('oiatse_2108_13_style_3', 'one_image_text_element_2108_13', 'ELEMENT', 'fontWeight', '400'),
       ('oiatse_2108_13_style_4', 'one_image_text_element_2108_13', 'ELEMENT', 'color', '#000000'),
       ('oiatse_2108_13_style_5', 'one_image_text_element_2108_13', 'ELEMENT', 'lineHeight', '1.4');


INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES
    ('two_images_two_texts_element_2108_1','','TEXT1','','Chữ ảnh 1',1,1,700,300,190,719.5,'',0,
     'two_images_two_texts_template_2108_7',''),
    ('two_images_two_texts_element_2108_2','','TEXT2','','Chữ ảnh 2',1,1,700,300,1030,719.5,'',0,
     'two_images_two_texts_template_2108_7',''),
    ('two_images_two_texts_element_2108_3','','IMAGE1','','',1,1,600,450,240,187.5,'image_url_1',0,
     'two_images_two_texts_template_2108_7',''),
    ('two_images_two_texts_element_2108_4','','IMAGE2','','',1,1,600,450,1080,187.5,'image_url_1',0,
     'two_images_two_texts_template_2108_7',''),
    ('two_images_two_texts_element_2108_5', NULL, 'HEADING', 'TIÊU ĐỀ', null, 1, 1, 1000, 80, 460, 52, '', 0,
     'two_images_two_texts_template_2108_7', null);

INSERT INTO field_styles (id, entity_id, entity_type, property_name, property_value)
VALUES ('tiattse_2108_1_style_1', 'two_images_two_texts_element_2108_1', 'ELEMENT', 'fontFamily', '  Poppins'),
       ('tiattse_2108_1_style_2', 'two_images_two_texts_element_2108_1', 'ELEMENT', 'fontSize', '17'),
       ('tiattse_2108_1_style_3', 'two_images_two_texts_element_2108_1', 'ELEMENT', 'fontWeight', '400'),
       ('tiattse_2108_1_style_4', 'two_images_two_texts_element_2108_1', 'ELEMENT', 'color', '#000000'),
       ('tiattse_2108_1_style_5', 'two_images_two_texts_element_2108_1', 'ELEMENT', 'lineHeight', '1.4');
INSERT INTO field_styles (id, entity_id, entity_type, property_name, property_value)
VALUES ('tiattse_2108_2_style_1', 'two_images_two_texts_element_2108_2', 'ELEMENT', 'fontFamily', '  Poppins'),
       ('tiattse_2108_2_style_2', 'two_images_two_texts_element_2108_2', 'ELEMENT', 'fontSize', '17'),
       ('tiattse_2108_2_style_3', 'two_images_two_texts_element_2108_2', 'ELEMENT', 'fontWeight', '400'),
       ('tiattse_2108_2_style_4', 'two_images_two_texts_element_2108_2', 'ELEMENT', 'color', '#000000'),
       ('tiattse_2108_2_style_5', 'two_images_two_texts_element_2108_2', 'ELEMENT', 'lineHeight', '1.4');

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES
    ('two_images_two_texts_element_2108_6','','TEXT1','','Chữ ảnh 1',1,1,850,325,272,170.5,'',0,
     'two_images_two_texts_template_2108_8',''),
    ('two_images_two_texts_element_2108_7','','TEXT2','','Chữ ảnh 2',1,1,850,325,800,620.5,'',0,
     'two_images_two_texts_template_2108_8',''),
    ('two_images_two_texts_element_2108_8','','IMAGE1','','ẢNH 1',1,1,550,450,1260,108,'',0,
     'two_images_two_texts_template_2108_8',''),
    ('two_images_two_texts_element_2108_9','','IMAGE2','','ẢNH 2',1,1,550,450,110,558,'',0,
     'two_images_two_texts_template_2108_8',''),
    ('two_images_two_texts_element_2108_10', NULL, 'HEADING', 'TIÊU ĐỀ', null, 1, 1, 1000, 80, 120, 48, 'image_url_1', 0,
     'two_images_two_texts_template_2108_8', null);

INSERT INTO field_styles (id, entity_id, entity_type, property_name, property_value)
VALUES ('tiattse_2108_6_style_1', 'two_images_two_texts_element_2108_6', 'ELEMENT', 'fontFamily', '  Poppins'),
       ('tiattse_2108_6_style_2', 'two_images_two_texts_element_2108_6', 'ELEMENT', 'fontSize', '17'),
       ('tiattse_2108_6_style_3', 'two_images_two_texts_element_2108_6', 'ELEMENT', 'fontWeight', '400'),
       ('tiattse_2108_6_style_4', 'two_images_two_texts_element_2108_6', 'ELEMENT', 'color', '#000000'),
       ('tiattse_2108_6_style_5', 'two_images_two_texts_element_2108_6', 'ELEMENT', 'lineHeight', '1.4');
INSERT INTO field_styles (id, entity_id, entity_type, property_name, property_value)
VALUES ('tiattse_2108_7_style_1', 'two_images_two_texts_element_2108_7', 'ELEMENT', 'fontFamily', '  Poppins'),
       ('tiattse_2108_7_style_2', 'two_images_two_texts_element_2108_7', 'ELEMENT', 'fontSize', '17'),
       ('tiattse_2108_7_style_3', 'two_images_two_texts_element_2108_7', 'ELEMENT', 'fontWeight', '400'),
       ('tiattse_2108_7_style_4', 'two_images_two_texts_element_2108_7', 'ELEMENT', 'color', '#000000'),
       ('tiattse_2108_7_style_5', 'two_images_two_texts_element_2108_7', 'ELEMENT', 'lineHeight', '1.4');


SET FOREIGN_KEY_CHECKS=1;