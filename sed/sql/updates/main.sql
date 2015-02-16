SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS sed_user_details;
SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE IF NOT EXISTS sed_user_details (
  id      BIGINT NOT NULL AUTO_INCREMENT,
  address VARCHAR(100),
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;
#---------------------------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------
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
VALUES ('Admin', '21232f297a57a5a743894a0e4a801fc3', 'ADMIN', 'admin', 'admin@bsu.by', FALSE);

/**
  Teacher User
 */
INSERT INTO sed_user (name, password, role, login, email, disabled)
VALUES ('Teacher', '8d788385431273d11e8b43bb78f3aa41', 'TEACHER', 'teacher', 'teacher@bsu.by', FALSE);

/**
  Student User
 */
INSERT INTO sed_user (name, password, role, login, email, disabled)
VALUES ('Student', 'cd73502828457d15655bbd7a63fb0bc8', 'STUDENT', 'student', 'student@bsu.by', FALSE);

#---------------------------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS sed_system;
CREATE TABLE IF NOT EXISTS sed_system (
  id            BIGINT       NOT NULL         AUTO_INCREMENT,
  `key`         VARCHAR(50)  NOT NULL,
  value         VARCHAR(50)  NOT NULL,
  description   VARCHAR(200) NOT NULL,
  display_value VARCHAR(100) NOT NULL,
  `bit`         BIT          NOT NULL         DEFAULT FALSE,
  category      VARCHAR(50)  NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL', 'sed.bsu@gmail.com', 'System Email Server', 'Only for gmail accounts.', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL_PASSWORD', 'sed.bsu2014', 'Email Password', '', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL_PROTOCOL', 'smtp', 'Email Protocol', '', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL_HOST', 'smtp.gmail.com', 'Email Host', '', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL_PORT', '587', 'Email Port', '', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL_MASK', '@gmail.com', 'Email Mask', '', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category, `bit`)
VALUES ('CHECK_PEOPLE_REGISTRATION', 'true', 'Check Peoples Registration', '', 'PEOPLES', TRUE);

INSERT INTO sed_system (`key`, value, display_value, description, category, `bit`)
VALUES ('CHECK_STUDENT_REGISTRATION', 'true', 'Check Students Registration', '', 'STUDENTS', TRUE);


#---------------------------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS sed_student;
SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE IF NOT EXISTS sed_student (
  id      BIGINT      NOT NULL AUTO_INCREMENT,
  user_id BIGINT UNIQUE,
  course  VARCHAR(10) NOT NULL,
  student_group VARCHAR(10) NOT NULL,

  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES sed_user (id)
    ON DELETE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;

#---------------------------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS sed_people;
SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE IF NOT EXISTS sed_people (
  id       BIGINT            NOT NULL AUTO_INCREMENT,
  user_id  BIGINT UNIQUE,
  head     BIT DEFAULT FALSE NOT NULL,
  position VARCHAR(200)      NOT NULL,
  address  VARCHAR(100),

  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES sed_user (id)
    ON DELETE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;

#---------------------------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS sed_content;
SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE IF NOT EXISTS sed_content (
  id           BIGINT       NOT NULL AUTO_INCREMENT,
  name         VARCHAR(200) NOT NULL,
  content      BLOB,
  update_date  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  content_type VARCHAR(50),
  creator      BIGINT       NULL     DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (creator) REFERENCES sed_user (id)
    ON DELETE SET NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;

INSERT INTO sed_content (name)
VALUES ('Educational');

INSERT INTO sed_content (name)
VALUES ('Scientific');

INSERT INTO sed_content (name)
VALUES ('Conference');

INSERT INTO sed_content (name)
VALUES ('Department Info');

#---------------------------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS sed_people_content;
SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE IF NOT EXISTS sed_people_content (
  people_id  BIGINT,
  content_id BIGINT,

  UNIQUE (content_id),
  PRIMARY KEY (people_id, content_id),
  FOREIGN KEY (people_id) REFERENCES sed_people (id)
    ON DELETE CASCADE,
  FOREIGN KEY (content_id) REFERENCES sed_content (id)
    ON DELETE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;

#---------------------------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------

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

#---------------------------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS sed_news;
SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE IF NOT EXISTS sed_news (
  id           BIGINT    NOT NULL AUTO_INCREMENT,
  creator      BIGINT UNIQUE,
  content      BIGINT,
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  PRIMARY KEY (id),
  FOREIGN KEY (creator) REFERENCES sed_user (id),
  FOREIGN KEY (content) REFERENCES sed_content (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;

#---------------------------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------


SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS sed_primary;
SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE IF NOT EXISTS sed_primary (
  id         BIGINT      NOT NULL AUTO_INCREMENT,
  content_id BIGINT,
  `key`      VARCHAR(50) NOT NULL UNIQUE,

  PRIMARY KEY (id),
  FOREIGN KEY (content_id) REFERENCES sed_content (id)
    ON DELETE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;

INSERT INTO sed_primary (content_id, `key`)
VALUES (1, 'EDUCATIONAL_WORK');

INSERT INTO sed_primary (content_id, `key`)
VALUES (2, 'SCIENTIFIC_WORK');

INSERT INTO sed_primary (content_id, `key`)
VALUES (3, 'CONFERENCE');

INSERT INTO sed_primary (content_id, `key`)
VALUES (4, 'DEPARTMENT_INFO');

#---------------------------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------