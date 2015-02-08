SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS sed_people_content;
SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE IF NOT EXISTS sed_people_content (
  id            BIGINT NOT NULL AUTO_INCREMENT,
  people_id     BIGINT,
  content_id    BIGINT,
  content_title VARCHAR(100),

  PRIMARY KEY (id),
  FOREIGN KEY (people_id) REFERENCES sed_people (id)
    ON DELETE CASCADE,
  FOREIGN KEY (content_id) REFERENCES sed_content (id)
    ON DELETE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;