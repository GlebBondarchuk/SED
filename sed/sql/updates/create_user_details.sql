SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS sed_user_details;
SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE IF NOT EXISTS sed_user_details (
  id      BIGINT NOT NULL AUTO_INCREMENT,
  phone   VARCHAR(50),
  address VARCHAR(100),
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;
