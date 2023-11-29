package pro.example;

public class student extends Userprofile {
 
private Double gpa;
public student(){
    super();
}
public student(String name, String contactDetails,Double gpa) {
 super(name, contactDetails);
 this.gpa=gpa;
}



public Double getGpa() {
    return gpa;
}
public void setGpa(Double gpa) {
    this.gpa = gpa;
}
}