package pro.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class semester {
    private String semesterName;
    LocalDate start;
    LocalDate end;
    ArrayList<course> course;
    public semester(String semesterName, LocalDate start, LocalDate end ,ArrayList<course> course) {
        this.semesterName = semesterName;
        this.start = start;
        this.end = end;
        this.course=course;
    }
    public String getSemesterName() {
        return semesterName;
    }
    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }
    public LocalDate getStart() {
        return start;
    }
    public void setStart(LocalDate start) {
        this.start = start;
    }
    public LocalDate getEnd() {
        return end;
    }
    public void setEnd(LocalDate end) {
        this.end = end;
    }
    public ArrayList<course> getCourse() {
        return course;
    }
    public void setCourse(ArrayList<course> course) {
        this.course = course;
    }
    

}