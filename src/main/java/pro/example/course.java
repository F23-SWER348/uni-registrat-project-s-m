package pro.example;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class course {
    private String courseName;
    private int numOfCredits;
    private String faculty;
    List<String> prerequisites;
    LocalDateTime start ;
    LocalDateTime end;
   

    public course(String name, int numOfCredits, String faculty ) {
        this.courseName = name;
        this.numOfCredits = numOfCredits;
        this.faculty = faculty;
        
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
    public String getFaculty() {
        return faculty;
    }
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }
    public LocalDateTime getStart(){
        return this.start;
    }
     public void setStart(LocalDateTime start ){
        this.start=start;
    }
     public LocalDateTime getEnd(){
        return this.end;
    }
     public void setEnd(LocalDateTime end ){
        this.end=end;
    }
    public boolean hasScheduleConflict(course otherCourse) {
        LocalDateTime thisStart = this.getStart();
        LocalDateTime thisEnd = this.getEnd();
        LocalDateTime otherStart = otherCourse.getStart();
        LocalDateTime otherEnd = otherCourse.getEnd();
        // Check for overlap
        return !(otherEnd.isBefore(thisStart) || otherStart.isAfter(thisEnd));
    }
    
}