package pro.example;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class registar  {
    String nameOfTheRegistrar;
    
    public void addNewSemester (String name , LocalDate start, LocalDate end,ArrayList<course> course){
        semester s =new semester (name , start, end,course);
    } 
    public synchronized void addNewStudent(String name, String contactDetails, ArrayList<course> courses, List<String> x) {

      student student = new student(name, contactDetails);
      student.settokenCourses(x);

      courses.forEach(c -> {
        course c1 = c;
        AddNewCoursesStudent(student, c1);
    });

     System.out.println("New student added: " + student.getName());

  }

  public synchronized void addNewFaculty(String name, String contactDetails, ArrayList<course> courses) {
    faculty faculty = new faculty(name, contactDetails, courses);
    
    if (!hasFacultyScheduleConflict(faculty.getCourses())) {
        // You might want to do something with the created faculty, like adding it to a list.
        System.out.println("New faculty added: " + faculty.getName());
    } else {
        System.out.println("Schedule conflict! The new faculty's courses conflict with each other.");
    }
}

private boolean hasFacultyScheduleConflict(ArrayList<course> facultyCourses) {
    return facultyCourses.stream().anyMatch(course -> hasScheduleConflict(facultyCourses, course));
}

    public void AddNewGrade(student s, double gpa){
        s.setGpa(gpa);
    }

    public void AddNewCourse(String name, int numOfCredits,LocalDateTime start,LocalDateTime end){
      
      course c=new course(name,numOfCredits,start,end);
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
    public void AddNewCoursesStudent( student s, course newCourse) {
        if (!newCourse.getPrerequisites().isEmpty()) {
            if (s.gettokenCourses().containsAll(newCourse.getPrerequisites())) {
                if (!hasScheduleConflict(s.getCourses(), newCourse)) {
                    s.getCourses().add(newCourse);
                    System.out.println("Course added to student: " + newCourse.getCourseName());
                } else {
                    System.out.println("Schedule conflict! The new course conflicts with existing courses.");
                }
            } else if(newCourse.getPrerequisites().isEmpty()) {
                if (!hasScheduleConflict(s.getCourses(), newCourse)) {
                    s.getCourses().add(newCourse);
                    System.out.println("Course added to student: " + newCourse.getCourseName());
                } else {
                    System.out.println("Schedule conflict! The new course conflicts with existing courses.");
                }
            }
        }
    }

    private boolean hasScheduleConflict(ArrayList<course> studentCourses, course newCourse) {
        return studentCourses.stream()
                .anyMatch(existingCourse -> existingCourse.hasScheduleConflict(newCourse));
    }
        



}
  