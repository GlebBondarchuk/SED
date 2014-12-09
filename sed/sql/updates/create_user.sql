DROP TABLE IF EXISTS sed_user;
CREATE TABLE IF NOT EXISTS sed_user (
  id         BIGINT      NOT NULL AUTO_INCREMENT,
  login VARCHAR(50) NOT NULL,
  name  VARCHAR(50) NOT NULL,
  password   VARCHAR(50) NOT NULL,
  email      VARCHAR(50) NOT NULL,
  disabled   BIT DEFAULT FALSE,
  PRIMARY KEY (id))
  ENGINE = InnoDB;