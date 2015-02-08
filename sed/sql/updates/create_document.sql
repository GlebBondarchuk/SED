DROP TABLE IF EXISTS sed_document;
CREATE TABLE IF NOT EXISTS sed_document (
  id           BIGINT       NOT NULL AUTO_INCREMENT,
  name         VARCHAR(200) NOT NULL,
  created_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  content_type VARCHAR(50)  NOT NULL,
  category     VARCHAR(50)  NOT NULL,
  creator      BIGINT       NULL     DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (creator) REFERENCES sed_user (id)
    ON DELETE SET NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;

INSERT INTO sed_document (name, content_type, category, creator)
VALUES ('no_photo_available.jpg', 'image/jpg', 'USER_PHOTO', 1);