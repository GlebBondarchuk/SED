DROP TABLE IF EXISTS sed_system;
CREATE TABLE IF NOT EXISTS sed_system (
  id            BIGINT       NOT NULL AUTO_INCREMENT,
  `key`         VARCHAR(50)  NOT NULL,
  value         VARCHAR(50)  NOT NULL,
  description   VARCHAR(200) NOT NULL,
  display_value VARCHAR(100) NOT NULL,
  category      VARCHAR(50)  NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL', 'sed.bsu@gmail.com', 'System Email Server', 'Only for gmail accounts.', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL_PASSWORD', 'sed.bsu2014', 'Email Password', '', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL_PROTOCOL', 'smtp', 'Email Protocol', '', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL_HOST', 'smtp.gmail.com', 'Email Host', '', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL_PORT', '587', 'Email Port', '', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL_MASK', '@gmail.com', 'Email Mask', '', 'EMAIL');

