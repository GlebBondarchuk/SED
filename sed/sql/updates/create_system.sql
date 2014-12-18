DROP TABLE IF EXISTS sed_system;
CREATE TABLE IF NOT EXISTS sed_system (
  id            BIGINT       NOT NULL AUTO_INCREMENT,
  `key`        VARCHAR(50)  NOT NULL,
  value         VARCHAR(50)  NOT NULL,
  description   VARCHAR(200) NOT NULL,
  display_value VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

INSERT INTO sed_system (`key`, value, display_value, description)
VALUES ('email', 'sed.bsu@gmail.com', 'System Email Server', 'Recommended to use gmail account.');

INSERT INTO sed_system (`key`, value, display_value, description)
VALUES ('emailPassword', 'sed.bsu2014', 'Email Password', '');
