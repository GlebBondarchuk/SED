DROP TABLE IF EXISTS sed_user;
CREATE TABLE IF NOT EXISTS sed_user (
  id       BIGINT            NOT NULL AUTO_INCREMENT,
  name     VARCHAR(50)       NOT NULL,
  password VARCHAR(50)       NOT NULL,
  role     VARCHAR(50)       NOT NULL,
  login    VARCHAR(50)       NOT NULL UNIQUE,
  disabled BIT DEFAULT FALSE NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

INSERT INTO sed_user (name, password, role, login, disabled)
VALUES ('System', 'system', 'ADMIN', 'sed.bsu@gmail.com', TRUE);

/**
  Admin User
 */
INSERT INTO sed_user (name, password, role, login, disabled)
VALUES ('Admin', '21232f297a57a5a743894a0e4a801fc3', 'ADMIN', 'admin', FALSE);

/**
  Teacher User
 */
INSERT INTO sed_user (name, password, role, login, disabled)
VALUES ('Teacher', '8d788385431273d11e8b43bb78f3aa41', 'TEACHER', 'teacher', FALSE);

/**
  Student User
 */
INSERT INTO sed_user (name, password, role, login, disabled)
VALUES ('Student', 'cd73502828457d15655bbd7a63fb0bc8', 'STUDENT', 'student', FALSE);

INSERT INTO sed_user (name, password, role, login, disabled)
VALUES ('Gleb Bondarchuk', '899cc984ac3801fa9cb12f1486264628', 'ADMIN', 'gleb.exadel@gmail.com', FALSE);