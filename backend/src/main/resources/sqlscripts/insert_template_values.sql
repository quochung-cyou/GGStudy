INSERT INTO templates
values
(UUID_TO_BIN('6ba5525c-3e51-45a9-9aca-26876e734dc7'),'TEXT_ONLY'),
(UUID_TO_BIN('6aad922f-74ba-4794-97cc-716ac6dec522'), 'ONE_IMAGE_AND_TEXT'),
(UUID_TO_BIN('4c1f9f4f-db9a-47c9-86dc-d24784914913'), 'TWO_IMAGES_AND_TEXT');


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
`duration`,
`template_id`)
values
(UUID_TO_BIN('5833d20a-dfa8-4998-8982-31ccde93995b'),'TEXT','','',1,1,5,5,5,5,"example.com/text",3,UUID_TO_BIN('6ba5525c-3e51-45a9-9aca-26876e734dc7')),
(UUID_TO_BIN('e6b29c36-fa77-4769-8796-a3dc67f954ff'),'TEXT','','',1,1,5,5,5,5,"example.com/text",3,UUID_TO_BIN('6aad922f-74ba-4794-97cc-716ac6dec522')),
(UUID_TO_BIN('8b5d978e-f0ba-4e09-a6ff-f9cac2ec39bb'),'IMAGE','','',1,1,10,10,10,10,"example.com/image1",3,UUID_TO_BIN('6aad922f-74ba-4794-97cc-716ac6dec522')),
(UUID_TO_BIN('e9f90324-2c8e-44bf-b6e8-211303a46ed2'),'TEXT','','',1,1,5,5,5,5,"example.com/text",3,UUID_TO_BIN('4c1f9f4f-db9a-47c9-86dc-d24784914913')),
(UUID_TO_BIN('f75c325c-c40d-421e-a990-30b63f4b608f'),'IMAGE','','',1,1,10,10,10,10,"example.com/image1",3,UUID_TO_BIN('4c1f9f4f-db9a-47c9-86dc-d24784914913')),
(UUID_TO_BIN('5a9c0aec-1b96-4649-a0ff-1afba4e145a0'),'IMAGE','','',1,1,10,10,10,10,"example.com/image2",3,UUID_TO_BIN('4c1f9f4f-db9a-47c9-86dc-d24784914913'));


