DROP TABLE IF EXISTS sed_statistics;
CREATE TABLE IF NOT EXISTS sed_statistics (
  id      BIGINT NOT NULL AUTO_INCREMENT,
  host    VARCHAR(100),
  date    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  country VARCHAR(100),
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;