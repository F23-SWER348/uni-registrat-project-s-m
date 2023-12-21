package pro.example;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class registar  {
    String nameOfTheRegistrar;


    // add New Semester
    public void addNewSemester (String name , LocalDate start, LocalDate end,ArrayList<course> course){
        semester s =new semester (name , start, end,course);
    } 

//add New Student
    public synchronized student addNewStudent(String name, String contactDetails, ArrayList<course> courses, List<String> x) {

      student student = new student(name, contactDetails);
      student.settokenCourses(x);

      courses.forEach(c -> {
        course c1 = c;
        AddNewCoursesStudent(student,courses, c1);
    });

     System.out.println("New student added: " + student.getName());
     return student;
  }

//add New Faculty
  public  faculty addNewFaculty(String name, String contactDetails, ArrayList<course> courses) {
    faculty faculty = new faculty(name, contactDetails);
    
    if (!hasFacultyScheduleConflict(courses)) {
        faculty.setCourses(courses);
        
        System.out.println("New faculty added: " + faculty.getName());
    } else {
        System.out.println("Schedule conflict! The new faculty's courses conflict with each other.");
    }return faculty;
}


// check for Faculty Schedule Conflict
private boolean hasFacultyScheduleConflict(ArrayList<course> facultyCourses) {
    return facultyCourses.stream()
            .flatMap(course1 -> facultyCourses.stream()
                    .filter(course2 -> !course1.equals(course2))
                    .filter(course2 -> course1.hasScheduleConflict(course2)))
            .findFirst()
            .isPresent();
}

//Add New Course
    public course AddNewCourse(String name, int numOfCredits,LocalTime start,LocalTime end, String day){
      
      course c=new course(name,numOfCredits,start,end,day);
      return c;
    }
    

    // report for academic Standing
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


    //Add New Courses Student
    
    public void AddNewCoursesStudent(student s,ArrayList<course> courses, course newCourse) {
        
        if (!newCourse.getPrerequisites().isEmpty()) {
            if (s.gettokenCourses().get().containsAll(newCourse.getPrerequisites().get())) {
                if (!hasScheduleConflict(courses, newCourse)) {
                    s.getCourses().add(newCourse);
                    System.out.println("Course added to student: " + newCourse.getCourseName());
                } else {
                    
                    System.out.println("Schedule conflict! The new course conflicts with existing courses.");
                }
            } else {
                System.out.println("Missing prerequisites! The student does not have all the prerequisites for the new course.");
            }
        } else {
            if (!hasScheduleConflict(courses, newCourse)) {
                s.getCourses().add(newCourse);
                System.out.println("Course added to student: " + newCourse.getCourseName());
            } else {
                System.out.println("Schedule conflict! The new course conflicts with existing courses.");
            }
        }
    }


    //check for Schedule Conflict
    private boolean hasScheduleConflict(ArrayList<course> studentCourses, course newCourse) {
        if(studentCourses.size()==1){
            return false;
        }
     
        return studentCourses.stream()
                .anyMatch(existingCourse -> existingCourse.hasScheduleConflict(newCourse));
    }
        



}
  