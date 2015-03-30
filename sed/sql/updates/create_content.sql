SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS sed_content;
SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE IF NOT EXISTS sed_content (
  id           BIGINT       NOT NULL AUTO_INCREMENT,
  name         VARCHAR(200) NOT NULL,
  content      BLOB,
  update_date  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  content_type VARCHAR(50),
  creator      BIGINT       NULL     DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (creator) REFERENCES sed_user (id)
    ON DELETE SET NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8
  DEFAULT COLLATE utf8_general_ci;

INSERT INTO sed_content (name)
VALUES ('Educational');

INSERT INTO sed_content (name)
VALUES ('Scientific');

INSERT INTO sed_content (name)
VALUES ('Conference');

INSERT INTO sed_content (name)
VALUES ('Department Info');