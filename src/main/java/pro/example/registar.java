package pro.example;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Predicate;

public class registar  {
    String nameOfTheRegistrar;
    public void addNewSemester (String name , LocalDate start, LocalDate end,ArrayList<course> course){
        semester s =new semester (name , start, end,course);
    } 
    public void addNewStudent(String name, String contactDetails, Double gpa, ArrayList<course> courses) {
      student student = new student(name, contactDetails, gpa, courses);
      // You might want to do something with the created student, like adding it to a list.
      System.out.println("New student added: " + student.getName());
  }

  public void addNewFaculty(String name, String contactDetails, ArrayList<course> courses) {
      faculty faculty = new faculty(name, contactDetails, courses);
      // You might want to do something with the created faculty, like adding it to a list.
      System.out.println("New faculty added: " + faculty.getName());
  }

    public void AddNewGrade(student s, double gpa){
        s.setGpa(gpa);
    }

    public void AddNewCourse(String name, int numOfCredits, String faculty,LocalDateTime weekly_meeting){
      
      course c=new course(name,numOfCredits,faculty);
    }
    public String academicStanding(student student) {
        double gpa = student.getGpa();

        Predicate<Double> highestHonorsCondition = g -> g >= 3.5;
        Predicate<Double> honorsCondition = g -> g >= 3.0;
        Predicate<Double> goodStandingCondition = g -> g >= 2.0;

        if (highestHonorsCondition.test(gpa)) {
            return "Highest Honors";
        } else if (honorsCondition.test(gpa)) {
            return "Honors";
        } else if (goodStandingCondition.test(gpa)) {
            return "Good Standing";
        } else {
            return "Probation";
        }
    }
  }