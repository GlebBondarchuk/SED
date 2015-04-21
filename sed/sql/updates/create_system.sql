DROP TABLE IF EXISTS sed_system;
CREATE TABLE IF NOT EXISTS sed_system (
  id            BIGINT       NOT NULL         AUTO_INCREMENT,
  `key`         VARCHAR(50)  NOT NULL,
  value         VARCHAR(350)  NOT NULL,
  description   VARCHAR(200) NOT NULL,
  display_value VARCHAR(100) NOT NULL,
  `bit`         BIT          NOT NULL         DEFAULT FALSE,
  `date`        BIT          NOT NULL         DEFAULT FALSE,
  category      VARCHAR(50)  NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL', 'sed.bsu@gmail.com', 'System Email Server', 'Only for gmail accounts.', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL_PASSWORD', 'sed.bsu2014', 'Email Password', 'description', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL_PROTOCOL', 'smtp', 'Email Protocol', 'description', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL_HOST', 'smtp.gmail.com', 'Email Host', 'description', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL_PORT', '587', 'Email Port', 'description', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL_MASK', '@gmail.com', 'Email Mask', 'description', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category, `bit`)
VALUES ('CHECK_PEOPLE_REGISTRATION', 'true', 'Check Peoples Registration', 'description', 'PEOPLES', TRUE);

INSERT INTO sed_system (`key`, value, display_value, description, category, `bit`)
VALUES ('CHECK_STUDENT_REGISTRATION', 'true', 'Check Students Registration', 'description', 'STUDENTS', TRUE);

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('NEWS_PAGE_LIMIT', '5', 'News Page Limit', 'description', 'NEWS');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('NEWS_SHOWING_PERIOD', '30', 'News showing period', 'Remove news after X days passed', 'NEWS');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('GALLERY_PAGE_LIMIT', '9', 'Gallery page limit', 'Gallery page limit', 'GALLERY');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('SEARCH_PAGE_LIMIT', '10', 'Search Page Limit', 'Search Page Limit', 'SEARCH');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL_SERVER', 'http://webmail.bsu.by', 'Mail Server', 'Mail Server', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('MAP_URL', 'https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d2351.1269220140075!2d27.547007999999995!3d53.893947999999995!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x46dbcf9b2cfa2ddf%3A0x61bdf8323afbfd08!2z0JHQtdC70L7RgNGD0YHRgdC60LjQuSDQs9C-0YHRg9C00LDRgNGB0YLQstC10L3QvdGL0Lkg0YPQvdC40LLQtdGA0YHQuNGC0LXRgg!5e0!3m2!1sru!2s!4v1422195021598', 'Map URL', 'Map URL', 'CONTACT');
#
# INSERT INTO sed_system (`key`, value, display_value, description, category)
# VALUES ('IMAGE_URLS', 'http://puu.sh/fDHYt/7e29d8df36.jpg', 'Size should be 750x400', 'Image URLS', 'IMAGE');

