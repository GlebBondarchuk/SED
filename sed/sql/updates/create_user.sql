DROP TABLE IF EXISTS sed_user;
CREATE TABLE IF NOT EXISTS sed_user (
  id       BIGINT            NOT NULL AUTO_INCREMENT,
  name     VARCHAR(50)       NOT NULL,
  password VARCHAR(50)       NOT NULL,
  role     VARCHAR(50)       NOT NULL,
  login    VARCHAR(50)       NOT NULL,
  disabled BIT DEFAULT FALSE NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

INSERT INTO sed_user (name, password, role, login, disabled)
VALUES ('System User', 'system', 'ADMIN', 'sed.bsu@gmail.com', TRUE);
INSERT INTO sed_user (name, password, role, login, disabled)
VALUES ('Gleb Bondarchuk', '899cc984ac3801fa9cb12f1486264628', 'ADMIN', 'gleb.exadel@gmail.com', FALSE);