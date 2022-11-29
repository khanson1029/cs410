create database cs410;
use cs410;

CREATE TABLE Courses (
  c_id VARCHAR(50) PRIMARY KEY,
  section INTEGER NOT NULL,
  description VARCHAR(200) NOT NULL
);

CREATE TABLE Categories(
  c_name VARCHAR(50) PRIMARY KEY,
  weight INT,
  FOREIGN KEY (c_name) REFERENCES Courses(c_id)
);


CREATE TABLE Assignments (
  a_name VARCHAR(50) PRIMARY KEY,
  a_value INTEGER,
  grade ENUM ('A', 'B', 'C', 'D', 'F'),
  FOREIGN KEY (a_name) REFERENCES Courses(c_id)
);

CREATE TABLE Students (
  s_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  s_name VARCHAR(100) NOT NULL,
  FOREIGN KEY (s_id) REFERENCES Courses(c_id)
);