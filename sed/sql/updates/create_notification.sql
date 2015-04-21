DROP TABLE IF EXISTS sed_notification;
CREATE TABLE IF NOT EXISTS sed_notification (
  id       BIGINT       NOT NULL AUTO_INCREMENT,
  from_id  BIGINT,
  to_id    BIGINT,
  message  VARCHAR(500) NOT NULL,
  send_date TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (from_id) REFERENCES sed_user (id)
    ON DELETE CASCADE,
  FOREIGN KEY (to_id) REFERENCES sed_user (id)
    ON DELETE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;