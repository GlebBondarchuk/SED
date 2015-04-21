DROP TABLE IF EXISTS sed_gallery;
CREATE TABLE IF NOT EXISTS sed_gallery (
  id          BIGINT NOT NULL AUTO_INCREMENT,
  description VARCHAR(300),
  document_id BIGINT,
  PRIMARY KEY (id),
  FOREIGN KEY (document_id) REFERENCES sed_document (id) ON DELETE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;