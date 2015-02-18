SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS sed_news;
SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE IF NOT EXISTS sed_news (
  id           BIGINT    NOT NULL AUTO_INCREMENT,
  creator      BIGINT,
  content      BIGINT,
  photo        VARCHAR(200),
  simpleText   VARCHAR(1000),
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  PRIMARY KEY (id),
  FOREIGN KEY (creator) REFERENCES sed_user (id),
  FOREIGN KEY (content) REFERENCES sed_content (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;