package pro.example;

import java.util.ArrayList;

import java.util.ArrayList;
import java.util.List;

public class student extends Userprofile {
 
private Double gpa;
ArrayList<course> courses;
List<course> tokenCourses;
public student(){
    super();
}
public student(String name, String contactDetails,Double gpa,ArrayList<course> courses) {
 super(name, contactDetails);
 this.gpa=gpa;
 this.courses=courses;
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
public List<course> gettokenCourses(){
    return tokenCourses;
}
public void settokenCourses(List<course> t){
    this.tokenCourses=t;

}}