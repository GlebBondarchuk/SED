DROP TABLE IF EXISTS sed_news_url;
CREATE TABLE IF NOT EXISTS sed_news_url (
  id           BIGINT       NOT NULL AUTO_INCREMENT,
  url          VARCHAR(500) NOT NULL,
  search_words VARCHAR(500) NOT NULL,
  imageUrl     VARCHAR(500),
  last_search  TIMESTAMP,
  disabled     BIT                   DEFAULT FALSE,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;