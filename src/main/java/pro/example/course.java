package pro.example;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


public class course {
    private String courseName;
    private int numOfCredits;
    List<String> prerequisites;
    LocalTime start ;
    LocalTime end;
    String day;

    public course(String name, int numOfCredits, LocalTime start,LocalTime end , String day) {
        this.end=end;
        this.start=start;
        this.courseName = name;
        this.numOfCredits = numOfCredits;
        this.day =day;
        
    }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String name) {
        this.courseName = name;
    }
    public int getNumOfCredits() {
        return numOfCredits;
    }
    public void setNumOfCredits(int numOfCredits) {
        this.numOfCredits = numOfCredits;
    }
    
  public Optional<List<String>> getPrerequisites() {
    return Optional.ofNullable(this.prerequisites);
}

    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }
    public LocalTime getStart(){
        return this.start;
    }
     public void setStart(LocalTime start ){
        this.start=start;
    }
     public LocalTime getEnd(){
        return this.end;
    }
     public void setEnd(LocalTime end ){
        this.end=end;
    }
       public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
    
    public boolean hasScheduleConflict(course otherCourse) {
        if(otherCourse.getDay().equals(this.getDay())&& !otherCourse.getCourseName().equals(this.getCourseName())){
        LocalTime thisStart = this.getStart();
        LocalTime thisEnd = this.getEnd();
        LocalTime otherStart = otherCourse.getStart();
        LocalTime otherEnd = otherCourse.getEnd();
        // Check for overlap
        return !(otherEnd.isBefore(thisStart) || otherStart.isAfter(thisEnd));
    }
    return false;
 
    
}}