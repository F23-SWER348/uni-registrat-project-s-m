package pro.example;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.ArrayList;
import java.util.List;

public class student extends Userprofile {
 
private Double gpa;
ArrayList<course> courses;
List<String> tokenCourses;
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
    return gpa;
}
public void setGpa(Double gpa) {
    this.gpa = gpa;
}
public List<String> gettokenCourses(){
    return tokenCourses;
}
public void settokenCourses(List<String> t){
    this.tokenCourses=t;

}

    public void calculateGpa(List<String> grades) {
        ForkJoinPool pool = new ForkJoinPool();

        double totalCredits = pool.invoke(new TotalCreditsTask(getCourses()));
        if (totalCredits == 0) {
            this.gpa = 0.0;  
            return;
        }

        double totalGradePoints = pool.invoke(new TotalGradePointsTask(getCourses(), grades));
        this.gpa = totalGradePoints / totalCredits;
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
        switch (grade) {
            case "A": return 4.0;
            case "B+": return 3.5 ;
            case "B": return 3.0;
            case "c+": return 2.5;
            case "c": return 2.0;
            case "d+": return 1.5;
            case "d": return 1.0;
            default: return 0.0;
        }
    }


}