DROP TABLE IF EXISTS sed_system;
CREATE TABLE IF NOT EXISTS sed_system (
  id            BIGINT       NOT NULL         AUTO_INCREMENT,
  `key`         VARCHAR(50)  NOT NULL,
  value         VARCHAR(350)  NOT NULL,
  description   VARCHAR(200) NOT NULL,
  display_value VARCHAR(100) NOT NULL,
  `bit`         BIT          NOT NULL         DEFAULT FALSE,
  `date`        BIT          NOT NULL         DEFAULT FALSE,
  category      VARCHAR(50)  NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL', 'sed.bsu@gmail.com', 'System Email Server', 'Only for gmail accounts.', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL_PASSWORD', 'sed.bsu2014', 'Email Password', 'description', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL_PROTOCOL', 'smtp', 'Email Protocol', 'description', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL_HOST', 'smtp.gmail.com', 'Email Host', 'description', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL_PORT', '587', 'Email Port', 'description', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL_MASK', '@gmail.com', 'Email Mask', 'description', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category, `bit`)
VALUES ('CHECK_PEOPLE_REGISTRATION', 'true', 'Check Peoples Registration', 'description', 'PEOPLES', TRUE);

INSERT INTO sed_system (`key`, value, display_value, description, category, `bit`)
VALUES ('CHECK_STUDENT_REGISTRATION', 'true', 'Check Students Registration', 'description', 'STUDENTS', TRUE);

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('NEWS_PAGE_LIMIT', '5', 'News Page Limit', 'description', 'NEWS');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('NEWS_SHOWING_PERIOD', '30', 'News showing period', 'Remove news after X days passed', 'NEWS');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('GALLERY_PAGE_LIMIT', '9', 'Gallery page limit', 'Gallery page limit', 'GALLERY');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('SEARCH_PAGE_LIMIT', '10', 'Search Page Limit', 'Search Page Limit', 'SEARCH');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('EMAIL_SERVER', 'http://webmail.bsu.by', 'Mail Server', 'Mail Server', 'EMAIL');

INSERT INTO sed_system (`key`, value, display_value, description, category)
VALUES ('MAP_URL', 'https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d2351.1269220140075!2d27.547007999999995!3d53.893947999999995!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x46dbcf9b2cfa2ddf%3A0x61bdf8323afbfd08!2z0JHQtdC70L7RgNGD0YHRgdC60LjQuSDQs9C-0YHRg9C00LDRgNGB0YLQstC10L3QvdGL0Lkg0YPQvdC40LLQtdGA0YHQuNGC0LXRgg!5e0!3m2!1sru!2s!4v1422195021598', 'Map URL', 'Map URL', 'CONTACT');

#------------------------------------------------------------------------------------------------------------------------------------------

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

#------------------------------------------------------------------------------------------------------------------------------------------

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
  news_subscriber BIT DEFAULT FALSE,
  disabled BIT DEFAULT FALSE NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;

INSERT INTO sed_user (id, name, password, role, email, login, phone, photo, disabled, news_subscriber) VALUES (1, 'System', 'system', 'ADMIN', 'sed.bsu@gmail.com', 'sed.bsu', '1', '1', true, false);
INSERT INTO sed_user (id, name, password, role, email, login, phone, photo, disabled, news_subscriber) VALUES (2, 'Gleb Bondarchuk', '21232f297a57a5a743894a0e4a801fc3', 'ADMIN', 'gleb.exadel@gmail.com', 'admin', '+375292921166', 'http://cs616630.vk.me/v616630840/b287/K-pwaAZjDI4.jpg', false, true);
INSERT INTO sed_user (id, name, password, role, email, login, phone, photo, disabled, news_subscriber) VALUES (3, 'Курбацкий Александр Николаевич ', '21232f297a57a5a743894a0e4a801fc3', 'TEACHER', 'fpm.nalivkoSA@bsu.by', 'bsu1', '(8-017) 209-52-73 ', 'http://localhost:8980/sed/get/2015/04/21/no_photo_available.jpg', false, false);
INSERT INTO sed_user (id, name, password, role, email, login, phone, photo, disabled, news_subscriber) VALUES (4, 'Василенко Жанна Витальевна', '21232f297a57a5a743894a0e4a801fc3', 'TEACHER', 'bsu2@bsu.by', 'bsu2', '+375 (17) 209 52 73', 'http://fpmi.bsu.by/sm_full.aspx?guid=25893', false, false);
INSERT INTO sed_user (id, name, password, role, email, login, phone, photo, disabled, news_subscriber) VALUES (7, 'Ляшевич Никита', '21232f297a57a5a743894a0e4a801fc3', 'STUDENT', 'nikita1.lyashevich@gmail.com', 'fpm.liashevich', '+375291804714', 'https://pp.vk.me/c618324/v618324476/155f2/5OIBysQg4zQ.jpg', false, false);
INSERT INTO sed_user (id, name, password, role, email, login, phone, photo, disabled, news_subscriber) VALUES (9, 'Войтешенко Иосиф Станиславович', '21232f297a57a5a743894a0e4a801fc3', 'TEACHER', 'voit1@bsu.by', 'voit', '(8-017) 209-52-73 ', 'http://fpmi.bsu.by/sm_full.aspx?guid=25913', false, false);
INSERT INTO sed_user (id, name, password, role, email, login, phone, photo, disabled, news_subscriber) VALUES (10, 'Бондаренко Светлана Петровна', '21232f297a57a5a743894a0e4a801fc3', 'TEACHER', 'bsu5@bsu.by', 'bsu5', '(8-017) 209-52-73 ', 'http://fpmi.bsu.by/sm_full.aspx?guid=25953', false, false);
INSERT INTO sed_user (id, name, password, role, email, login, phone, photo, disabled, news_subscriber) VALUES (11, 'Пазюра Екатерина Васильевна', '21232f297a57a5a743894a0e4a801fc3', 'TEACHER', 'bsu7@bsu.by', 'bsu7', '(8-017) 209-52-73 ', 'http://fpmi.bsu.by/sm_full.aspx?guid=25993', false, false);
INSERT INTO sed_user (id, name, password, role, email, login, phone, photo, disabled, news_subscriber) VALUES (12, 'Карпович Наталья Александровна', '21232f297a57a5a743894a0e4a801fc3', 'TEACHER', 'bsu11@bsu.by', 'bsu11', '(8-017) 209-52-73 ', 'http://fpmi.bsu.by/sm_full.aspx?guid=25983', false, false);
INSERT INTO sed_user (id, name, password, role, email, login, phone, photo, disabled, news_subscriber) VALUES (14, '1233', '202cb962ac59075b964b07152d234b70', 'STUDENT', 'gleb1.exadel@gmail.com', 'gleb.exadel', '123', 'http://img.tyt.by/n/10/4/andrea_rigoni_.jpg', false, true);
INSERT INTO sed_user (id, name, password, role, email, login, phone, photo, disabled, news_subscriber) VALUES (16, 'Gleb Vladimirovich Bondarchuk ', '202cb962ac59075b964b07152d234b70', 'TEACHER', 'fpm.bondarch@bsu.by', 'fpm.bondarch', '123', 'http://cs618616.vk.me/v618616840/274a3/DrA6UdCERSE.jpg', false, true);
INSERT INTO sed_user (id, name, password, role, email, login, phone, photo, disabled, news_subscriber) VALUES (17, '123123', '202cb962ac59075b964b07152d234b70', 'STUDENT', '123@gmail.com', '123', '11123', 'http://img.tyt.by/n/10/4/andrea_rigoni_.jpg', false, false);


#------------------------------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS sed_text;
CREATE TABLE IF NOT EXISTS sed_text (
  id          BIGINT    NOT NULL AUTO_INCREMENT,
  text        TEXT,
  text_key       VARCHAR(50) UNIQUE,
  update_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;
INSERT INTO sed_text (id, text, text_key, update_date) VALUES (1, '
В Кремле с пониманием отнеслись к намерению президента Беларуси Александра Лукашенко отметить 9 Мая в Минске, а не в Москве, заявил в понедельник пресс-секретарь президента России Дмитрий Песков.
Читать полностью:  http://news.tut.by/politics/444799.html
', 'MAIN_ADDRESS_TEXT', '2015-04-19 16:03:49');
INSERT INTO sed_text (id, text, text_key, update_date) VALUES (2, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quae repudiandae fugiat illo cupiditate excepturi esse officiis consectetur, laudantium qui voluptatem. Ad necessitatibus velit, accusantium expedita debitis impedit rerum totam id. Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus quibusdam recusandae illum, nesciunt, architecto, saepe facere, voluptas eum incidunt dolores magni itaque autem neque velit in. At quia quaerat asperiores.', 'SCIENTIFIC_TEXT', '2015-04-19 16:04:40');
INSERT INTO sed_text (id, text, text_key, update_date) VALUES (3, 'Перечень основных учебных дисциплин:
Программирование
Cпециальность «Прикладная математика (научно-производственная деятельность)»
Операционные системы
', 'EDUCATIONAL_TEXT', '2015-04-19 16:04:54');
INSERT INTO sed_text (id, text, text_key, update_date) VALUES (4, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quae repudiandae fugiat illo cupiditate excepturi esse officiis consectetur, laudantium qui voluptatem. Ad necessitatibus velit, accusantium expedita debitis impedit rerum totam id. Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus quibusdam recusandae illum, nesciunt, architecto, saepe facere, voluptas eum incidunt dolores magni itaque autem neque velit in. At quia quaerat asperiores.', 'CONFERENCE_TEXT', '2015-04-19 16:04:56');
INSERT INTO sed_text (id, text, text_key, update_date) VALUES (5, 'Эксперты из Международной организации труда (МОТ) прогнозируют рост безработицы во всем мире. К 2019-му году хранить трудовую книжку дома будут около 212 миллионов человек. Сейчас таких около 201 миллиона. Меньше чем через пятилетку ряды безработных пополнит население такой страны, как Беларусь. FINANCE.TUT.BY проанализировал данные мирового рынка труда и посмотрел, какое место в нем занимает наша страна.
Читать полностью:  http://finance.tut.by/news444803.html
', 'NEWS_TEXT', '2015-04-21 18:23:46');
INSERT INTO sed_text (id, text, text_key, update_date) VALUES (6, '<h3>Контакты</h3>

        <p>
            Пр. Независимости, 4.,
            Главный корпус, к. 228,230<br>
        </p>

        <p><i class="fa fa-phone"></i>
            <abbr title="Phone">P</abbr>: (+375-17) 209-5273</p>

        <p><i class="fa fa-envelope-o"></i>
            <abbr title="Email">E</abbr>: <a href="mailto:name@example.com ">kurb@unibel.by</a>
        </p>

        <p><i class="fa fa-clock-o"></i>
            <abbr title="Hours">H</abbr>: Понедельник - Пятница: 9:00 AM to 5:00 PM</p>
        <ul class="list-unstyled list-inline list-social-icons">
            <li>
                <a href="https://facebook.com"><i class="fa fa-facebook-square fa-2x"></i></a>
            </li>
            <li>
                <a href="https://linkedin.com"><i class="fa fa-linkedin-square fa-2x"></i></a>
            </li>
            <li>
                <a href="https://twitter.com"><i class="fa fa-twitter-square fa-2x"></i></a>
            </li>
            <li>
                <a href="https://google.com"><i class="fa fa-google-plus-square fa-2x"></i></a>
            </li>
        </ul>
   ', 'CONTACT_DETAILS', '2015-04-21 17:48:42');

#------------------------------------------------------------------------------------------------------------------------------------------

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

#------------------------------------------------------------------------------------------------------------------------------------------

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

INSERT INTO sed_student (id, user_id, course, student_group) VALUES (3, 7, '5', '8');
INSERT INTO sed_student (id, user_id, course, student_group) VALUES (4, 14, '123', '123');
INSERT INTO sed_student (id, user_id, course, student_group) VALUES (5, 17, '123', '123');

#------------------------------------------------------------------------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS sed_people;
SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE IF NOT EXISTS sed_people (
  id       BIGINT            NOT NULL AUTO_INCREMENT,
  user_id  BIGINT UNIQUE,
  position VARCHAR(200)      NOT NULL,
  address  VARCHAR(100),

  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES sed_user (id)
    ON DELETE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;

INSERT INTO sed_people (id, user_id, position, address) VALUES (2, 3, 'Заведующий кафедрой, Заслуженный деятель науки Республики Беларусь, доктор технических наук, профессор', 'Пр. Независимости, 4. Главный корпус БГУ, к. 228, 230');
INSERT INTO sed_people (id, user_id, position, address) VALUES (3, 4, 'Доцент, кандидат технических наук', 'Пр. Независимости, 4. Главный корпус БГУ, к. 228, 230');
INSERT INTO sed_people (id, user_id, position, address) VALUES (4, 9, 'Доцент, кандидат технических наук', 'Пр. Независимости, 4. Главный корпус БГУ, к. 228, 230');
INSERT INTO sed_people (id, user_id, position, address) VALUES (5, 10, 'Старший преподаватель', 'Пр. Независимости, 4. Главный корпус БГУ, к. 228, 230');
INSERT INTO sed_people (id, user_id, position, address) VALUES (6, 11, 'Старший преподаватель', 'Пр. Независимости, 4. Главный корпус БГУ, к. 228, 230');
INSERT INTO sed_people (id, user_id, position, address) VALUES (7, 12, 'Старший преподаватель', 'Пр. Независимости, 4. Главный корпус БГУ, к. 228, 230');
INSERT INTO sed_people (id, user_id, position, address) VALUES (10, 16, '123', '123');
INSERT INTO sed_people (id, user_id, position, address) VALUES (11, 2, 'Старший преподаватель', 'Пр. Независимости, 4. Главный корпус БГУ, к. 228, 230');

#------------------------------------------------------------------------------------------------------------------------------------------

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

#------------------------------------------------------------------------------------------------------------------------------------------

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
  ENGINE = InnoDB,
  DEFAULT CHARSET =utf8;

INSERT INTO sed_news_url (id, url, search_words, last_search, disabled, imageUrl) VALUES (72, 'http://news.tut.by/rss/all.rss', 'а', '2015-04-21 19:12:55', false, 'http://img.tyt.by/i/rss/news/logo.gif');
INSERT INTO sed_news_url (id, url, search_words, last_search, disabled, imageUrl) VALUES (73, 'http://habrahabr.ru/rss/interesting', 'а', '2015-04-21 19:12:56', false, 'http://habrahabr.ru/i/logo.gif');
INSERT INTO sed_news_url (id, url, search_words, last_search, disabled, imageUrl) VALUES (74, 'http://feeds.reuters.com/reuters/peopleNews', 'a', '2015-04-21 19:12:56', false, 'http://www.reuters.com/resources_v2/images/reuters125.png');
INSERT INTO sed_news_url (id, url, search_words, last_search, disabled, imageUrl) VALUES (75, 'http://www.mmf.bsu.by/news.xml', 'а', '2015-04-21 19:12:56', false, null);

#------------------------------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS sed_navigation;
CREATE TABLE IF NOT EXISTS sed_navigation (
  id              BIGINT      NOT NULL AUTO_INCREMENT,
  parent_id       BIGINT,
  text            VARCHAR(200) NOT NULL,
  url             VARCHAR(300),
  authorized_only BIT                  DEFAULT FALSE,
  list_number     INT(3),
  list_order      INT(3),
  lang            VARCHAR(3),
  navbar          BIT,
  PRIMARY KEY (id),
  FOREIGN KEY (parent_id) REFERENCES sed_navigation (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;

INSERT INTO sed_navigation (id, parent_id, text, url, authorized_only, list_number, list_order, lang, navbar) VALUES (24, null, 'Информация', null, true, 2, 0, null, false);
INSERT INTO sed_navigation (id, parent_id, text, url, authorized_only, list_number, list_order, lang, navbar) VALUES (26, 24, 'Site Map', '/map', false, 0, 0, null, false);
INSERT INTO sed_navigation (id, parent_id, text, url, authorized_only, list_number, list_order, lang, navbar) VALUES (27, 24, 'Состав Кафедры', '/peoples', false, 0, 0, null, false);
INSERT INTO sed_navigation (id, parent_id, text, url, authorized_only, list_number, list_order, lang, navbar) VALUES (28, 24, 'Студенты', '/students', true, 0, 0, null, false);
INSERT INTO sed_navigation (id, parent_id, text, url, authorized_only, list_number, list_order, lang, navbar) VALUES (30, 24, 'Информация Кафедры', null, false, 0, 0, null, false);
INSERT INTO sed_navigation (id, parent_id, text, url, authorized_only, list_number, list_order, lang, navbar) VALUES (31, 30, 'Учебная работа', '/content/educational', false, 0, 0, null, false);
INSERT INTO sed_navigation (id, parent_id, text, url, authorized_only, list_number, list_order, lang, navbar) VALUES (32, 30, 'Научная работа', '/content/scientific', false, 5, 0, null, false);
INSERT INTO sed_navigation (id, parent_id, text, url, authorized_only, list_number, list_order, lang, navbar) VALUES (33, 30, 'Конференции', '/content/conference', false, 0, 0, null, false);
INSERT INTO sed_navigation (id, parent_id, text, url, authorized_only, list_number, list_order, lang, navbar) VALUES (34, null, 'Новости', null, false, 0, 0, null, false);
INSERT INTO sed_navigation (id, parent_id, text, url, authorized_only, list_number, list_order, lang, navbar) VALUES (35, 34, 'Новости Кафедры', '/news', false, 0, 0, null, false);
INSERT INTO sed_navigation (id, parent_id, text, url, authorized_only, list_number, list_order, lang, navbar) VALUES (36, null, 'Контакты', '/contact', false, -2, 0, null, false);
INSERT INTO sed_navigation (id, parent_id, text, url, authorized_only, list_number, list_order, lang, navbar) VALUES (37, null, 'Галерея', '/gallery', false, 0, 0, null, false);
INSERT INTO sed_navigation (id, parent_id, text, url, authorized_only, list_number, list_order, lang, navbar) VALUES (38, null, 'Заведующий Кафедрой', '/people/bsu1', false, 1, 0, null, false);
INSERT INTO sed_navigation (id, parent_id, text, url, authorized_only, list_number, list_order, lang, navbar) VALUES (39, null, 'Лидеры Кубы и США провели переговоры впервые с 1956 года', '/content/18375', false, 0, 0, null, false);
INSERT INTO sed_navigation (id, parent_id, text, url, authorized_only, list_number, list_order, lang, navbar) VALUES (41, null, 'THIS PAGE', '/map/add', false, 0, 0, null, false);
INSERT INTO sed_navigation (id, parent_id, text, url, authorized_only, list_number, list_order, lang, navbar) VALUES (43, null, 'Системные Аттрибуты', '/admin/system', false, 0, 0, null, false);
INSERT INTO sed_navigation (id, parent_id, text, url, authorized_only, list_number, list_order, lang, navbar) VALUES (3652, null, 'аffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffddddddddddddddddddddddddddddddddddd', '/map/edit/3652', true, 0, 0, null, false);
INSERT INTO sed_navigation (id, parent_id, text, url, authorized_only, list_number, list_order, lang, navbar) VALUES (3653, 24, '17 мгновений весны', '/content/21222', true, 0, 0, null, false);
INSERT INTO sed_navigation (id, parent_id, text, url, authorized_only, list_number, list_order, lang, navbar) VALUES (3654, 24, '345', '/map/add', true, 0, 0, null, false);
INSERT INTO sed_navigation (id, parent_id, text, url, authorized_only, list_number, list_order, lang, navbar) VALUES (3655, null, 'HTML', '/content/21747', false, 0, 0, null, false);
INSERT INTO sed_navigation (id, parent_id, text, url, authorized_only, list_number, list_order, lang, navbar) VALUES (3656, null, '123', null, false, 0, 0, null, false);

#------------------------------------------------------------------------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS sed_content;
SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE IF NOT EXISTS sed_content (
  id           BIGINT       NOT NULL AUTO_INCREMENT,
  name         VARCHAR(200) NOT NULL,
  content      LONGBLOB,
  update_date  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  content_type VARCHAR(50),
  creator      BIGINT       NULL     DEFAULT NULL,
  static       BIT DEFAULT FALSE,
  role        VARCHAR(20),
  PRIMARY KEY (id),
  FOREIGN KEY (creator) REFERENCES sed_user (id)
    ON DELETE SET NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8
  DEFAULT COLLATE utf8_general_ci;

INSERT INTO sed_content (id, name, content, update_date, content_type, creator, static, role) VALUES (1, 'Учебная работа', '<div><b><br><a href="http://localhost:8980/sed/content/17262" title="Link: http://localhost:8980/sed/content/17262">http://localhost:8980/sed/content/17262</a><br><span class="wysiwyg-color-green">Перечень основных учебных дисциплин:</span></b></div><ul><li>Программирование<ul><li><a href="http://www.elib.bsu.by/handle/123456789/6994" target="_blank" rel="" title="Link: http://www.elib.bsu.by/handle/123456789/6994">Cпециальность «Прикладная математика (научно-производственная деятельность)»</a></li></ul></li><li>Операционные системы<ul><li><a href="http://www.elib.bsu.by/handle/123456789/6714" target="_blank" rel="" title="Link: http://www.elib.bsu.by/handle/123456789/6714">Cпециальность «Актуарная математика»</a></li><li><a href="http://www.elib.bsu.by/handle/123456789/6985" target="_blank" rel="" title="Link: http://www.elib.bsu.by/handle/123456789/6985">Cпециальность «Прикладная математика (научно-производственная деятельность)»</a></li><li>Cпециальность «Прикладная информатика (программное обеспечение компьютерных систем)»</li></ul></li><li>Теоретические основы информационной безопасности<ul><li><a href="http://www.elib.bsu.by/handle/123456789/6907" target="_blank" rel="">Cпециальность «Компьютерная безопасность (математические методы и программные средства)»</a></li></ul></li><li>Программно-аппаратные средства обеспечения информационной безопасности<ul><li><u><a href="http://www.elib.bsu.by/handle/123456789/6902" target="_blank" rel="">Cпециальность «Компьютерная безопасность (математические методы и программные средства)»</a></u></li></ul></li><li>Проектирование программных систем<ul><li><a href="http://www.elib.bsu.by/handle/123456789/6958" target="_blank" rel="">Cпециальность «Прикладная информатика (программное обеспечение компьютерных систем)»</a></li></ul></li><li>Системное программирование<ul><li><a href="http://www.elib.bsu.by/handle/123456789/41642" target="_blank" rel="">Cпециальность «Прикладная информатика (программное обеспечение компьютерных систем)»</a></li></ul></li><li>Аппаратные средства компьютерных систем<ul><li><a href="http://www.elib.bsu.by/handle/123456789/6924" target="_blank" rel="">Cпециальность «Прикладная информатика (программное обеспечение компьютерных систем)»</a></li></ul></li><li><span class="wysiwyg-color-red"><b>Технологии программирования</b></span><ul><li><a href="http://www.elib.bsu.by/handle/123456789/6772" target="_blank" rel="">Cпециальность «Информатика»</a></li><li><a href="http://www.elib.bsu.by/handle/123456789/41649" target="_blank" rel="">Cпециальность «Прикладная информатика (программное обеспечение компьютерных систем)»</a></li></ul></li><li>Программирование мобильных систем<ul><li><a href="http://www.elib.bsu.by/handle/123456789/6958" target="_blank" rel="">Cпециальность «Прикладная информатика (программное обеспечение компьютерных систем)»</a></li></ul></li><li>Избранные главы физики: физика компьютеров<ul><li><a href="http://www.elib.bsu.by/handle/123456789/6774" target="_blank" rel="">Cпециальность «Информатика»</a></li><li><a href="http://www.elib.bsu.by/handle/123456789/6914" target="_blank" rel="">Cпециальность «Компьютерная безопасность (математические методы и программные средства)»</a></li><li><a href="http://www.elib.bsu.by/handle/123456789/7002" target="_blank" rel="">Cпециальность «Прикладная математика (научно-производственная деятельность)»</a></li></ul></li><li>Основы информационных технологий</li><li>Программное обеспечение мультимедийных технологий<ul><li><a href="http://www.elib.bsu.by/handle/123456789/6948" target="_blank" rel="">Cпециальность «Прикладная информатика (программное обеспечение компьютерных систем)»</a></li></ul></li><li>Проектирование человеко-машинных интерфейсов</li><li>Безопасность информационных систем</li><li>Компьютерные сети и системы</li></ul><div><b>Специализация:</b></div><ul><li>Математическое и программное обеспечение вычислительных машин и систем</li><li>Защищенные информационные системы</li><li>Программное обеспечение встроенных систем</li></ul><div><b>Перечень направлений курсовых и дипломных работ:</b><ul><li>Технологии программирования</li><li>Компьютерная безопасность</li><li>Прикладная информатика</li></ul><b>Тематика курсовых работ по кафедре технологий программирования ФПМИ 2012/2013 уч. год&nbsp;</b><b>СПЕЦИАЛЬНОСТЬ:&nbsp;&nbsp; G 1-31 03 03 «Прикладная математика»СПЕЦИАЛИЗАЦИЯ: G 1-31 03 03 09 «Математическое и программное обеспечение вычислительных машин и систем»</b><ul><li>Разработка подсистемы тестирования приложений.</li><li>Технологии Windows Presentation Foundation и Silverlight.</li><li>Проектирование системы проектирования приложений.</li><li>Универсальное CRM веб-приложение.</li><li>Разработка подсистемы расчета нагрузки.</li><li>Распознавание растровых изображений.</li><li>Разработка приложения для координации продаж.</li><li>Разработка подходов к интеграции систем выдачи интеллектуальных документов с внешними информационными системами.</li><li>Сервисы Google и их применение в Java-приложениях.</li><li>Современные технологии разработки Web-приложений на платформе Java.</li><li>Разработка клиент-серверного приложения с использованием REST-сервиса.</li><li>Проектирование Web-сервиса для обмена голосовыми сообщениями.</li><li>Web-сервисы&nbsp;SOAP, REST и Windows Communication Foundation (WCF) при построении приложений сервисно – ориентированной архитектуры.</li><li>Разработка системы автоматизированного тестирования веб-приложений.</li><li>Стиль MVC (Model-View-Controller) и другие стили разработки приложений в ASP.NET 4.</li><li>Разработка сайта региональных олимпиад по программированию.</li><li>Разработка автоматизированной системы формирования и сбора заказов.</li><li>Разработка автоматизированного рабочего места преподавателя.</li><li>Сайт новостей для слабовидящих людей.</li><li>Разработка модуля объемной визуализации медицинских изображений.</li><li>Разработка основных блоков мультиплатформенного фреймфорка для создания игр.</li><li>Разработка системы управления операциями с денежными средствами</li><li>Формирование растровых изображений.</li></ul><b>Специальность:&nbsp;G 1-98 01-01 01 «Компьютерная безопасность»специализация: &nbsp;G 1-98 01-01 03«Защищенные информационные системы»</b><ul><li>Программные средства защиты данных на flash-носителях.</li><li>Исследование методов&nbsp;анализа (оценки, проверки, доказательства) правильности функционирования механизмов безопасности</li><li>Безопасность приложений в ASP.NET.</li><li>Разработка безопасных приложений&nbsp;в среде Visual Studio.</li><li>Безопасность мобильных Web-приложений.</li><li>Анализ методов и систем доказательства корректности программ</li><li>Организация безопасности приложений на платформе .NET</li><li>Разработка защищенного приложения «Интернет-банкинг».</li><li>Сравнительный анализ технологических платформ по обеспечению безопасности программного обеспечения.</li><li>Организация безопасного взаимодействия клиентов с web-приложением на языке Java.</li><li>Безопасность Web-приложений, построенных с помощью сервисов SOAP, REST и Windows Communication Foundation (WCF).</li><li>Методы противодействия DDOS-атакам.</li><li>Организация безопасности клиент-серверных приложений в ASP.NET MVC Framework.</li><li>Универсальная электронная карта. Плюсы и минусы при введении и использовании.</li><li>Анализ развития и распространения технологии Bitcoin</li><li>Разработка защищенного приложения доступом к хранилищу данных проектной документации.</li><li>Защита Web-приложений, построенных с использованием технологии Ajax.</li><li>Разработка Web-приложений на платформе Java. Технология Sprin.</li><li>Разработка системы для поддержки создания игрового приложения на платформе ОС Android.</li><li>Разработка&nbsp;драйвера-фильтра файловой системы для защиты пользовательских данных.</li><li>Реализация платформы для изучения моделей безопасности.</li><li>Анализ и разработка решений по универсальной персональной идентификации.</li><li>Анализ методов стеганографического встраивания информации в html-файлы.</li></ul><b>Специальность: &nbsp;G 1-31 03-07 01 «Прикладная информатика»специализация: G 1-98 01-01 03 &nbsp;«Программное обеспечение встроенных систем»</b><ul><li>Реализация в среде Matlab алгоритма преобразования координат из геодезической системы координат в прямоугольную</li><li>Реализация в среде Matlab Simulink алгоритма определения ориентации беспилотного летательного аппарата по показаниям МЭМС датчиков</li><li>Алгоритм компенсации движения (Motion Compensation) для интер-предсказаний (Inter Prediction) для реализации в СБИС видеокодеков</li><li>Реализация БЧХ декодера для приемника мультимедийного вещания системы РАВИС на языке C/C++ или MATLAB.</li><li>Выбор оптимальной ОС реального времени для решения задач навигации и управления полетом беспилотных летательных аппаратов.</li><li>Управление ресурсами и работа планировщика задач ОС FreeRTOS в составе пилотажно-навигационного комплекса беспилотного летательного аппарата.</li><li>Реализация многозадачности во встраиваемых системах на примере ОС реального времени FreeRTOS.</li><li>Неблокирующая синхронизация</li><li>Методы перехвата API вызовов</li><li>Имплементация минимального загрузчика ядра Linux для процессоров Marvell Kirkwood</li><li>Реализация промышленного протокола FIELDBUS c использованием программируемого модуля реального времени в процессорах Texas Instruments Sitara.</li><li>Реализация драйвера ядра Linux для контролера CAN в процессоре Fujitsu MB86R01.</li><li>Реализация многопоточного кодирования видео на процессорах TI Davinci DM8168.</li></ul><b>Тематика дипломных работпо кафедре технологий программирования ФПМИ2012/2013 уч. годСПЕЦИАЛЬНОСТЬ:&nbsp;&nbsp; G 1-31 03 03 «Прикладная математика»СПЕЦИАЛИЗАЦИЯ: G 1-31 03 03 09 «Математическое и программное обеспечение вычислительных машин и систем»</b><ul><li><div>Разработка инфраструктуры, обеспечивающей взаимодействие участников деловой игры.</div></li><li><div>Разработка алгоритмов сортировки по комбинированным ключам наборов данных с динамически изменяющейся структурой.</div></li><li><div>Анализ подходов и разработка программного обеспечения для сбора данных на основе мобильных терминалов в системах идентификации.</div></li><li><div>Разработка Android-приложений с динамическим интерфейсом &nbsp;и вариативной логикой на основе XML-шаблонов.</div></li><li><div>Поддержка принятия решений по управлению портфелем проектов.</div></li><li><div>Разработка web-приложения автоматизации процесса составления расписания занятий.</div></li><li><div>Система поддержки управления персоналом предприятия на основе моделей компетенций.</div></li><li><div>Моделирование бизнес-процессов предприятия на основе технологий SAP.</div></li><li><div>Разработка автоматизированной системы контроля знаний.</div></li><li><div>Построение модели оценки знаний студентов.</div></li><li><div>Информационно-технологическая поддержка интеграции ИТ проектов.</div></li><li><div>Разработка средств визуализации розы ветров в сетевых игровых приложениях.</div></li><li><div>Разработка приложений сервис - ориентированной архитектуры с использованием технологий Windows Communication Foundation и Windows Workflow Foundation.</div></li></ul><b>Специальность: &nbsp;G 1-98 01-01 01 «Компьютерная безопасность»специализация: G 1-98 01-01 03«Защищенные информационные системы»</b><ul><li><div>Разработка проекта и реализация защищенного мобильного приложения.</div></li><li><div>Исследование методов оценивания рисков, проектирование и разработка программного комплекса для оценивания.</div></li><li><div>Обеспечение безопасного авторизованного&nbsp;доступа к SOAP и REST сервисам в приложениях сервис-ориентированной архитектуры.</div></li><li><div>Проблемы безопасности при компоновке приложений&nbsp;сервис-ориентированной архитектуры.</div></li><li><div>Разработка программных средств защиты данных в локальных сетях Windows.</div></li><li><div>Реализация защищенного мобильного приложения «ИнформЦентрСервис».</div></li><li><div>Методы и средства защиты данных от воздействия вредоносного программного обеспечения.</div></li><li><div>Разработка программного обеспечения криптографической защиты информации в соответствии со стандартами Республики Беларусь.</div></li><li><div>Анализ подходов и разработка средств контроля доступа в условиях миграции интеллектуальных бесконтактных карт на технологию Mifare Plus.</div></li><li><div>Обеспечение безопасности программных систем электронной коммерции.</div></li><li><div>Анализ текстов на естественном языке.</div></li><li><div>Проектирование и разработка защищенной распределенной системы управления группой отелей.</div></li></ul><a href="http://fpmi.bsu.by/sm_full.aspx?guid=9583" target="" rel=""><b>Общие курсы</b></a></div><div><b>Для научно-исследовательской работы студентов созданы следующие лаборатории:</b><ul><li><div>Лаборатория «Бизнес-инкубатор»&nbsp;позволяет развивать студенческие проекты в сфере высоких технологий. Образовательная программа лаборатории включает открытые лекции экспертов, тематические семинары и тренинги.</div></li><li><div>«Лаборатория технологий разработки видеоигр»&nbsp;создана с целью подготовки и повышения навыков студентов в сфере разработки видеоигр для различных аппаратных платформ. В лаборатории студенты и молодые специалисты создают и развивают свои собственные игровые проекты, каждый из которых получает экспертную поддержку специалистов.</div></li></ul><a href="http://fpmi.bsu.by/sm_full.aspx?guid=9573" target="" rel="">Список выпускников кафедры</a></div>', '2015-04-11 16:37:36', null, null, true, null);
INSERT INTO sed_content (id, name, content, update_date, content_type, creator, static, role) VALUES (2, 'Научная работа', '<div>Перечень основных учебных дисциплин:</div><ul><li>Программирование<ul><li><a href="http://www.elib.bsu.by/handle/123456789/6994" target="_blank" rel="">Cпециальность «Прикладная математика (научно-производственная деятельность)»</a></li></ul></li><li>Операционные системы</li><ul><li><a href="http://www.elib.bsu.by/handle/123456789/6714" target="_blank" rel="">Cпециальность «Актуарная математика»</a></li><li><a href="http://www.elib.bsu.by/handle/123456789/6985" target="_blank" rel="">Cпециальность «Прикладная математика (научно-производственная деятельность)»</a></li><li>Cпециальность «Прикладная информатика (программное обеспечение компьютерных систем)»</li></ul></ul><br><div class="embed-responsive embed-responsive-16by9"><iframe frameborder="any" src="http://www.youtube.com/embed/9yAdGrKHSJc" class="embed-responsive-item"></iframe></div><br><br><ul><li>Теоретические основы информационной безопасности<ul><li><a href="http://www.elib.bsu.by/handle/123456789/6907" target="_blank" rel="">Cпециальность «Компьютерная безопасность (математические методы и программные средства)»</a></li></ul></li><li>Программно-аппаратные средства обеспечения информационной безопасности<ul><li><u><a href="http://www.elib.bsu.by/handle/123456789/6902" target="_blank" rel="">Cпециальность «Компьютерная безопасность (математические методы и программные средства)»</a></u></li></ul></li><li>Проектирование программных систем<ul><li><a href="http://www.elib.bsu.by/handle/123456789/6958" target="_blank" rel="">Cпециальность «Прикладная информатика (программное обеспечение компьютерных систем)»</a></li></ul></li><li>Системное программирование<ul><li><a href="http://www.elib.bsu.by/handle/123456789/41642" target="_blank" rel="">Cпециальность «Прикладная информатика (программное обеспечение компьютерных систем)»</a></li></ul></li><li>Аппаратные средства компьютерных систем<ul><li><a href="http://www.elib.bsu.by/handle/123456789/6924" target="_blank" rel="">Cпециальность «Прикладная информатика (программное обеспечение компьютерных систем)»</a></li></ul></li><li>Технологии программирования<ul><li><a href="http://www.elib.bsu.by/handle/123456789/6772" target="_blank" rel="">Cпециальность «Информатика»</a></li><li><a href="http://www.elib.bsu.by/handle/123456789/41649" target="_blank" rel="">Cпециальность «Прикладная информатика (программное обеспечение компьютерных систем)»</a></li></ul></li><li>Программирование мобильных систем<ul><li><a href="http://www.elib.bsu.by/handle/123456789/6958" target="_blank" rel="">Cпециальность «Прикладная информатика (программное обеспечение компьютерных систем)»</a></li></ul></li><li>Избранные главы физики: физика компьютеров<ul><li><a href="http://www.elib.bsu.by/handle/123456789/6774" target="_blank" rel="">Cпециальность «Информатика»</a></li><li><a href="http://www.elib.bsu.by/handle/123456789/6914" target="_blank" rel="">Cпециальность «Компьютерная безопасность (математические методы и программные средства)»</a></li><li><a href="http://www.elib.bsu.by/handle/123456789/7002" target="_blank" rel="">Cпециальность «Прикладная математика (научно-производственная деятельность)»</a></li></ul></li><li>Основы информационных технологий</li><li>Программное обеспечение мультимедийных технологий<ul><li><a href="http://www.elib.bsu.by/handle/123456789/6948" target="_blank" rel="">Cпециальность «Прикладная информатика (программное обеспечение компьютерных систем)»</a></li></ul></li><li>Проектирование человеко-машинных интерфейсов</li><li>Безопасность информационных систем</li><li>Компьютерные сети и системы</li></ul><br><div class="embed-responsive embed-responsive-16by9"><iframe frameborder="any" src="http://www.youtube.com/embed/9yAdGrKHSJc" class="embed-responsive-item"></iframe></div><br><div>Специализация:</div><ul><li>Математическое и программное обеспечение вычислительных машин и систем</li><li>Защищенные информационные системы</li><li>Программное обеспечение встроенных систем</li></ul><div>Перечень направлений курсовых и дипломных работ:<ul><li>Технологии программирования</li><li>Компьютерная безопасность</li><li>Прикладная информатика</li></ul>Тематика курсовых работ по кафедре технологий программирования ФПМИ 2012/2013 уч. год&nbsp;СПЕЦИАЛЬНОСТЬ:&nbsp;&nbsp; G 1-31 03 03 «Прикладная математика»СПЕЦИАЛИЗАЦИЯ: G 1-31 03 03 09 «Математическое и программное обеспечение вычислительных машин и систем»<ul><li>Разработка подсистемы тестирования приложений.</li><li>Технологии Windows Presentation Foundation и Silverlight.</li><li>Проектирование системы проектирования приложений.</li><li>Универсальное CRM веб-приложение.</li><li>Разработка подсистемы расчета нагрузки.</li><li>Распознавание растровых изображений.</li><li>Разработка приложения для координации продаж.</li><li>Разработка подходов к интеграции систем выдачи интеллектуальных документов с внешними информационными системами.</li><li>Сервисы Google и их применение в Java-приложениях.</li><li>Современные технологии разработки Web-приложений на платформе Java.</li><li>Разработка клиент-серверного приложения с использованием REST-сервиса.</li><li>Проектирование Web-сервиса для обмена голосовыми сообщениями.</li><li>Web-сервисы&nbsp;SOAP, REST и Windows Communication Foundation (WCF) при построении приложений сервисно – ориентированной архитектуры.</li><li>Разработка системы автоматизированного тестирования веб-приложений.</li><li>Стиль MVC (Model-View-Controller) и другие стили разработки приложений в ASP.NET 4.</li><li>Разработка сайта региональных олимпиад по программированию.</li><li>Разработка автоматизированной системы формирования и сбора заказов.</li><li>Разработка автоматизированного рабочего места преподавателя.</li><li>Сайт новостей для слабовидящих людей.</li><li>Разработка модуля объемной визуализации медицинских изображений.</li><li>Разработка основных блоков мультиплатформенного фреймфорка для создания игр.</li><li>Разработка системы управления операциями с денежными средствами</li><li>Формирование растровых изображений.</li></ul><br><div class="embed-responsive embed-responsive-16by9"><iframe frameborder="any" src="http://www.youtube.com/embed/9yAdGrKHSJc" class="embed-responsive-item"></iframe></div><br>Специальность:&nbsp;G 1-98 01-01 01 «Компьютерная безопасность»специализация: &nbsp;G 1-98 01-01 03«Защищенные информационные системы»<ul><li>Программные средства защиты данных на flash-носителях.</li><li>Исследование методов&nbsp;анализа (оценки, проверки, доказательства) правильности функционирования механизмов безопасности</li><li>Безопасность приложений в ASP.NET.</li><li>Разработка безопасных приложений&nbsp;в среде Visual Studio.</li><li>Безопасность мобильных Web-приложений.</li><li>Анализ методов и систем доказательства корректности программ</li><li>Организация безопасности приложений на платформе .NET</li><li>Разработка защищенного приложения «Интернет-банкинг».</li><li>Сравнительный анализ технологических платформ по обеспечению безопасности программного обеспечения.</li><li>Организация безопасного взаимодействия клиентов с web-приложением на языке Java.</li><li>Безопасность Web-приложений, построенных с помощью сервисов SOAP, REST и Windows Communication Foundation (WCF).</li><li>Методы противодействия DDOS-атакам.</li><li>Организация безопасности клиент-серверных приложений в ASP.NET MVC Framework.</li><li>Универсальная электронная карта. Плюсы и минусы при введении и использовании.</li><li>Анализ развития и распространения технологии Bitcoin</li><li>Разработка защищенного приложения доступом к хранилищу данных проектной документации.</li><li>Защита Web-приложений, построенных с использованием технологии Ajax.</li><li>Разработка Web-приложений на платформе Java. Технология Sprin.</li><li>Разработка системы для поддержки создания игрового приложения на платформе ОС Android.</li><li>Разработка&nbsp;драйвера-фильтра файловой системы для защиты пользовательских данных.</li><li>Реализация платформы для изучения моделей безопасности.</li><li>Анализ и разработка решений по универсальной персональной идентификации.</li><li>Анализ методов стеганографического встраивания информации в html-файлы.</li></ul>Специальность: &nbsp;G 1-31 03-07 01 «Прикладная информатика»специализация: G 1-98 01-01 03 &nbsp;«Программное обеспечение встроенных систем»<ul><li>Реализация в среде Matlab алгоритма преобразования координат из геодезической системы координат в прямоугольную</li><li>Реализация в среде Matlab Simulink алгоритма определения ориентации беспилотного летательного аппарата по показаниям МЭМС датчиков</li><li>Алгоритм компенсации движения (Motion Compensation) для интер-предсказаний (Inter Prediction) для реализации в СБИС видеокодеков</li><li>Реализация БЧХ декодера для приемника мультимедийного вещания системы РАВИС на языке C/C++ или MATLAB.</li><li>Выбор оптимальной ОС реального времени для решения задач навигации и управления полетом беспилотных летательных аппаратов.</li><li>Управление ресурсами и работа планировщика задач ОС FreeRTOS в составе пилотажно-навигационного комплекса беспилотного летательного аппарата.</li><li>Реализация многозадачности во встраиваемых системах на примере ОС реального времени FreeRTOS.</li><li>Неблокирующая синхронизация</li><li>Методы перехвата API вызовов</li><li>Имплементация минимального загрузчика ядра Linux для процессоров Marvell Kirkwood</li><li>Реализация промышленного протокола FIELDBUS c использованием программируемого модуля реального времени в процессорах Texas Instruments Sitara.</li><li>Реализация драйвера ядра Linux для контролера CAN в процессоре Fujitsu MB86R01.</li><li>Реализация многопоточного кодирования видео на процессорах TI Davinci DM8168.</li></ul>Тематика дипломных работпо кафедре технологий программирования ФПМИ2012/2013 уч. годСПЕЦИАЛЬНОСТЬ:&nbsp;&nbsp; G 1-31 03 03 «Прикладная математика»СПЕЦИАЛИЗАЦИЯ: G 1-31 03 03 09 «Математическое и программное обеспечение вычислительных машин и систем»<ul><li><div>Разработка инфраструктуры, обеспечивающей взаимодействие участников деловой игры.</div></li><li><div>Разработка алгоритмов сортировки по комбинированным ключам наборов данных с динамически изменяющейся структурой.</div></li><li><div>Анализ подходов и разработка программного обеспечения для сбора данных на основе мобильных терминалов в системах идентификации.</div></li><li><div>Разработка Android-приложений с динамическим интерфейсом &nbsp;и вариативной логикой на основе XML-шаблонов.</div></li><li><div>Поддержка принятия решений по управлению портфелем проектов.</div></li><li><div>Разработка web-приложения автоматизации процесса составления расписания занятий.</div></li><li><div>Система поддержки управления персоналом предприятия на основе моделей компетенций.</div></li><li><div>Моделирование бизнес-процессов предприятия на основе технологий SAP.</div></li><li><div>Разработка автоматизированной системы контроля знаний.</div></li><li><div>Построение модели оценки знаний студентов.</div></li><li><div>Информационно-технологическая поддержка интеграции ИТ проектов.</div></li><li><div>Разработка средств визуализации розы ветров в сетевых игровых приложениях.</div></li><li><div>Разработка приложений сервис - ориентированной архитектуры с использованием технологий Windows Communication Foundation и Windows Workflow Foundation.</div></li></ul><br><div class="embed-responsive embed-responsive-4by3"><iframe class="embed-responsive-item" src="//www.youtube.com/embed/zFCFRMjt5Xg" allowfullscreen=""></iframe></div><br><br><br>Специальность: &nbsp;G 1-98 01-01 01 «Компьютерная безопасность»специализация: G 1-98 01-01 03«Защищенные информационные системы»<ul><li><div>Разработка проекта и реализация защищенного мобильного приложения.</div></li><li><div>Исследование методов оценивания рисков, проектирование и разработка программного комплекса для оценивания.</div></li><li><div>Обеспечение безопасного авторизованного&nbsp;доступа к SOAP и REST сервисам в приложениях сервис-ориентированной архитектуры.</div></li><li><div>Проблемы безопасности при компоновке приложений&nbsp;сервис-ориентированной архитектуры.</div></li><li><div>Разработка программных средств защиты данных в локальных сетях Windows.</div></li><li><div>Реализация защищенного мобильного приложения «ИнформЦентрСервис».</div></li><li><div>Методы и средства защиты данных от воздействия вредоносного программного обеспечения.</div></li><li><div>Разработка программного обеспечения криптографической защиты информации в соответствии со стандартами Республики Беларусь.</div></li><li><div>Анализ подходов и разработка средств контроля доступа в условиях миграции интеллектуальных бесконтактных карт на технологию Mifare Plus.</div></li><li><div>Обеспечение безопасности программных систем электронной коммерции.</div></li><li><div>Анализ текстов на естественном языке.</div></li><li>Проектирование и разработка защищенной распределенной системы управления группой отелей.</li></ul><br><div class="embed-responsive embed-responsive-16by9"><iframe class="embed-responsive-item" src="//www.youtube.com/embed/zFCFRMjt5Xg" allowfullscreen=""></iframe></div><br><br><a href="http://fpmi.bsu.by/sm_full.aspx?guid=9583" target="" rel="" title="Link: http://fpmi.bsu.by/sm_full.aspx?guid=9583">Общие курсы</a></div>', '2015-04-20 14:03:32', null, null, true, 'STUDENT');
INSERT INTO sed_content (id, name, content, update_date, content_type, creator, static, role) VALUES (3, 'Конференции', 'ма «Информационной безопасности». Участники: США, Германия, Россия, Китай, Индия, Израиль, Япония и т.д., (2011, Гармиш-Партенкирхен, Мюнхен, Германия),</li><li>XVI научно-практическая конференция «Комплексная защита информации» (2011, Гродно),</li><li>Участие в организации ежегодной конференции «Международная безопасность и противодействие терроризму »,</li><li>Участие в организации ежегодного Международного форума « Software Engineering Forum »,</li><li>Международная научно-практическая конференция «Современные технологии автоматической идентификации и электронного бизнеса. Состояние и перспективы развития» ID COMPETENCE 2011,</li><li>Международная научно-практическая конференция «Развитие сетей трансфера технологий для инноваций», СТТИ-2013,</li><li>ХII&nbsp;Международная конференция «Развитие информатизации и государственной системы научно-технической информации&nbsp;(РИНТИ-2013)»,</li><li>Республиканская научная конференция студентов и аспирантов Республики Беларусь «НИРС-2011»,</li><li>Ежегодная научная конференция студентов и аспирантов БГУ.</li></ul>', '2015-04-11 16:37:54', null, null, true, null);
INSERT INTO sed_content (id, name, content, update_date, content_type, creator, static, role) VALUES (4, 'Информация кафедры', '<span><b>Дата образования</b>:&nbsp;февраль 1991 г.<br></span><span><b>Преподавательский состав</b>:&nbsp;1 профессор,&nbsp;6 доцентов,&nbsp;4 старших преподавателя,&nbsp;7 ассистентов и 1 ведущий лаборант.<br></span><span><b>Заведующий кафедрой</b>&nbsp;—&nbsp;<a href="http://sed-fpmi.mycloud.by/people/bsu1" target="_blank" rel="">КУРБАЦКИЙ Александр Николаевич</a>, профессор, доктор технических наук, Заслуженный деятель науки Республики Беларусь.<br><br></span><img alt="" src="http://fpmi.bsu.by/sm_full.aspx?guid=9553"><span><br><br></span><span>Пр. Независимости, 4. Главный корпус, к. 228,230, тел. (+375-17) 209-5273&nbsp;<br><span><span><b>Е-mail</b>:</span>&nbsp;<a target="" rel="">kurb@unibel.by<br></a></span></span><span>До октября 2010 года кафедра именовалась кафедрой технологии программирования.<br><br></span><span><br></span>', '2015-04-11 16:39:53', null, null, true, null);
INSERT INTO sed_content (id, name, content, update_date, content_type, creator, static, role) VALUES (5, '', '<span>Эксперты из Международной организации труда (МОТ)&nbsp;<a href="http://www.dw.de/%D0%BC%D0%B5%D0%B6%D0%B4%D1%83%D0%BD%D0%B0%D1%80%D0%BE%D0%B4%D0%BD%D0%B0%D1%8F-%D0%BE%D1%80%D0%B3%D0%B0%D0%BD%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D1%8F-%D1%82%D1%80%D1%83%D0%B4%D0%B0-%D0%BF%D1%80%D0%BE%D0%B3%D0%BD%D0%BE%D0%B7%D0%B8%D1%80%D1%83%D0%B5%D1%82-%D1%80%D0%BE%D1%81%D1%82-%D0%B1%D0%B5%D0%B7%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%B8%D1%86%D1%8B-%D0%B2-%D0%BC%D0%B8%D1%80%D0%B5/a-18201421" target="_blank" rel="">прогнозируют</a>&nbsp;рост безработицы во всем мире. К 2019-му году хранить трудовую книжку дома будут около 212 миллионов человек. Сейчас таких около 201 миллиона. Меньше чем через пятилетку ряды безработных пополнит население такой страны, как Беларусь. FINANCE.TUT.BY проанализировал данные мирового рынка труда и посмотрел, какое место в нем занимает наша страна.</span><br><br>МОТ приводит данные по безработным более чем в 130 странах мира. Конечно, если использовать только официальную статистику, то Беларусь все еще оставалась на вершине рейтинга. Напомним, по статистике, безработица пока не превысила 1 процент от экономически активного населения. Если же смотреть на данные обследования домашних хозяйств, то цифра получается больше - 5,1 процента. По мнению экспертов бизнес-школы ИПМ, эта цифра является "естественной" для нашей страны. В итоге по количеству безработных Беларусь оказалась на одном уровне с Молдовой.&nbsp;<br><span><br><br></span><a href="http://www.fpmi.bsu.by/ru/main.aspx?guid=34621" target="" rel=""><i>Другие сайты факультета</i></a><i>&nbsp;</i><a href="http://www.fpmi.bsu.by/ru/main.aspx?guid=18751" target="" rel=""><i>Структура</i></a><i>&nbsp;</i><a href="http://www.fpmi.bsu.by/ru/main.aspx?guid=6091" target="" rel=""><i>Образование</i></a><i>&nbsp;</i><a href="http://www.fpmi.bsu.by/ru/main.aspx?guid=19111" target="" rel=""><i>Наука</i></a><i>&nbsp;</i><a href="http://www.fpmi.bsu.by/ru/main.aspx?guid=6121" target="" rel=""><i>Международное сотрудничество</i></a><i>&nbsp;</i><a href="http://www.fpmi.bsu.by/ru/main.aspx?guid=19491" target="" rel=""><i>Студенту</i></a><i>&nbsp;</i><a href="http://www.fpmi.bsu.by/ru/main.aspx?guid=1081" target="" rel=""><i>НИРСА</i></a><i>&nbsp;</i><a href="http://www.fpmi.bsu.by/ru/main.aspx?guid=1131" target="" rel=""><i>Совет молодых ученых</i></a><i>&nbsp;<br></i><a href="http://www.fpmi.bsu.by/ru/main.aspx?guid=2251" target="" rel=""><i>Олимпиады</i></a><a href="http://www.fpmi.bsu.by/ru/main.aspx?guid=20601" target="" rel=""><i>Абитуриенту и школьнику</i></a><i>&nbsp;</i><a href="http://www.fpmi.bsu.by/ru/main.aspx?guid=20621" target="" rel=""><i>Центр&nbsp;Компетенций&nbsp;по ИТ</i></a><i>&nbsp;</i><a href="http://www.fpmi.bsu.by/ru/main.aspx?guid=20631" target="" rel=""><i>История</i></a><i>&nbsp;</i><a href="http://www.fpmi.bsu.by/ru/main.aspx?guid=20641" target="" rel=""><i>Издания факультета</i></a><i>&nbsp;</i><a href="http://www.fpmi.bsu.by/ru/main.aspx?guid=20651" target="" rel=""><i>Профбюро ФПМИ</i></a><i>&nbsp;</i><a href="http://www.fpmi.bsu.by/ru/main.aspx?guid=20661" target="" rel=""><i>Персональные страницы</i></a><i>&nbsp;</i><a href="http://www.fpmi.bsu.by/ru/main.aspx?guid=20901" target="" rel=""><i>Фотогалереи&nbsp;</i></a><i>&nbsp;</i><a href="http://www.fpmi.bsu.by/ru/main.aspx?guid=22521" target="" rel=""><i>Газета ФПМы</i></a><i>&nbsp;</i><i><a href="http://www.fpmi.bsu.by/ru/main.aspx?guid=34261" target="" rel="">Наши партнеры</a><br></i><br><div><br><a href="http://www.president.gov.by/" target="_blank" rel=""><img width="147" alt="president" src="http://www.chemistry.bsu.by/images/link/president.gif" height="54"></a>&nbsp; &nbsp;<a href="http://edu.gov.by/" target="_blank" rel=""><img width="148" alt="miedu" src="http://www.chemistry.bsu.by/images/link/miedu.png" height="54"></a>&nbsp;&nbsp;<a href="http://www.elib.bsu.by/" target="_blank" rel=""><img width="152" alt="elibbsu" src="http://www.chemistry.bsu.by/images/link/elib.bsu.jpg" height="53"></a>&nbsp;&nbsp;&nbsp; &nbsp;<a href="http://www.bsu.by/" target="_blank" rel=""><img width="142" alt="bsu" src="http://www.chemistry.bsu.by/images/link/bsu.png" height="52"></a>&nbsp; &nbsp;<a href="http://www.abiturient.by/" target="_blank" rel=""><img width="144" alt="abiturient" src="http://www.chemistry.bsu.by/images/link/abiturient.jpg" height="50"></a>&nbsp;&nbsp;&nbsp;<a href="http://research.bsu.by/" target="_blank" rel=""><img width="144" alt="" src="http://www.chemistry.bsu.by/images/Temp/banner-gun-rus.gif" height="50"></a>&nbsp; &nbsp;<a href="http://www.president.gov.by/" target="_blank" rel=""><img width="147" alt="president" src="http://www.chemistry.bsu.by/images/link/president.gif" height="54"></a><br></div>', '2015-04-21 18:25:43', 'text/html', null, true, null);
INSERT INTO sed_content (id, name, content, update_date, content_type, creator, static, role) VALUES (8, 'Биография', '<b>Доцент кафедры технологий программирования ФПМИ, кандидат технических наук</b><br><br><b>Краткая биография</b><ul><li><div>Родилась в Минске.</div></li><li><div>Закончила факультет прикладной математики и информатики Белорусского государственного университета.</div></li><li><div>После окончания обучения в Белорусском государственном университете закончила аспирантуру.</div></li><li><div>В настоящее время работает доцентом на кафедре технологий программирования факультета прикладной математики и информатики, читает курс по программированию, ведет практические занятия по программированию, операционным системам, руководит курсовыми и дипломными работами, участвует в работе государственной экзаменационной комиссии.</div></li></ul><b>Преподаваемые дисциплины</b><ul><li><div>Программирование</div></li><li><div>Операционные системы</div></li></ul><b>Педагогическая работа</b><ul><li>Программирование на Java (лекции, лабораторные занятия).</li><li>Операционные системы (лабораторные занятия).</li><li>Программирование на С/С++, Assembler.</li><li>Учебная практика (лабораторные занятия).</li><li>Разработка Java-приложений (лекции, лабораторные занятия).</li><li>Клиент-серверные приложения на платформе Java (лекции, лабораторные занятия).</li><li>Основы информационных технологий (лабораторные занятия)</li></ul>', '2015-04-11 16:39:13', 'text/html', null, false, null);
INSERT INTO sed_content (id, name, content, update_date, content_type, creator, static, role) VALUES (9, 'Научная работа', '<b>Учебно-методические работы</b><ol><li><div>Василенко Ж.В. Основы языка С#. Учебно-методическое пособие. – Минск: БГУ, 2008. – 50 с.</div></li><li><div>Василенко Ж.В. Разработка приложений на платформе .NET. Учебно-методическое пособие. – Минск: БГУ, 2008. – 64 с.</div></li></ol><b>Статьи в научных журналах</b><ol><li><div>Василенко Ж.В., Труш Н.Н. Исследование скорости сходимости первого момента оценки спектральной плотности с полиномиальным окном данных. // Информатизация образования. – 2003. – № 4. С. 88-91.</div></li><li><div>Василенко Ж.В., Труш Н.Н. Некоторые свойства полиномиальных оценок спектральной плотности. // Вестник БГУ. Сер.1: Физ. Мат. Мех. – 2004. – № 3. – С. 108-110.</div></li><li><div>Василенко Ж.В. Программное обеспечение по статистическому анализу данных. Методология сравнительного анализа. // Информатизация образования. 2004. – № 3. – С. 60-64.</div></li><li><div>Василенко Ж. В., Труш Н. Н', '2015-04-11 16:39:24', 'text/html', null, false, null);
INSERT INTO sed_content (id, name, content, update_date, content_type, creator, static, role) VALUES (10, 'Статьи в электронной библиотеке', '<ol><li><div><a href="http://elib.bsu.by/handle/123456789/10132" target="_blank" rel="">Note on Brain image asymmetry analysis</a>&nbsp;// Международный конгресс по информатике: информационные системы и технологии: материалы международного научного конгресса 31 окт. – 3 нояб. 2011 г. : в 2 ч. Ч. 2. – Минск: БГУ, 2011. – C. 258-260.</div></li><li><div><a href="http://www.elib.bsu.by/handle/123456789/3812" target="_blank" rel="" title="Link: http://www.elib.bsu.by/handle/123456789/3812">Информатизация обучения математике и информатике: педагогические аспекты</a>. 2006</div></li><li><div><a href="http://www.elib.bsu.by/handle/123456789/6459" target="_blank" rel="">Исследование подходов, методов и инструментальх средств проектирования и разработки информационно-аналитических систем с использованием семантических сетей</a>: отчет о НИР(заключительный)/БГУ; рук. Курбацкий А. Н.. - Минск,2010. - № ГР 20062646</div></li><li><div><a href="http://www.elib.bsu.by/handle/123456789/1684" target="_blank" rel="">Основы языка С#.</a>&nbsp;пособие для студентов спец. 1-31 03 03 «Прикладная математика (по направлениям)», 1-31 03 04 «Информатика», 1-31 03 05 «Актуарная математика», 1-31 03 06 «Экономическая ки- бернетика (по направлениям)».</div></li><li><div><a href="http://www.elib.bsu.by/handle/123456789/1543" target="_blank" rel="">Разработка приложений на платформе.NET.</a>&nbsp;учеб.-метод. пособие для студентов спец. 1-31 03 03 «Прикладная математика (по направлениям)», 1-31 03 04 «Информатика», 1-31 03 05 «Актуарная математика», 1-31 03 06 «Экономическая кибернетика (по направлениям)»</div></li></ol>', '2015-04-11 16:39:44', 'text/html', null, false, null);
INSERT INTO sed_content (id, name, content, update_date, content_type, creator, static, role) VALUES (17, 'Биография', '<b>Доцент кафедры технологий программирования ФПМИ, кандидат технических наук</b><br><br><b>Краткая биография</b><ul><li><div>Родился в 1954 году в Гродненской области.</div></li><li><div>1971-1976 г.г. – студент математико-механического факультета Ленинградского государственного университета.</div></li><li><div>1976-1983 г.г. – аспирант, инженер, младший научный сотрудник Кольского филиала АН СССР.</div></li><li><div>1983-1989 г.г. – старший преподаватель, доцент Гродненского университета.</div></li><li><div>С 1989 г. – доцент ФПМИ.</div></li><li><div>Кандидат технических наук (1982 г., г. Москва), доцент (1988 г.).</div></li></ul><b>Профессиональные интересы</b><ul><li><div>Распределенные системы и Web- программирование</div></li><li><div>Геоинформатика</div></li><li><div>Методические проблемы преподавания информатики</div></li></ul><b>Преподаваемые дисциплины</b><ul><li><div>Основы информационных технологий</div></li><li><div>Компьютерный анализ данных</div></li><li><div>Программное обеспечение коммуникационных технологий</div></li><li><div>Технологии разработки и защиты Web -приложений и Web –служб</div></li><li><div>Технологии ASP.NET разработки и защиты Web -приложений</div></li></ul>', '2015-02-22 15:06:47', 'text/html', null, false, null);
INSERT INTO sed_content (id, name, content, update_date, content_type, creator, static, role) VALUES (18, 'Основные публикации', 'V Междунар. конф.-форума (Минск, 16-17 нояб. 2009 г.). Ч.1. - Минск: А. Н. Вараксин, 2009. - с. 90-91.</div></li></ol>', '2015-02-22 15:07:13', 'text/html', null, false, null);
INSERT INTO sed_content (id, name, content, update_date, content_type, creator, static, role) VALUES (19, 'Биография', '<b>Старший преподаватель кафедры технологий программирования ФПМИ<br><br>Краткая биография</b><ul><li><div>Окончила факультет прикладной математики.</div></li><li><div>До 1999 года работала старшим преподавателем на кафедре компьютерных технологий и сетей (бывшей кафедре ИПМОАП).</div></li><li><div>В настоящее время работает на этом же факультете старшим преподавателем кафедры "Технологии программирования".</div></li></ul><b>Область научных интересов</b><ul><li><div>Информационные технологии программирования.</div></li><li><div>СУБД</div></li><li><div>ORACLE</div></li><li><div>Access</div></li></ul><b>Научная работа</b><ul><li><div>Теория и технология программирования,</div></li><li><div>Разработка математического и программного обеспечения информационных систем управления.</div></li></ul><b>Учебная работа</b><br><b>Проведение занятий на факультете прикладной математики по курсу</b><ul><li><div>«Программирование»</div></li><li><div>«Модели данных и СУБД»</div></li><li><div>«Операционные системы»</div></li></ul><b>Чтением специальных курсов касающихся разработки и реализации информационных систем управления.Начиная с 2004 года читает лекции и проводит практические занятия по следующим курсам на гуманитарном факультете Белгосуниверситета, а именно:</b><ul><li><div>«Программирование»,</div></li><li><div>«Архитектура вычислительных систем»,</div></li><li><div>«Операционные системы».</div></li></ul>По курсам «Модели данных и СУБД», «Программирование», «Архитектура вычислительных систем» подготовлены и изданы учебные пособия.<span>Активно участвует в общественной жизни факультета, неоднократно награждалась за достигнутые успехи в научно-педагогической деятельности почетными грамотами Белгосуниверситета.</span>', '2015-02-22 15:10:03', 'text/html', null, false, null);
INSERT INTO sed_content (id, name, content, update_date, content_type, creator, static, role) VALUES (20, 'Основные публикации', '<ol><li><div><a href="http://www.fpmi.bsu.by/main.aspx?guid=25861" target="_blank" rel="" title="Link: http://www.fpmi.bsu.by/main.aspx?guid=25861">Бондаренко С.П., Исаченко А.Н. Лабораторный практикум по курсу "Модели данных и СУБД".</a>&nbsp;– Минск: БГУ, 2005 г. – 104с.</div></li><li><div>Бондаренко С.П Математические модели и алгоритмы MES-системы.&nbsp;В сб. "Информационные системы и технологии ( IST 2006) " . Материалы III Международной конференции. Ч.2. Минск, 2006. с. 108-113.</div></li><li><div><a href="http://www.fpmi.bsu.by/main.aspx?guid=26681" target="_blank" rel="" title="Link: http://www.fpmi.bsu.by/main.aspx?guid=26681">Бондаренко С.П., Побегайло А.П. Лабораторный практикум по курсу "Программирование" в двух частях.</a>&nbsp;Ч. 1. Язык программирования Си. – Минск: БГУ, 2007 г. – 100с.</div></li><li><div><a href="http://www.fpmi.bsu.by/main.aspx?guid=26671" target="_blank" rel="" title="Link: http://www.fpmi.bsu.by/main.aspx?guid=26671">Бондаренко С.П., Исаченко А.Н. Модели данных и системы управления базами данных.</a>&nbsp;– Минск: БГУ, 2007 г. – 220с.</div></li><li><div>Бондаренко С.П., Жмура С. Г. Оценка и контроль риска кредитования физических лиц с использованием технологии Data Mining . В сб. "Информационные системы и технологии ( IST 2008) " . Материалы IY Международной конференции. – Минск, 2008. с. 282-287.</div></li><li><div><a href="http://www.fpmi.bsu.by/main.aspx?guid=26691" target="_blank" rel="" title="Link: http://www.fpmi.bsu.by/main.aspx?guid=26691">Бондаренко С.П., Побегайло А.П. Лабораторный практикум по курсу "Программирование" в двух частях.</a>&nbsp;Ч. 2. Язык программирования С++. – Минск: БГУ, 2008 г. – 70с.</div></li><li><div><a href="http://www.fpmi.bsu.by/main.aspx?guid=27671" target="_blank" rel="" title="Link: http://www.fpmi.bsu.by/main.aspx?guid=27671">Бондаренко С.П., Акинфина М.А. Архитектурные решения современных компьютеров:</a>&nbsp;учеб.метод.пособие для студентов гуманит.фак./ С.П. Бондаренко, М.А. Акинфина. – Минск: БГУ, 2010. – 52с.</div></li><li><div><a href="http://www.fpmi.bsu.by/main.aspx?guid=27681" target="_blank" rel="" title="Link: http://www.fpmi.bsu.by/main.aspx?guid=27681">Бондаренко С.П., Акинфина М .А. Многопроцессорные вычислительные системы</a>: учеб.метод.пособие для студентов гуманит.фак./ М.А. Акинфина, С.П. Бондаренко. – Минск: БГУ, 2010. – 51с.</div></li><li><div>Бондаренко С.П., Акинфина М.А. Система контроля съемных носителей информации и устройств ввода-вывода АРМ / Информационные системы и технологии ( IST ’2010): Материалы VI Международной конференции, Минск, 24-25 ноября 2010 г. / БГУ. – Минск, 2010. – С. 324-327.</div></li><li><div>Бондаренко С.П., Акинфина М.А., Ткалич Т.А. Система анализа и выбора методологии разработки программного обеспечения / Информационные системы и технологии ( IST ’2010): Материалы VI Международной конференции, Минск, 24-25 ноября 2010 г. / БГУ. – Минск, 2010. – С. 257-260. &nbsp;</div></li></ol>', '2015-02-22 15:10:29', 'text/html', null, false, null);
INSERT INTO sed_content (id, name, content, update_date, content_type, creator, static, role) VALUES (21, 'Биография', 'Старший преподаватель кафедры технологий программирования ФПМИ<br><br><b>Область научных интересов:</b><ul><li>Методика преподавания информатики и программирования</li></ul><span>Ст. преподаватель&nbsp;Пазюра Е.В.&nbsp;награждалась Грамотой Министерства образования, Грамотой Комитета образования Мингорисполкома, Почетной грамотой БГУ.</span>', '2015-02-22 15:12:40', 'text/html', null, false, null);
INSERT INTO sed_content (id, name, content, update_date, content_type, creator, static, role) VALUES (22, 'Биография', 'Старший преподаватель&nbsp; кафедры технологий программирования ФПМИ<br><br><b>Область научных интересов:</b><ul><li>Использование систем программирования в учебном процессе</li></ul><span>Ст. преподаватель&nbsp;Карпович Н.А.&nbsp;награждалась Почетной грамотой БГУ, Грамотой ГОРОНО, Лауреат 3-ей премии специального Фонда Президента РБ по социальной поддержке одаренных учащихся.</span>', '2015-02-22 15:14:27', 'text/html', null, false, null);
INSERT INTO sed_content (id, name, content, update_date, content_type, creator, static, role) VALUES (21743, '', null, '2015-04-21 14:26:51', null, null, false, null);
INSERT INTO sed_content (id, name, content, update_date, content_type, creator, static, role) VALUES (21745, '123', 123, '2015-04-21 14:29:39', null, null, false, null);

#------------------------------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS sed_document;
CREATE TABLE IF NOT EXISTS sed_document (
  id           BIGINT       NOT NULL AUTO_INCREMENT,
  name         VARCHAR(200) NOT NULL,
  `path`       VARCHAR(300) NOT NULL,
  created_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  content_type VARCHAR(100),
  role         VARCHAR(20),
  category     VARCHAR(50)  NOT NULL,
  creator      BIGINT       NULL     DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (creator) REFERENCES sed_user (id)
    ON DELETE SET NULL,
  INDEX doc_name(name)
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;

INSERT INTO sed_document (id, name, path, created_date, content_type, category, creator, role) VALUES (2071, 'no_photo_available-1429620911579.jpg', '2015/04/21/no_photo_available-1429620911579.jpg', '2015-04-21 15:55:11', 'image/jpeg', 'DOC', 2, null);
INSERT INTO sed_document (id, name, path, created_date, content_type, category, creator, role) VALUES (2072, 'diagram (2).png', '2015/04/21/diagram (2).png', '2015-04-21 19:09:02', 'image/png', 'DOC', 2, null);
INSERT INTO sed_document (id, name, path, created_date, content_type, category, creator, role) VALUES (2073, 'cutmypic.png', '2015/04/21/cutmypic.png', '2015-04-21 19:11:34', 'image/png', 'DOC', 2, null);
INSERT INTO sed_document (id, name, path, created_date, content_type, category, creator, role) VALUES (2074, 'imgo.jpg', '2015/04/21/imgo.jpg', '2015-04-21 19:11:56', 'image/jpeg', 'DOC', 2, null);
INSERT INTO sed_document (id, name, path, created_date, content_type, category, creator, role) VALUES (2075, 'pulse.png', '2015/04/21/pulse.png', '2015-04-21 19:12:17', 'image/png', 'DOC', 2, null);
INSERT INTO sed_document (id, name, path, created_date, content_type, category, creator, role) VALUES (2077, '47273.jpg', '2015/04/21/47273.jpg', '2015-04-21 19:13:33', 'image/jpeg', 'DOC', 2, null);

#------------------------------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS sed_gallery;
CREATE TABLE IF NOT EXISTS sed_gallery (
  id          BIGINT NOT NULL AUTO_INCREMENT,
  description VARCHAR(300),
  document_id BIGINT,
  PRIMARY KEY (id),
  FOREIGN KEY (document_id) REFERENCES sed_document (id) ON DELETE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;

INSERT INTO sed_gallery (id, description, document_id) VALUES (2, 'Погода в Минске для вашего сайта за пару минут. Точный прогноз погоды в Беларуси и по всему миру от Гисметео.', 2071);
INSERT INTO sed_gallery (id, description, document_id) VALUES (3, null, 2072);
INSERT INTO sed_gallery (id, description, document_id) VALUES (4, null, 2073);
INSERT INTO sed_gallery (id, description, document_id) VALUES (5, null, 2074);
INSERT INTO sed_gallery (id, description, document_id) VALUES (6, null, 2075);
INSERT INTO sed_gallery (id, description, document_id) VALUES (8, null, 2077);

#------------------------------------------------------------------------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS sed_news;
SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE IF NOT EXISTS sed_news (
  id           BIGINT    NOT NULL AUTO_INCREMENT,
  creator      BIGINT,
  content      BIGINT,
  photo        VARCHAR(2000),
  simpleText   VARCHAR(3000),
  category     VARCHAR(70),
  fixed        BIT                DEFAULT FALSE,
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  PRIMARY KEY (id),
  FOREIGN KEY (creator) REFERENCES sed_user (id) ON DELETE SET NULL ,
  FOREIGN KEY (content) REFERENCES sed_content (id)
    ON DELETE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET =utf8;

#------------------------------------------------------------------------------------------------------------------------------------------

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

#------------------------------------------------------------------------------------------------------------------------------------------


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

INSERT INTO sed_people_content (people_id, content_id) VALUES (3, 8);
INSERT INTO sed_people_content (people_id, content_id) VALUES (3, 9);
INSERT INTO sed_people_content (people_id, content_id) VALUES (3, 10);
INSERT INTO sed_people_content (people_id, content_id) VALUES (4, 17);
INSERT INTO sed_people_content (people_id, content_id) VALUES (4, 18);
INSERT INTO sed_people_content (people_id, content_id) VALUES (5, 19);
INSERT INTO sed_people_content (people_id, content_id) VALUES (5, 20);
INSERT INTO sed_people_content (people_id, content_id) VALUES (6, 21);
INSERT INTO sed_people_content (people_id, content_id) VALUES (7, 22);

#------------------------------------------------------------------------------------------------------------------------------------------