package pro.example;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.locks.ReentrantLock;
import java.util.List;
import java.util.Optional;

public class student extends Userprofile {
 
private Double gpa;
ArrayList<course> courses =new ArrayList<>();
List<String> tokenCourses;
private final ReentrantLock lock = new ReentrantLock();
public student(){
    super();
}
public student(String name, String contactDetails) {
 super(name, contactDetails);

}
public ArrayList<course> getCourses() {
    return courses;
}
public void setCourses(ArrayList<course> courses) {
    this.courses = courses;
}
public Double getGpa() {
    lock.lock(); // Acquire the lock
    try {
        return gpa;
    } finally {
        lock.unlock(); // Release the lock in the finally block
    }
 
}
public void setGpa(Double gpa) {
    this.gpa = gpa;
}
public Optional<List<String>> gettokenCourses() {
    return Optional.ofNullable(this.tokenCourses);
}
public void settokenCourses(List<String> t){
    this.tokenCourses=t;

}

public void calculateGpa(List<String> grades) {
    try (ForkJoinPool pool = new ForkJoinPool()) {
        double totalCredits = pool.invoke(new TotalCreditsTask(courses));
        if (totalCredits == 0) {
            this.gpa = 0.0;
            return;
        }

        double totalGradePoints = pool.invoke(new TotalGradePointsTask(courses, grades));
        this.gpa = totalGradePoints / totalCredits;
    }
}

private class TotalCreditsTask extends RecursiveTask<Double> {
    private List<course> courses;

    public TotalCreditsTask(List<course> courses) {
        this.courses = courses;
    }

    @Override
    protected Double compute() {
        return courses.stream()
                .mapToDouble(course::getNumOfCredits)
                .sum();
    }
}

private class TotalGradePointsTask extends RecursiveTask<Double> {
    private List<course> courses;
    private List<String> grades;

    public TotalGradePointsTask(List<course> courses, List<String> grades) {
        this.courses = courses;
        this.grades = grades;
    }

    @Override
    protected Double compute() {
        return courses.stream()
                .mapToDouble(course -> {
                    int index = courses.indexOf(course);
                    String grade = grades.get(index);
                    return gradeToGradePoint(grade) * course.getNumOfCredits();
                })
                .sum();
    }
}

private double gradeToGradePoint(String grade) {
    if ("A".equalsIgnoreCase(grade)) {
        return 4.0;
    } else if ("B+".equalsIgnoreCase(grade)) {
        return 3.5;
    } else if ("B".equalsIgnoreCase(grade)) {
        return 3.0;
    } else if ("C+".equalsIgnoreCase(grade)) {
        return 2.5;
    } else if ("C".equalsIgnoreCase(grade)) {
        return 2.0;
    } else if ("D+".equalsIgnoreCase(grade)) {
        return 1.5;
    } else if ("D".equalsIgnoreCase(grade)) {
        return 1.0;
    } else {
        return 0.0;
    }
    
}

}