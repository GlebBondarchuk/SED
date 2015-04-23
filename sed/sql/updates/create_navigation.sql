DROP TABLE IF EXISTS sed_navigation;
CREATE TABLE IF NOT EXISTS sed_navigation (
  id              BIGINT      NOT NULL AUTO_INCREMENT,
  parent_id       BIGINT,
  text            VARCHAR(200) NOT NULL,
  url             VARCHAR(300),
  role            VARCHAR(20),
  list_number     INT(3),
  list_order      INT(3),
  lang            VARCHAR(3),
  navbar          BIT,
  PRIMARY KEY (id),
  FOREIGN KEY (parent_id) REFERENCES sed_navigation (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;