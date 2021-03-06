SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS sed_people;
SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE IF NOT EXISTS sed_people (
  id       BIGINT            NOT NULL AUTO_INCREMENT,
  user_id  BIGINT UNIQUE,
  position VARCHAR(200)      NOT NULL,
  address  VARCHAR(100),

  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES sed_user (id)
    ON DELETE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;