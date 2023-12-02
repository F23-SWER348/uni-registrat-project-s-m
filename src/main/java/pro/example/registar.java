package pro.example;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class registar  {
    String nameOfTheRegistrar;
    public void addNewSemester (String name , LocalDate start, LocalDate end,ArrayList<course> course){
        semester s =new semester (name , start, end,course);
    } 
    public void AddNewStudent(student s){
       s =new student();
    }
    public void AddNewGrade(student s, double gpa){
        s.setGpa(gpa);
    }
    public void AddNewCourse(String name, int numOfCredits, String faculty,LocalDateTime weekly_meeting){
      course c=new course(name,numOfCredits,faculty);
    }
    public void academicStanding(student s){
           // to detect the highest honours, honours, or probation and provide a report
    }
}