package pro.example;
import java.time.LocalDateTime;
import java.util.Optional;

public class course {
    private String courseName;
    private int numOfCredits;
    private String faculty;
    private String prerequisites;
   

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
    public Optional getPrerequisites() {
        return Optional.ofNullable("Theres No prerequisites");
    }
    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }


    
}