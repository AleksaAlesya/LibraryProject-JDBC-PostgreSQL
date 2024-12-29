DROP  TABLE Book;
--удалить все данные из таблицы
TRUNCATE TABLE Book;



CREATE TABLE Book(
    id int GENERATED BY DEFAULT AS IDENTITY  PRIMARY KEY ,
    title varchar(200) NOT NULL ,
    author varchar(50),
    year int NOT NULL ,
    person_id int REFERENCES Person (id)  ON DELETE SET NULL
    --customer_id int  REFERENCES Customer (customer_id) ON DELETE SET NULL
);

INSERT INTO Book(title, author, year,person_id) VALUES ('Чистый код', 'Мартин Р',2019,1);
INSERT INTO Book(title, author, year) VALUES ('Чистый код. Создание, анализ и рефакторинг', 'Роберт Мартин',2019);
INSERT INTO Book(title, author, year) VALUES ('Чистая архитектура', 'Роберт Мартин',2021);
INSERT INTO Book(title, author, year) VALUES ('Java Эффективное программирование', 'Джошуа Блох',2019);
INSERT INTO Book(title, author, year) VALUES ('Java8. Полное руководство', 'Герберд Шилдт',2015);
INSERT INTO Book(title, author, year) VALUES ('Грокаем алгоритмы', 'Бхаргава',2017);
INSERT INTO Book(title, author, year) VALUES ('Философия Java 2015', 'Брюс Эккель',2015);
INSERT INTO Book(title, author, year) VALUES ('Программирование на Java для начинающих 2016', 'Грат',2016);
INSERT INTO Book(title, author, year) VALUES ('Лямбда выражения в Java 8. Функциональное программирование', 'Ричард_Уорбэртон',2014);


SELECT *FROM Book;

SELECT title FROM Book WHERE id=1;

SELECT * FROM Book JOIN Person ON Book.person_id = Person.id where Book.id =27;

UPDATE Book SET person_id=2 WHERE id=24;