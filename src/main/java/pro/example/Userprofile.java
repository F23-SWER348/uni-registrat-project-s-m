package pro.example;
public class Userprofile {

    String name;
     String contactDetails;
    public Userprofile(String name, String contactDetails) {
        this.name = name;
        this.contactDetails = contactDetails;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Userprofile() {
    }
    public String getContactDetails() {
        return contactDetails;
    }
    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
}