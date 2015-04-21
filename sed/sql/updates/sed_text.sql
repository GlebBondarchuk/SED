DROP TABLE IF EXISTS sed_text;
CREATE TABLE IF NOT EXISTS sed_text (
  id          BIGINT    NOT NULL AUTO_INCREMENT,
  text        TEXT,
  text_key       VARCHAR(50) UNIQUE,
  update_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;