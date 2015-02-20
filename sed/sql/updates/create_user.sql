SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS sed_user;
SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE IF NOT EXISTS sed_user (
  id       BIGINT            NOT NULL AUTO_INCREMENT,
  name     VARCHAR(50)       NOT NULL UNIQUE,
  password VARCHAR(50)       NOT NULL,
  role     VARCHAR(50)       NOT NULL,
  email    VARCHAR(50)       NOT NULL UNIQUE,
  login    VARCHAR(50)       NOT NULL UNIQUE,
  phone    VARCHAR(50),
  photo    VARCHAR(100),
  details  BIGINT                     DEFAULT NULL,
  disabled BIT DEFAULT FALSE NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (details) REFERENCES sed_user_details (id)
    ON DELETE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;

INSERT INTO sed_user (name, password, role, login, email, disabled)
VALUES ('System', 'system', 'ADMIN', 'sed.bsu', 'sed.bsu@gmail.com', TRUE);

/**
  Admin User
 */
INSERT INTO sed_user (name, password, role, login, email, disabled)
VALUES ('Admin', '21232f297a57a5a743894a0e4a801fc3', 'ADMIN', 'admin', 'admin', FALSE);

/**
  Teacher User
 */
INSERT INTO sed_user (name, password, role, login, email, disabled)
VALUES ('Teacher', '8d788385431273d11e8b43bb78f3aa41', 'TEACHER', 'teacher', 'teacher', FALSE);

/**
  Student User
 */
INSERT INTO sed_user (name, password, role, login, email, disabled)
VALUES ('Student', 'cd73502828457d15655bbd7a63fb0bc8', 'STUDENT', 'student', 'student', FALSE);

