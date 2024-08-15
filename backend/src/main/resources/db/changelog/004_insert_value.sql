SET FOREIGN_KEY_CHECKS=0;

INSERT INTO templates (id, template_type)
values
    ('text_only_template_1', 'TEXT_ONLY'),
    ('one_image_and_text_template_2', 'ONE_IMAGE_AND_TEXT'),
    ('one_image_and_text_template_3', 'ONE_IMAGE_AND_TEXT'),
    ('one_image_and_text_template_4', 'ONE_IMAGE_AND_TEXT'),
    ('one_image_and_text_template_5', 'ONE_IMAGE_AND_TEXT'),
    ('one_image_and_text_template_6', 'ONE_IMAGE_AND_TEXT'),
    ('two_images_and_text_template_7', 'TWO_IMAGES_AND_TEXT'),
    ('two_images_and_text_template_8', 'TWO_IMAGES_AND_TEXT');

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES
    ('text_only_element_1',NULL,'HEADING','TIÊU ĐỀ','',1,1,1200,600,360,240,'',0,
     'text_only_template_1','');

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES
    ('one_image_and_text_element_1','','TEXT','','CHỮ',1,1,1400,120,260,881,'',0,
     'one_image_and_text_template_2',''),
    ('one_image_and_text_element_2','','IMAGE','','',1,1,800,600,560,205,'image_url_1',0,
     'one_image_and_text_template_2',''),
    ('one_image_and_text_element_3', NULL, 'HEADING', 'TIÊU ĐỀ', null, 1, 1, 1000, 80, 460, 48.7, '', 0,
     'one_image_and_text_template_2', null);

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES
    ('one_image_and_text_element_4','','TEXT','','CHỮ',1,1,500,600,250,260,'',0,
     'one_image_and_text_template_3',''),
    ('one_image_and_text_element_5','','IMAGE','','',1,1,800,600,900,260,'image_url_1',0,
     'one_image_and_text_template_3',''),
    ('one_image_and_text_element_6', NULL, 'HEADING', 'TIÊU ĐỀ', null, 1, 1, 1000, 80, 460, 108, '', 0,
     'one_image_and_text_template_3', null);

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES
    ('one_image_and_text_element_7','','TEXT','','CHỮ',1,1,500,600,1200,260,'',0,
     'one_image_and_text_template_4',''),
    ('one_image_and_text_element_8','','IMAGE','','',1,1,800,600,250,260,'image_url_1',0,
     'one_image_and_text_template_4',''),
    ('one_image_and_text_element_9', NULL, 'HEADING', 'TIÊU ĐỀ', null, 1, 1, 1000, 80, 460, 108, '', 0,
     'one_image_and_text_template_4', null);

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES
    ('one_image_and_text_element_10','','TEXT','','CHỮ',1,1,650,750,155,222,'',0,
     'one_image_and_text_template_5',''),
    ('one_image_and_text_element_11','','IMAGE','','',1,1,1080,1080,960,0,'image_url_1',0,
     'one_image_and_text_template_5',''),
    ('one_image_and_text_element_12', NULL, 'HEADING', 'TIÊU ĐỀ', null, 0, 2, 800, 80, 80, 68, '', 0,
     'one_image_and_text_template_5', null);

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES
    ('one_image_and_text_element_13','','TEXT','','CHỮ',1,1,650,750,1115,222,'',0,
     'one_image_and_text_template_6',''),
    ('one_image_and_text_element_14','','IMAGE','','',1,1,1080,1080,-120,0,'image_url_1',0,
     'one_image_and_text_template_6',''),
    ('one_image_and_text_element_15', NULL, 'HEADING', 'TIÊU ĐỀ', null, 1, 1, 800, 80, 1040, 68,'', 0,
     'one_image_and_text_template_6', null);

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES
    ('two_images_and_text_element_1','','TEXT1','','Chữ ảnh 1',1,1,700,300,190,719.5,'',0,
     'two_images_and_text_template_7',''),
    ('two_images_and_text_element_2','','TEXT2','','Chữ ảnh 2',1,1,700,300,1030,719.5,'',0,
     'two_images_and_text_template_7',''),
    ('two_images_and_text_element_3','','IMAGE1','','',1,1,600,450,240,187.5,'image_url_1',0,
     'two_images_and_text_template_7',''),
    ('two_images_and_text_element_4','','IMAGE2','','',1,1,600,450,1080,187.5,'image_url_1',0,
     'two_images_and_text_template_7',''),
    ('two_images_and_text_element_5', NULL, 'HEADING', 'TIÊU ĐỀ', null, 1, 1, 1000, 80, 460, 52, '', 0,
     'two_images_and_text_template_7', null);

INSERT INTO elements (id, slide_id, element_type, heading_title, content, layer, appear_order, size_x, size_y, pos_x,
                      pos_y, image_url, duration, template_id, topic_name)
VALUES
    ('two_images_and_text_element_6','','TEXT1','','Chữ ảnh 1',1,1,850,325,272,170.5,'',0,
     'two_images_and_text_template_8',''),
    ('two_images_and_text_element_7','','TEXT2','','Chữ ảnh 2',1,1,850,325,800,620.5,'',0,
     'two_images_and_text_template_8',''),
    ('two_images_and_text_element_8','','IMAGE1','','ẢNH 1',1,1,550,450,1260,108,'',0,
     'two_images_and_text_template_8',''),
    ('two_images_and_text_element_9','','IMAGE2','','ẢNH 2',1,1,550,450,110,558,'',0,
     'two_images_and_text_template_8',''),
    ('two_images_and_text_element_10', NULL, 'HEADING', 'TIÊU ĐỀ', null, 1, 1, 1000, 80, 120, 48, 'image_url_1', 0,
     'two_images_and_text_template_8', null);

SET FOREIGN_KEY_CHECKS=1;