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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

public class AppTest {
    static  ArrayList<course> courses =new ArrayList<>();
    static  registar registar = new registar();
    static  String path = "src/test/resources/course.txt";
       static  String path2 = "src/test/resources/faculty.txt";
  
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
                                
 
                i++;  
             } 
 
         }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        }