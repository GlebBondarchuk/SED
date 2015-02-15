SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS sed_people_content;
SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE IF NOT EXISTS sed_people_content (
  people_id  BIGINT,
  content_id BIGINT,

  UNIQUE (content_id),
  PRIMARY KEY (people_id, content_id),
  FOREIGN KEY (people_id) REFERENCES sed_people (id)
    ON DELETE CASCADE,
  FOREIGN KEY (content_id) REFERENCES sed_content (id)
    ON DELETE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;