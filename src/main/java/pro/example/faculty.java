package pro.example;

import java.util.ArrayList;

public class faculty extends Userprofile {
    ArrayList<course> courses;


public faculty(String name, String contactDetails) {
    super(name, contactDetails);
    
}


public ArrayList<course> getCourses() {
    return courses;
}


public void setCourses(ArrayList<course> courses) {
    this.courses = courses;
}


public faculty(){
    super();
}


    
}