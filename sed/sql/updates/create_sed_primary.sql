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
  DEFAULT CHARSET = utf8;

INSERT INTO sed_primary (content_id, `key`)
VALUES (1, 'EDUCATIONAL_WORK');

INSERT INTO sed_primary (content_id, `key`)
VALUES (2, 'SCIENTIFIC_WORK');

INSERT INTO sed_primary (content_id, `key`)
VALUES (3, 'CONFERENCE');

INSERT INTO sed_primary (content_id, `key`)
VALUES (4, 'DEPARTMENT_INFO');

INSERT INTO sed_primary (content_id, `key`)
VALUES (5, 'MAIN_CONTENT')
