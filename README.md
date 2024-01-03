# Academic Management System Documentation
## Overview
### The Academic Management System is a Java-based system designed to manage academic information The system allows 
### for the addition of students, faculty, courses, semesters, and facilitates the interaction between these entities.
## Adding a New Semester
### To add a new semester, use the addNewSemester method in the registar class. 
### Provide the name of the semester, start and end dates, and a list of courses associated with the semester.
## Adding a New Student
### To add a new student, use the addNewStudent method in the registar class. 
### Provide the student's name, contact details, a list of courses, and a list of course tokens.
## Adding a New Faculty
### To add a new faculty member, use the addNewFaculty method in the registar class.
### Provide the faculty member's name, contact details, and a list of courses.
## Adding a New Course
### To add a new course, use the AddNewCourse method in the registar class. 
### Provide the course name, number of credits, start and end times, and the day of the week.
## Checking for Schedule Conflicts
### The system checks for schedule conflicts when adding courses to students or faculty. If a conflict exists, it will be reported in the console.
## Academic Standing
### The academicStanding method in the registar class provides information about a student's academic standing based on their GPA.
## Classes
## Userprofile
### The Userprofile class serves as a base class for student and faculty, providing basic information such as name and contact details.

## student
### The student class represents a student in the academic system. It extends Userprofile and includes features such as GPA calculation and course enrollment.

## faculty
### The faculty class represents a faculty member in the academic system. It extends Userprofile and includes features such as course assignments.

## course
### The course class represents a course offered in a semester. It includes information such as course name, credits, prerequisites, and schedule details.

## semester
### The semester class represents an academic semester. It includes information such as the semester name, start and end dates, and associated courses.

## registar
### The registar class serves as the main class for managing the academic system. It includes methods for adding semesters, students, faculty, courses, and checking for schedule conflicts.
## to see the class digram click on the link below
[Class Digram ](https://www.figma.com/file/oC2U2fSAC1xlNgspXGKRtb/class-digram?type=whiteboard&node-id=0-1&t=dJ6EuyasZPlQsboD-0)
