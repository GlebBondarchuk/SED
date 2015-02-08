DROP TABLE IF EXISTS sed_content;
CREATE TABLE IF NOT EXISTS sed_content (
  id           BIGINT       NOT NULL AUTO_INCREMENT,
  name         VARCHAR(200) NOT NULL,
  content      BLOB         NOT NULL,
  update_date  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  content_type VARCHAR(50)  NOT NULL,
  creator      BIGINT       NULL     DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (creator) REFERENCES sed_user (id)
    ON DELETE SET NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;