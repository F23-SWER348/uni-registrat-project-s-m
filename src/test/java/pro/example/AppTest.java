package pro.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.beans.Transient;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {
    static  ArrayList<course> courses =new ArrayList<>();
    static  registar registar = new registar();
    static  String path = "src/test/resources/course.txt";
       static  String path2 = "src/test/resources/faculty.txt";
      List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
  
    @BeforeAll
    static void testApp() {
      
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
          
            int i = 0;
    
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) { // Ensure all required fields are present
                    String name = parts[0];
                    int credits = Integer.parseInt(parts[1]);
                    LocalTime start = LocalTime.parse(parts[2]);
                    
                    LocalTime end = LocalTime.parse(parts[3]);
                   
                    String day = parts[4];
                    String prerequisite = parts[5];
                    
                    course c = registar.AddNewCourse(name, credits, start, end, day);
                    // Assuming prerequisites are stored as a list of strings, you might want to adjust this part
                    c.setPrerequisites(List.of(prerequisite));
    
                    courses.add(c);
                    assertEquals(courses.get(i).getCourseName(), name);
                  //  System.out.println(courses.get(i).getCourseName());
                  //  System.out.println(courses.get(i).prerequisites);
                    i++;
                    //System.out.println(start);
                }
            }
             LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 5, 31);
        registar.addNewSemester("Spring 2023", startDate, endDate, courses);

           

        } catch (IOException e) {
            e.printStackTrace();
        }}

        @Test
        void testFacultyConflict(){
          //  System.out.println(courses.get(0).getCourseName());
        ArrayList<faculty> faculties = new ArrayList<>();
        ArrayList<course> a =new ArrayList<>();
        a.add(courses.get(3));
        a.add(courses.get(4));
       // System.out.println(a);
    
        try (BufferedReader br = new BufferedReader(new FileReader(path2))) {
                int i=0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) { // Ensure all required fields are present
                    String name = parts[0].trim();
                    String contactDetails = parts[1].trim();

                    faculty fac = registar.addNewFaculty(name, contactDetails, a);
                 //   System.out.println(fac);
                    faculties.add(fac);

                    assertEquals(fac.getName(), name);
                  //  System.out.println(faculties.get(i).getCourses().get(0).getCourseName());
                                
 
                i++;  
             } 
 
         }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
        void testFaculty(){
      //      System.out.println(courses.get(0).getCourseName());
        ArrayList<faculty> faculties = new ArrayList<>();
        ArrayList<course> a =new ArrayList<>();
        a.add(courses.get(0));
        a.add(courses.get(4));
       // System.out.println(a);
    
        try (BufferedReader br = new BufferedReader(new FileReader(path2))) {
                int i=0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) { // Ensure all required fields are present
                    String name = parts[0].trim();
                    String contactDetails = parts[1].trim();

                    faculty fac = registar.addNewFaculty(name, contactDetails, a);
                 //   System.out.println(fac);
                    faculties.add(fac);

                    assertEquals(fac.getName(), name);
                                
                 System.out.println(faculties.get(i).getCourses().get(0).getCourseName());
                i++;  
             } 
 
         }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    void tokenCourses(){
         String filePath1 = "src/test/resources/tokencourses.txt";

        // Read the file and store each line in a list
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath1))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create three lists, each containing two subjects
        

        // Split each line and distribute subjects to the three lists
          int j=0;
        for (String line : lines) {
            String[] subjects = line.split(",");
          
            if (subjects.length == 2) {
                if(j==0){
                list1.add(subjects[0]);
                list1.add(subjects[1]);
                }
                 else if(j==1){
                list2.add(subjects[0]);
                list2.add(subjects[1]);
                }
                else if(j==2){
                list3.add(subjects[0]);
                list3.add(subjects[1]);
                }
                j++;
                 // If you want to keep the original line with both subjects
            } else {
                System.out.println("Invalid line format: " + line);
            }
        }
      assertEquals(list1.get(0), "Mathematics");
      assertEquals(list2.get(0), "History");
      assertEquals(list3.get(0), "Chemistry");
        // Display the resulting lists
        System.out.println("List 1: " + list1);
        System.out.println("List 2: " + list2);
        System.out.println("List 3: " + list3);
    }
    
    @Test
        void testStudentConflict(){
             String filePath = "src/test/resources/student.txt";

        ArrayList<student> stu = new ArrayList<>();
        ArrayList<course> a =new ArrayList<>();

        a.add(courses.get(3));
        System.out.println(courses.get(3).getPrerequisites());
       a.add(courses.get(2));
       a.add(courses.get(0));

           tokenCourses();
      
    
  
       // System.out.println(a);
    
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                int i=0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) { // Ensure all required fields are present
                    String name = parts[0].trim();
                    String contactDetails = parts[1].trim();

                    student s = registar.addNewStudent(name, contactDetails, a,list1);
                   
                    stu.add(s);

                    assertEquals(s.getName(), name);
                  //  System.out.println(faculties.get(i).getCourses().get(0).getCourseName());
                            System.out.println(stu.get(i).getCourses().get(0).getCourseName());   
                                 System.out.println(stu.get(i).gettokenCourses());  
 
                i++;  
             } 
 
         }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
        void testStudentConflict1(){
             String filePath = "src/test/resources/student.txt";

        ArrayList<student> stu = new ArrayList<>();
        ArrayList<course> a =new ArrayList<>();

        a.add(courses.get(3));
        System.out.println(courses.get(3).getPrerequisites());
       a.add(courses.get(2));
       
       
           tokenCourses();
      
    
  
       // System.out.println(a);
    
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                int i=0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) { // Ensure all required fields are present
                    String name = parts[0].trim();
                    String contactDetails = parts[1].trim();

                    student s = registar.addNewStudent(name, contactDetails, a,list1);
                   
                    stu.add(s);

                    assertEquals(s.getName(), name);
                  //  System.out.println(faculties.get(i).getCourses().get(0).getCourseName());
                            System.out.println(stu.get(i).getCourses().get(0).getCourseName());   
                                 System.out.println(stu.get(i).gettokenCourses());  
 
                i++;  
             } 
 
         }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
        void testStudentConflict2(){
             String filePath = "src/test/resources/student.txt";

        ArrayList<student> stu = new ArrayList<>();
        ArrayList<course> a =new ArrayList<>();

        a.add(courses.get(3));
        System.out.println(courses.get(3).getPrerequisites());
       a.add(courses.get(1));
      
       
           tokenCourses();
      
    
  
       // System.out.println(a);
    
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                int i=0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) { // Ensure all required fields are present
                    String name = parts[0].trim();
                    String contactDetails = parts[1].trim();

                    student s = registar.addNewStudent(name, contactDetails, a,list3);
                   
                    stu.add(s);

                    assertEquals(s.getName(), name);
                  //  System.out.println(faculties.get(i).getCourses().get(0).getCourseName());
              //              System.out.println(stu.get(i).getCourses().get(0).getCourseName());   
                                 System.out.println(stu.get(i).gettokenCourses());  
 
                i++;  
             } 
 
         }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
      @Test
        void testStudentConflict3(){
             String filePath = "src/test/resources/student.txt";

        ArrayList<student> stu = new ArrayList<>();
        ArrayList<course> a =new ArrayList<>();

        a.add(courses.get(4));
        System.out.println(courses.get(3).getPrerequisites());
       a.add(courses.get(2));
       
       
           tokenCourses();
      
    
  
       // System.out.println(a);
    
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                int i=0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) { // Ensure all required fields are present
                    String name = parts[0].trim();
                    String contactDetails = parts[1].trim();

                    student s = registar.addNewStudent(name, contactDetails, a,list2);
                   
                    stu.add(s);

                    assertEquals(s.getName(), name);
                  //  System.out.println(faculties.get(i).getCourses().get(0).getCourseName());
                          //  System.out.println(stu.get(i).getCourses().get(0).getCourseName());   
                                 System.out.println(stu.get(i).gettokenCourses());  
 
                i++;  
             } 
 
         }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



        }