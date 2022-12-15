****************
* Project - Grade Book
* CS 410
* 12/14/2022
* Chazz Chandler, Kyle Hanson, Kyle Schultz
****************

OVERVIEW:

This project is designed to create a database ran via command line from a java
application linking a MySQL Database via JDBC. The goal is to create a ER graph,
a MySQL Database, a Java Application to run the program and a video walking
through the functions.


INCLUDED FILES:

README.txt - This file
ZIPFile - containing Driver Class and Maven files
Model.pdf - Diagram of ER Model
Schema.sql - DDL file for the SQL script
Dump.sql - Contains the built database with dummy info


BUILDING AND RUNNING:




PROGRAM DESIGN:

The program is designed to take a large variety of inputs depending upon the
command that is passed into the command line. There is a "help" command
which will display all of the commands and their desired inputs. Typically the
user is required to have a particular class selected which ensures the user can
then select students attending that class, assign students to the class and 
add grades and assignments to the class. Lastly the program can then produce the
grades for a particular student for each category as well as their total grade. 
In general the tool is designed to be a gradebook for teachers to use to handle
multiple classes, students, grades and assignments.


TESTING:

We ran each SQL queary locally in MySQL to ensure that they ran prior to running
them from the Command Line via JDBC. We also slowly built upon the quearies, 
we would ensure the SELECT statements would retrieve the data. Once it did we 
began to add the additional selects and joins to build them up. Once the Quearies
worked we linked them up with a CallableStatement function from Java. Once we 
began to use the command line we'd slowly add print statements to ensure proper 
values were being accessed and printed in the proper format. The core portion
of our testing and building that truly made this easier was having all 3 of us
ona zoom call screen sharing and trio-programming the entire time. We always had
3 sets of eyes each looking at a different segment of code that truly made the 
debugging process super easy.

DISCUSSION:

Overall it was a difficult assignment, but we feel we learned alot about different
functions and methods that we had previously not known. The SQL statements got easier
and easier as we were doing so many that the process to build and call them seemed to
become second nature to us. If we had an issue with one, we had two people to back us
up and ensure it was working properly. We found the true benefit of Pair-Programming
or in this case Trio-programming. We also found that the initial build of the ER and
database can truly influence the ease or difficulty that can be created down the line
creating SQL statements. 