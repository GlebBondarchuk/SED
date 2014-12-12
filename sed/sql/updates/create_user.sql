DROP TABLE IF EXISTS sed_user;
CREATE TABLE IF NOT EXISTS sed_user (
  id       BIGINT            NOT NULL AUTO_INCREMENT,
  login    VARCHAR(50)       NOT NULL,
  name     VARCHAR(50)       NOT NULL,
  password VARCHAR(50)       NOT NULL,
  role     VARCHAR(50)       NOT NULL,
  email    VARCHAR(50)       NOT NULL,
  disabled BIT DEFAULT FALSE NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

INSERT INTO sed_user (login, name, password, role, email, disabled) VALUES ('system', 'system', 'system', 'ADMIN', 'system@sed.com', TRUE);