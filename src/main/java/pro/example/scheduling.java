package pro.example;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class scheduling {
    LocalDateTime time;
   course courses;
    public scheduling(LocalDateTime time,course courses) {
        this.time = time;
        this.courses = courses;
    }
    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    public course getCourses() {
        return courses;
    }
    public void setCourses(course courses) {
        this.courses = courses;
    }




}
