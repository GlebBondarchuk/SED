DROP TABLE IF EXISTS sed_process;
CREATE TABLE IF NOT EXISTS sed_process (
  id          BIGINT             NOT NULL AUTO_INCREMENT,
  process     VARCHAR(50) UNIQUE NOT NULL,
  description VARCHAR(250)       NOT NULL,
  displayName VARCHAR(70)        NOT NULL,
  disabled    BIT                         DEFAULT FALSE,
  cron        VARCHAR(150)        NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

INSERT INTO sed_process (process, displayName, description, cron) VALUES ('UPDATE_NEWS', 'News Update Process', 'Find news', '0 00 18 * * ?');

INSERT INTO sed_process (process, displayName, description, cron) VALUES ('DELETE_NEWS', 'News Delete Process', 'Delete news', '0 00 18 * * ?');