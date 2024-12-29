DROP  TABLE Person CASCADE ;
--удалить все данные из таблицы
TRUNCATE TABLE Person;

--Добавить столбец в таблицу
ALTER TABLE person ADD  COLUMN address varchar(50) NOT NULL ;

CREATE TABLE person(
    id int GENERATED BY DEFAULT AS IDENTITY  PRIMARY KEY ,
    fio varchar(100) NOT NULL UNIQUE ,
    yearOfBirth int check ( yearOfBirth < 2100 and yearOfBirth >= 1900) NOT NULL
);

INSERT INTO person(fio, yearOfBirth) VALUES ('Александра',1984);
INSERT INTO person(fio, yearOfBirth) VALUES ('Edvard',1980);
INSERT INTO person(fio, yearOfBirth) VALUES ( 'Evgeny', 1991);
INSERT INTO person(fio, yearOfBirth) VALUES ( 'Egor ', 1991);
INSERT INTO person(fio, yearOfBirth) VALUES ( 'Михаил', 1991);
INSERT INTO person(fio, yearOfBirth) VALUES ( 'Виталий', 1985);
INSERT INTO person(fio, yearOfBirth) VALUES ( 'Наталья', 1991);
INSERT INTO person(fio, yearOfBirth) VALUES ( 'Аннастасия', 1995);
INSERT INTO person(fio, yearOfBirth) VALUES ( 'Артем', 1991);
INSERT INTO person(fio, yearOfBirth) VALUES ( 'Сергей', 1970);




SELECT *FROM person;

SELECT fio FROM person WHERE id=1;