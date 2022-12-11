create database if not exists cs410;
use cs410;

CREATE TABLE classes(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    coursenum VARCHAR(50) NOT NULL,
    section INTEGER NOT NULL,
    term VARCHAR(50) NOT NULL,
    description VARCHAR(200)NOT NULL
);

CREATE TABLE categories(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL
);

CREATE TABLE assignments(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(200)NOT NULL,
    points INTEGER NOT NULL        
);

CREATE TABLE students(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    username VARCHAR(200) NOT NULL
);

CREATE TABLE enrollments(
	id INTEGER PRIMARY KEY  AUTO_INCREMENT,
    classid INTEGER NOT NULL,
    studentid INTEGER NOT NULL,
	FOREIGN KEY(classid) REFERENCES classes(id),
    FOREIGN KEY(studentid) REFERENCES students(id)
);

CREATE TABLE grades(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    grade INTEGER NOT NULL,
    studentid INTEGER NOT NULL,
    assignmentid INTEGER NOT NULL,
	FOREIGN KEY(studentid) REFERENCES students(id),
    FOREIGN KEY(assignmentid) REFERENCES assignments(id)
);

CREATE TABLE weights(
	id INTEGER PRIMARY KEY  AUTO_INCREMENT,
    weight DOUBLE NOT NULL,
    catid INTEGER NOT NULL,
    classid INTEGER NOT NULL,
	FOREIGN KEY(catid) REFERENCES categories(id),
    FOREIGN KEY(classid) REFERENCES classes(id)
);

insert into classes (coursenum, section, term, description) values ('cs410', '1', 'Fall 2022', 'In depth database design');
insert into classes (coursenum, section, term, description) values ('art110', '1', 'Fall 2022', 'Intro to art principles');
insert into classes (coursenum, section, term, description) values ('bio224', '1','Fall 2022', 'Human physiology');

insert into categories (name) values ('homework');
insert into categories (name) values ('exam');
insert into categories (name) values ('project');

insert into assignments (name, description, points) values ('Homework 1', 'hw1', 100);
insert into assignments (name, description, points) values ('Homework 2', 'hw2', 100);
insert into assignments (name, description, points) values ('Homework 3', 'hw3', 100);
insert into assignments (name, description, points) values ('Homework 4', 'hw4', 100);
insert into assignments (name, description, points) values ('Midterm', 'midterm', 100);
insert into assignments (name, description, points) values ('Final', 'final', 100);
insert into assignments (name, description, points) values ('Project 1', 'p1', 100);
insert into assignments (name, description, points) values ('Project 2', 'p2', 100);

insert into students (name, username) values ('Pieter Heymann', 'Heymann');
insert into students (name, username) values ('Hilliary Itzkovsky', 'Itzkovsky');
insert into students (name, username) values ('Lanie Menci', 'Menci');
insert into students (name, username) values ('Denny Doulden', 'Doulden');
insert into students (name, username) values ('Dino Stendell', 'Stendell');
insert into students (name, username) values ('Esme Willes', 'Willes');
insert into students (name, username) values ('Zedekiah Bramich', 'Bramich');
insert into students (name, username) values ('Vinni Airey', 'Airey');
insert into students (name, username) values ('Ellis Venart', 'Venart');
insert into students (name, username) values ('Madel Domney', 'Domney');
insert into students (name, username) values ('Olva Bowick', 'Bowick');
insert into students (name, username) values ('Susanetta Colliss', 'Colliss');
insert into students (name, username) values ('Maureene Keatch', 'Keatch');
insert into students (name, username) values ('Aube Brogini', 'Brogini');
insert into students (name, username) values ('Dulcie Gard', 'Gard');
insert into students (name, username) values ('Alexandrina Redgrove', 'Redgrove');
insert into students (name, username) values ('Errol Keam', 'Keam');
insert into students (name, username) values ('Clarisse Redpath', 'Redpath');
insert into students (name, username) values ('Pepillo Garthland', 'Garthland');
insert into students (name, username) values ('Ches Trenoweth', 'Trenoweth');

insert into enrollments(classid, studentid) values(1, 1);
insert into enrollments(classid, studentid) values(3, 1);
insert into enrollments(classid, studentid) values(1 , 2);
insert into enrollments(classid, studentid) values(2 , 2);
insert into enrollments(classid, studentid) values(2 , 3);
insert into enrollments(classid, studentid) values(3 , 3);
insert into enrollments(classid, studentid) values(1 , 4);
insert into enrollments(classid, studentid) values(3 , 4);
insert into enrollments(classid, studentid) values(1 , 5);
insert into enrollments(classid, studentid) values(2 , 5);
insert into enrollments(classid, studentid) values(1 , 6);
insert into enrollments(classid, studentid) values(3 , 6);
insert into enrollments(classid, studentid) values(2 , 7);
insert into enrollments(classid, studentid) values(3 , 7);
insert into enrollments(classid, studentid) values(3 , 8);
insert into enrollments(classid, studentid) values(2 , 8);
insert into enrollments(classid, studentid) values(1 , 9);
insert into enrollments(classid, studentid) values(3 , 9);
insert into enrollments(classid, studentid) values(2 , 10);
insert into enrollments(classid, studentid) values(1 , 10);
insert into enrollments(classid, studentid) values(3 , 11);
insert into enrollments(classid, studentid) values(1 , 11);
insert into enrollments(classid, studentid) values(2 , 12);
insert into enrollments(classid, studentid) values(3 , 12);
insert into enrollments(classid, studentid) values(1 , 13);
insert into enrollments(classid, studentid) values(3 , 13);
insert into enrollments(classid, studentid) values(2 , 14);
insert into enrollments(classid, studentid) values(1 , 14);
insert into enrollments(classid, studentid) values(1 , 15);
insert into enrollments(classid, studentid) values(2 , 15);
insert into enrollments(classid, studentid) values(1 , 16);
insert into enrollments(classid, studentid) values(3 , 16);
insert into enrollments(classid, studentid) values(1 , 17);
insert into enrollments(classid, studentid) values(2 , 17);
insert into enrollments(classid, studentid) values(1 , 18);
insert into enrollments(classid, studentid) values(3 , 18);
insert into enrollments(classid, studentid) values(1 , 19);
insert into enrollments(classid, studentid) values(3 , 19);
insert into enrollments(classid, studentid) values(2 , 20);
insert into enrollments(classid, studentid) values(3 , 20);






insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 1 ,1);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 1 ,2);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 1 ,3);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 1 ,4);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 1 ,5);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 1 ,6);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 1 ,7);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 1 ,8);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 2 ,1);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 2 ,2);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 2 ,3);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 2 ,4);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 2 ,5);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 2 ,6);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 2 ,7);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 2 ,8);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 3 ,1);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 3 ,2);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 3 ,3);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 3 ,4);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 3 ,5);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 3 ,6);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 3 ,7);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 3 ,8);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 4 ,1);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 4 ,2);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 4 ,3);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 4 ,4);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 4 ,5);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 4 ,6);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 4 ,7);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 4 ,8);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 5 ,1);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 5 ,2);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 5 ,3);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 5 ,4);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 5 ,5);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 5 ,6);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 5 ,7);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 5 ,8);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 6 ,1);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 6 ,2);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 6 ,3);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 6 ,4);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 6 ,5);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 6 ,6);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 6 ,7);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 6 ,8);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 7 ,1);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 7 ,2);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 7 ,3);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 7 ,4);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 7 ,5);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 7 ,6);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 7 ,7);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 7 ,8);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 8 ,1);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 8 ,2);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 8 ,3);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 8 ,4);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 8 ,5);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 8 ,6);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 8 ,7);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 8 ,8);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 9 ,1);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 9 ,2);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 9 ,3);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 9 ,4);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 9 ,5);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 9 ,6);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 9 ,7);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 9 ,8);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 10 ,1);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 10 ,2);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 10 ,3);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 10 ,4);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 10 ,5);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 10 ,6);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 10 ,7);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 10 ,8);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 11 ,1);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 11 ,2);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 11 ,3);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 11 ,4);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 11 ,5);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 11 ,6);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 11 ,7);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 11 ,8);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 12 ,1);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 12 ,2);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 12 ,3);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 12 ,4);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 12 ,5);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 12 ,6);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 12 ,7);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 12 ,8);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 13 ,1);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 13 ,2);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 13 ,3);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 13 ,4);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 13 ,5);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 13 ,6);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 13 ,7);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 13 ,8);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 14 ,1);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 14 ,2);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 14 ,3);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 14 ,4);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 14 ,5);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 14 ,6);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 14 ,7);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 14 ,8);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 15 ,1);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 15 ,2);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 15 ,3);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 15 ,4);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 15 ,5);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 15 ,6);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 15 ,7);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 15 ,8);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 16 ,1);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 16 ,2);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 16 ,3);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 16 ,4);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 16 ,5);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 16 ,6);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 16 ,7);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 16 ,8);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 17 ,1);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 17 ,2);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 17 ,3);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 17 ,4);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 17 ,5);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 17 ,6);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 17 ,7);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 17 ,8);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 18 ,1);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 18 ,2);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 18 ,3);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 18 ,4);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 18 ,5);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 18 ,6);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 18 ,7);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 18 ,8);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 19 ,1);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 19 ,2);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 19 ,3);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 19 ,4);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 19 ,5);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 19 ,6);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 19 ,7);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 19 ,8);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 20 ,1);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 20 ,2);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 20 ,3);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 20 ,4);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 20 ,5);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 20 ,6);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 20 ,7);
insert into grades(grade, studentid, assignmentid) values(floor(rand()*101), 20 ,8);

insert into weights(weight, catid, classid) values(0.2 ,1, 1);
insert into weights(weight, catid, classid) values(0.5, 2, 1);
insert into weights(weight, catid, classid) values(0.3, 3, 1);
insert into weights(weight, catid, classid) values(0.5 ,1, 2);
insert into weights(weight, catid, classid) values(0.2, 2, 2);
insert into weights(weight, catid, classid) values(0.3, 3, 2);
insert into weights(weight, catid, classid) values(0.2 ,1, 3);
insert into weights(weight, catid, classid) values(0.5, 2, 3);
insert into weights(weight, catid, classid) values(0.3, 3, 3);





