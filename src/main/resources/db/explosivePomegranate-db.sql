
DROP DATABASE IF EXISTS library;

CREATE DATABASE library;
USE library;

CREATE TABLE book(
                     book_id INT AUTO_INCREMENT PRIMARY KEY,
                     isbn VARCHAR (17),
                     title VARCHAR (300),
                     description VARCHAR (6000),
                     year INT (4),
                     image BLOB,
                     currently_borrowed BOOLEAN
);

CREATE TABLE author(
                       author_id INT AUTO_INCREMENT PRIMARY KEY,
                       author_firstname VARCHAR (50),
                       author_lastname VARCHAR (30)
);

CREATE TABLE book_author(
                            book_id INT,
                            author_id INT,
                            FOREIGN KEY (book_id) REFERENCES book(book_id),
                            FOREIGN KEY (author_id) REFERENCES author(author_id)
);

CREATE TABLE category(
                         category_id INT AUTO_INCREMENT PRIMARY KEY,
                         category_name VARCHAR (50)
);

CREATE TABLE book_category(
                              book_id INT,
                              category_id INT,
                              FOREIGN KEY (book_id) REFERENCES book(book_id),
                              FOREIGN KEY (category_id) REFERENCES category(category_id)
);

CREATE TABLE role (
                      role_id Int AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(100)
);

CREATE TABLE user (
                      user_id INT AUTO_INCREMENT PRIMARY KEY,
                      firstname VARCHAR(30),
                      lastname VARCHAR(30),
                      email VARCHAR(50),
                      role_id INT,
                      FOREIGN KEY (role_id) REFERENCES role(role_id)
);

CREATE TABLE borrowed(
                         borrow_id INT AUTO_INCREMENT PRIMARY KEY,
                         user_id INT ,
                         book_id INT,
                         start_date DATE,
                         init_end_date DATE,
                         book_comment VARCHAR (600),
                         FOREIGN KEY (user_id) REFERENCES user(user_id),
                         FOREIGN KEY (book_id) REFERENCES book(book_id)
);

CREATE TABLE login (
                       user_id INT PRIMARY KEY,
                       password VARCHAR(200),
                       FOREIGN KEY (user_id) REFERENCES  user(user_id)
);

DROP USER IF EXISTS 'Pomegranate'@'localhost';
CREATE USER 'Pomegranate'@'localhost' IDENTIFIED BY 'ExplosivePW2021';
USE library;
GRANT ALL PRIVILEGES ON library.* TO 'Pomegranate'@'localhost';
GRANT FILE ON *.* TO 'Pomegranate'@'localhost';

/* Fill db with test data */
/* add content to book, done by Clelia*/
INSERT INTO library.book VALUES (1,'978-3-406-77346-4', 'World in Lockdown', 'Adam Tooze s stunning book tells the story of the twelve months from January 2020 to January 2021. At the beginning, Xi Jinping announces to the world that a deadly new virus is spreading in China. At the end, Joe Biden moves into the White House as Donald Trump''s successor. In between are the shockwaves of a pandemic that leaves no continent, country or population unscathed.
The brilliant economic historian Tooze not only describes how and why states and national economies responded to the event, each in their own way and with very different results. He also analyzes the pandemic in the context of the other major crises of our time, from the financial crisis to the climate crisis to the refugee crisis.
World in Lockdown is a profound diagnosis of the present and a book from which one can learn how the globalized world in which we live today works.'
, 2021, null,false);
INSERT INTO library.book VALUES(2,'978-3-423-34048-9', 'The laws of the winner','Success and a fulfilled life - we all have these goals. But often we settle for too little. Our visions, our dreams are overshadowed by obligations, frustrations or everyday business. This is exactly where Bodo Schäfer''s coaching comes in. He has developed and tested 30 easy-to-follow strategies that promote professional and personal success. Each law is explained in detail and contains a practical part with tasks and exercises, which the reader can try out and convert immediately. In this way, you can see day by day how your own behavior and view of the world change within just one month.'
,2003, null,false);
INSERT INTO library.book VALUES(3,'978-3-527-71800-9', 'Accounting compressed for dummies','The authors familiarize you with accounting step by step and in an easy-to-understand manner. From clarification of the terms, the tasks and the legal basics to accounting preparation and the first posting record to postings in practice. Book the purchase and sale of goods and services, book wages, salaries and social security, down payments, loans and receivables as well as machinery, equipment and not to forget taxes. Thus, accounting does not remain a book with seven seals.
',2021, null,false);
INSERT INTO library.book VALUES(4,'978-1-78941-388-5', 'MINT- Knowledge winns! I know more! Everything about Computer', 'What are all the things in a computer? how does it work? what can you do with it? over 100 windows to open the secrets around the topic computer, programming and the internet', 2020, null,false);
INSERT INTO library.book VALUES(5,'978-3-8362-7737-2', 'Java is also an island', 'The "Island" is the first choice when it comes to up-to-date and practical Java knowledge. Java beginners, students and switchers will benefit from this textbook. In addition to the treatment of the language basics of Java, there are compact introductions to special topics. You''ll learn about threads, algorithms, GUIs, files, and data streams. This book belongs on the shelf of every Java programmer. It is currently available in the 15th edition.',2020, null,false);
INSERT INTO library.book VALUES(6,'978-0-19-884480-8', 'Accounting: A smart approach', 'An ideal companion to accountancy modules for business and management students, with a unique running case study throughout to help students visualize the practical applications of accountancy and demystify abstract concepts.', 2020, null,false);
INSERT INTO library.book VALUES(7,'978-0-7148-7226-1','The history of art','One of the most famous and popular books ever written about art; now appears in a high-quality, handy edition. Lovingly designed in high quality: Printed on Bible paper, with two ribbons for reading and wrapped in tissue paper. The clear design of this Small Edition appeals to students as well as book lovers and anyone who wants to give a classic as a gift.', 2016, null,false);
INSERT INTO library.book VALUES(8,'978-3-7774-3702-6', 'The collection Emil Bührle', 'Paul Cézanne, Auguste Renoir, Claude Monet and many more - the Swiss industrialist Emil Bührle (1890-1956) built up an impressive collection around French Impressionism between 1936 and 1956. As the owner of the country''s largest arms factory, he was closely linked to the historical events of the period from World War II to the Cold War.At first, Emil Bührle acquired works almost exclusively in Switzerland; from 1951 onward, an intensive second phase followed, which was strongly influenced by Bührle''s business involvement in the United States. This volume illuminates the eventful history of the collection, which comprises a total of 633 works, and examines its significance in relation to modern art collections in Germany, Switzerland, and the United States. The show is complemented by contributions from various authors who - from Old Masters to Picasso - present 70 masterpieces from the collection.',2021, null,false);
INSERT INTO library.book VALUES(9,'978-3-658-16194-1', 'Mathematical formulary', 'This collection of formulas is adapted to the three-volume textbook system and enables rapid access to the desired information through a very detailed table of contents and subject index. All important data are clarified by formulas. Calculation examples show how to apply the formulas accurately to one''s own problems. Many tables on Laplace transformations, probability/statistics, and a detailed integral table provide reliable help. In the current edition, examples (curve discussion) have been added and the text has been made more comprehensible through text inserts.',2017,null,false);
INSERT INTO library.book VALUES(10, '978-3-8007-5072-6', 'Pocket book for ventilation fitters and foremen','For the most important sub-areas of ventilation and air-conditioning technology, both the necessary theoretical expertise and the required work skills are explained in the usual clear and detailed manner.
The latest technical requirements are taken into account in the areas of fire protection and hygiene of ventilation and air-conditioning systems. Consideration of energy-efficient construction and operation methods that meet the modern requirements of climate protection and energy conservation also occupies a large space. The inclusion of simple calculation tasks at the end of the book makes it possible to determine important boundary and design values by means of quick and rough calculations on the construction site or at the customer''s or company''s premises.
The 8th, revised and expanded edition has been adapted to the applicable as well as the changed and newly created standards, regulations and guidelines. The topic "school ventilation" has been newly included.', 2020, null ,false);
INSERT INTO library.book VALUES(11, '978-3-903861-81-7', 'The CO2 syndrome and its alternative-
The true cause of climate change, the failure of the experts, the connection with the Corona pandemic and the chance for a way out', 'The primary purpose of this book is to point out the shortcomings of the commonly held greenhouse doctrine. It assumes that the increased CO2 content of the air caused by the combustion of fossil fuels such as coal or oil is responsible for climate change. However, as the scientific investigations of the author - a chemist with a doctorate - have shown, this is not true. Instead, the true cause lies in the darkening of the earth''s surface, especially roofs and facades. This realization has so far met with ignorance from the experts as well as from the public opinion dependent on it up to the "climate youth". Actually, an immediate stop of the present climate policy and beyond that a paradigm shift would be necessary.',2021,null,false);
INSERT INTO library.book VALUES(12, '978-3-7448-2175-9','The Communist Manifesto | Communist Party Manifesto','The Communist Manifesto | Newly edited edition 2019 || The Communist Manifesto is a writing like a beacon, every sentence a hit. An accurate description of the conditions of the time, a theoretical instruction for action to re-found a society that is stirring in its boldness. The word "theoretical," however, cannot be overemphasized. For history has shown that implementation - for a variety of reasons - fails. || The bourgeoisie, that was at that time, in Marx''s and Engels''s times, the class, which by means of an above-average basic financial endowment, thanks to the resulting above-average education and training, eagerness to travel, multilingualism and an interwoven system of cords created the conditions to accumulate more and more capital, to become capitalists in Marx''s and Engels''s sense, who as owners of the means of production let others work for them. This class still exists, of course, but the word bourgeoisie has become old-fashioned and is no longer used. There is another apt word for this subset of society: globalization winners. - The topicality of the Communist Manifesto jumps out at you when you read it.',2021,null,false);
INSERT INTO library.book VALUES(13,'978-3-8468-7100-3','The art of war', 'Probably the oldest and best book on military strategy, it has served as a source of inspiration for many famous commanders. It is also a highly interesting read today, whose statements radiate to many areas of life.',2017, null,false);
INSERT INTO library.book VALUES(14, '978-3-0340-1594-3', 'Paradox Switzerland -
An outside view of their history', 'No English-language history of Switzerland has appeared since the 1950s. In 2013, Cambridge University Press published a Concise History of Switzerland. The work now appears updated for a German-speaking readership.
The authors deal with both historical events and the changing images of history over the eras. The starting point is the changes in Europe that allowed communities to flourish and form alliances after 1200. Many of these alliances disappeared again, but in the Alpine region some endured and developed into the Confederation. The authors take the account of their history right up to the present day. They paint a nuanced picture of Switzerland''s strengths and weaknesses and conclude with the conviction that the country will continue to play a special role in a changing Europe.', 2021, null,false);

/* add content to author, done by Clelia*/
INSERT INTO library.author VALUES(1,'Adam', 'Tooze');
INSERT INTO library.author VALUES(2,'Bodo', 'Schaefer');
INSERT INTO library.author VALUES(3,'Michael', 'Griga');
INSERT INTO library.author VALUES(4,'Raymund', 'Krauleidis');
INSERT INTO library.author VALUES(5,'Rosie', 'Dickins');
INSERT INTO library.author VALUES(6,'Christian', 'Ullenboom');
INSERT INTO library.author VALUES(7,'Mary', 'Carey');
INSERT INTO library.author VALUES(8,'Cathy', 'Knowles');
INSERT INTO library.author VALUES(9,'EH', 'Gombrich');
INSERT INTO library.author VALUES(10,'Lukas', 'Gloor');
INSERT INTO library.author VALUES(11, 'Lothar', 'Papula');
INSERT INTO library.author VALUES(12, 'Nicolas', 'Fritzsche');
INSERT INTO library.author VALUES(13, 'Thomas','Allmendinger');
INSERT INTO library.author VALUES(14, 'Karl','Marx');
INSERT INTO library.author VALUES(15,'Friedrich','Engels');
INSERT INTO library.author VALUES(16,'Sun','Tzu');
INSERT INTO library.author VALUES(17,'Clive H.','Church');
INSERT INTO library.author VALUES(18, 'Randolph C.','Head' );

/* add content to book_author, done by Clelia*/
INSERT INTO library.book_author VALUES(1,1);
INSERT INTO library.book_author VALUES(2,2);
INSERT INTO library.book_author VALUES(3,3);
INSERT INTO library.book_author VALUES(3,4);
INSERT INTO library.book_author VALUES(4,5);
INSERT INTO library.book_author VALUES(5,6);
INSERT INTO library.book_author VALUES(6,7);
INSERT INTO library.book_author VALUES(6,8);
INSERT INTO library.book_author VALUES(7,9);
INSERT INTO library.book_author VALUES(8,10);
INSERT INTO library.book_author VALUES(9,11);
INSERT INTO library.book_author VALUES(10,12);
INSERT INTO library.book_author VALUES(11,13);
INSERT INTO library.book_author VALUES(12,14);
INSERT INTO library.book_author VALUES(12,15);
INSERT INTO library.book_author VALUES(13,16);
INSERT INTO library.book_author VALUES(14,17);
INSERT INTO library.book_author VALUES(14,18);

/* add content to category, done by Clelia*/
INSERT INTO library.category VALUES(1,'Accounting');
INSERT INTO library.category VALUES(2,'Business');
INSERT INTO library.category VALUES(3,'Computer');
INSERT INTO library.category VALUES(4,'Art');
INSERT INTO library.category VALUES(5,'Technic');
INSERT INTO library.category VALUES(6,'Politic');
INSERT INTO library.category VALUES(7,'History');

/* add content to book_category, done by Clelia*/
INSERT INTO library.book_category VALUES(3,1);
INSERT INTO library.book_category VALUES(6,1);
INSERT INTO library.book_category VALUES(1,2);
INSERT INTO library.book_category VALUES(2,2);
INSERT INTO library.book_category VALUES(4,3);
INSERT INTO library.book_category VALUES(5,3);
INSERT INTO library.book_category VALUES(7,4);
INSERT INTO library.book_category VALUES(8,4);
INSERT INTO library.book_category VALUES(9,5);
INSERT INTO library.book_category VALUES(10,5);
INSERT INTO library.book_category VALUES(11,6);
INSERT INTO library.book_category VALUES(12,6);
INSERT INTO library.book_category VALUES(13,7);
INSERT INTO library.book_category VALUES(14,7);

/* add content to role, done by Clelia*/
INSERT INTO library.role VALUES(1, 'ROLE_ADMIN');
INSERT INTO library.role VALUES(2, 'ROLE_USER');

/* add content to user, done by Clelia */
INSERT INTO library.user VALUES(1, 'Sonja','Nussbaumer', 'sonja.nussbaumer@students.fhnw.ch', 1);
INSERT INTO library.user VALUES(2, 'Rebecca','Beutling', 'rebecca.beutling@students.fhnw.ch', 1);
INSERT INTO library.user VALUES(3, 'Salvatore','Trupia', 'salvatore.trupia@students.fhnw.ch', 1);
INSERT INTO library.user VALUES(4, 'Clelia','Meneghin', 'clelia.meneghin@students.fhnw.ch', 1);
INSERT INTO library.user VALUES(5, 'Paul','Hansel', 'paul.hansel@students.fhnw.ch', 2);
INSERT INTO library.user VALUES(6, 'Ursula','Jankiv', 'ursula.jankiv@students.fhnw.ch', 2);
INSERT INTO library.user VALUES(7, 'Andreas','Martin', 'andreas.martin@fhnw.ch', 1);
INSERT INTO library.user VALUES(8, 'Devid','Montecchiari', 'devid.montecchiari@fhnw.ch', 1);
INSERT INTO library.user VALUES(9, 'Charuta','Pande', 'charuta.pande@fhnw.ch', 1);

/* add content to borrowed, done by Clelia */
INSERT INTO library.borrowed VALUES(1,9,5,'2021-01-03','2021-01-30',NULL);
INSERT INTO library.borrowed VALUES(2,8,14,'2021-01-03','2021-01-10','Coffe stain on first page');
INSERT INTO library.borrowed VALUES(3,7,1,'2021-01-15','2021-02-01',NULL);
INSERT INTO library.borrowed VALUES(4,6,2,'2021-02-01','2021-02-20','Comment added with ink on some of the pages');
INSERT INTO library.borrowed VALUES(5,5,3,'2021-03-01','2021-03-05',NULL);
INSERT INTO library.borrowed VALUES(6,4,4,'2021-01-01','2021-03-25',NULL);
INSERT INTO library.borrowed VALUES(7,3,5,'2021-04-01','2021-04-02','Book cover is instable');
INSERT INTO library.borrowed VALUES(8,2,6,'2021-04-25','2021-05-07','someone folded the edged');
INSERT INTO library.borrowed VALUES(9,1,7,'2021-05-07','2021-05-010',NULL);
INSERT INTO library.borrowed VALUES(10,1,8,'2021-05-07','2021-05-19',NULL);
INSERT INTO library.borrowed VALUES(11,2,9,'2021-05-11','2021-06-04','Last page is ripped out- nothing important, all important pages are there');
INSERT INTO library.borrowed VALUES(12,3,10,'2021-06-02','2021-06-29',NULL);
INSERT INTO library.borrowed VALUES(13,4,11,'2021-06-03','2021-06-17',NULL);
INSERT INTO library.borrowed VALUES(14,5,12,'2021-06-14','2021-06-28',NULL);
INSERT INTO library.borrowed VALUES(15,6,13,'2021-06-23','2021-07-12',NULL);
INSERT INTO library.borrowed VALUES(16,7,14,'2021-07-01','2021-07-11',NULL);
INSERT INTO library.borrowed VALUES(17,8,14,'2021-07-02','2021-07-13',NULL);
INSERT INTO library.borrowed VALUES(18,9,13,'2021-07-05','2021-07-30',NULL);
INSERT INTO library.borrowed VALUES(19,9,12,'2021-07-07','2021-07-13','Ink stain in chapter 5');
INSERT INTO library.borrowed VALUES(20,8,11,'2021-08-01','2021-08-17',NULL);
INSERT INTO library.borrowed VALUES(21,7,10,'2021-08-04','2021-08-20',NULL);
INSERT INTO library.borrowed VALUES(22,6,9,' 2021-08-09','2021-08-28',NULL);
INSERT INTO library.borrowed VALUES(23,5,8,'2021-09-01','2021-09-12',NULL);
INSERT INTO library.borrowed VALUES(24,4,7,'2021-09-02','2021-09-13','Someone drew a funny face on the author image');
INSERT INTO library.borrowed VALUES(25,3,6,'2021-09-15','2021-10-01',NULL);
INSERT INTO library.borrowed VALUES(26,2,5,'2021-09-20','2021-10-17','Book cover is damaged');
INSERT INTO library.borrowed VALUES(27,1,4,'2021-10-01','2021-10-22',NULL);
INSERT INTO library.borrowed VALUES(28,1,3,'2021-10-01','2021-10-12',NULL);
INSERT INTO library.borrowed VALUES(29,2,2,'2021-10-22','2021-10-28',NULL);
INSERT INTO library.borrowed VALUES(30,3,1,'2021-10-14','2021-11-05',NULL);

/* add content to login, done by Clelia */
/*Password2021. via Bycrypt round 12 encoder*/
INSERT INTO library.login VALUES(1, '$2a$12$6kCrs9tqszAXQvPxb5CRde199uvHMJaX6E8NO0I5UiV1JlX71A.Ru');
INSERT INTO library.login VALUES(2, '$2a$12$6kCrs9tqszAXQvPxb5CRde199uvHMJaX6E8NO0I5UiV1JlX71A.Ru');
INSERT INTO library.login VALUES(3, '$2a$12$6kCrs9tqszAXQvPxb5CRde199uvHMJaX6E8NO0I5UiV1JlX71A.Ru');
INSERT INTO library.login VALUES(4, '$2a$12$6kCrs9tqszAXQvPxb5CRde199uvHMJaX6E8NO0I5UiV1JlX71A.Ru');
INSERT INTO library.login VALUES(5, '$2a$12$6kCrs9tqszAXQvPxb5CRde199uvHMJaX6E8NO0I5UiV1JlX71A.Ru');
INSERT INTO library.login VALUES(6, '$2a$12$6kCrs9tqszAXQvPxb5CRde199uvHMJaX6E8NO0I5UiV1JlX71A.Ru');
INSERT INTO library.login VALUES(7, '$2a$12$6kCrs9tqszAXQvPxb5CRde199uvHMJaX6E8NO0I5UiV1JlX71A.Ru');
INSERT INTO library.login VALUES(8, '$2a$12$6kCrs9tqszAXQvPxb5CRde199uvHMJaX6E8NO0I5UiV1JlX71A.Ru');
INSERT INTO library.login VALUES(9, '$2a$12$6kCrs9tqszAXQvPxb5CRde199uvHMJaX6E8NO0I5UiV1JlX71A.Ru');
