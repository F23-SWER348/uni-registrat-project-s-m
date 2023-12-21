package pro.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class AppTest {
    static  ArrayList<course> courses =new ArrayList<>();
    static  registar registar = new registar();
    static  String path = "src/test/resources/course.txt";
       static  String path2 = "src/test/resources/faculty.txt";
      List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        List<String> selectedValues = new ArrayList<>();


  //add courses
    @BeforeAll
    static void testApp() {
      
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
          
            int i = 0;
    
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) { 
                    String name = parts[0];
                    int credits = Integer.parseInt(parts[1]);
                    LocalTime start = LocalTime.parse(parts[2]);
                    LocalTime end = LocalTime.parse(parts[3]);
                    String day = parts[4];
                    String prerequisite = parts[5];  
                    course c = registar.AddNewCourse(name, credits, start, end, day);
                    c.setPrerequisites(List.of(prerequisite));
                    courses.add(c);
                    assertEquals(courses.get(i).getCourseName(), name);
                 
                    i++;
                    
                }
            }


            // create new semester with a list of courses
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 5, 31);
        registar.addNewSemester("Spring 2023", startDate, endDate, courses);

        } catch (IOException e) {
            e.printStackTrace();
        }}

// ---------------------------------------------------------------------//
//----------------------------------------------------------------------//
//----------------------------------------------------------------------//
     // all the test cases below use the courses int the Spring 2023 semester //




        //test faculty and no conflict 
        @Test
        void testFacultyConflict(){
        ArrayList<faculty> faculties = new ArrayList<>();
        ArrayList<course> a =new ArrayList<>();
        a.add(courses.get(3));
        a.add(courses.get(4));
      
        try (BufferedReader br = new BufferedReader(new FileReader(path2))) {
                int i=0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) { 
                    String name = parts[0].trim();
                    String contactDetails = parts[1].trim();
                    faculty fac = registar.addNewFaculty(name, contactDetails, a);
                    faculties.add(fac);
                    assertEquals(fac.getName(), name);
                i++;  
             } 
 
         }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //test faculty with conflict
    @Test
        void testFaculty(){
      
        ArrayList<faculty> faculties = new ArrayList<>();
        ArrayList<course> a =new ArrayList<>();
        a.add(courses.get(1));
        a.add(courses.get(3));
       
        try (BufferedReader br = new BufferedReader(new FileReader(path2))) {
                int i=0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) { 
                    String name = parts[0].trim();
                    String contactDetails = parts[1].trim();

                    faculty fac = registar.addNewFaculty(name, contactDetails, a);
              
                    faculties.add(fac);

                    assertEquals(fac.getName(), name);
                i++;  
             } 
 
         }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  // read from the token courses
    @Test
    void tokenCourses(){
         String filePath1 = "src/test/resources/tokencourses.txt";

    
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath1))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

      
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
                 
            } else {
                System.out.println("Invalid line format: " + line);
            }
        }
      assertEquals(list1.get(0), "Mathematics");
      assertEquals(list2.get(0), "History");
      assertEquals(list3.get(0), "Chemistry");
       
        System.out.println("List 1: " + list1);
        System.out.println("List 2: " + list2);
        System.out.println("List 3: " + list3);
    }

    //Missing prerequisites
    @Test
        void testStudentConflict(){
             String filePath = "src/test/resources/student.txt";

        ArrayList<student> stu = new ArrayList<>();
        ArrayList<course> a =new ArrayList<>();

        a.add(courses.get(3));
      
       a.add(courses.get(2));
       a.add(courses.get(0));

           tokenCourses();
      

    
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                int i=0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) { 
                    String name = parts[0].trim();
                    String contactDetails = parts[1].trim();

                    student s = registar.addNewStudent(name, contactDetails, a,list1);
                   
                    stu.add(s);

                    assertEquals(s.getName(), name);
                
                            System.out.println(stu.get(i).getCourses().get(0).getCourseName());   
                                 System.out.println(stu.get(i).gettokenCourses());  
 
                i++;  
             } 
 
         }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //no conflict and no Missing prerequisites
     @Test
        void testStudentConflict1(){
             String filePath = "src/test/resources/student.txt";

        ArrayList<student> stu = new ArrayList<>();
        ArrayList<course> a =new ArrayList<>();

        a.add(courses.get(3));
        
       a.add(courses.get(2));
       
       
           tokenCourses();
    
    
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                int i=0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) { 
                    String name = parts[0].trim();
                    String contactDetails = parts[1].trim();

                    student s = registar.addNewStudent(name, contactDetails, a,list1);
                   
                    stu.add(s);

                    assertEquals(s.getName(), name);
                 
                            System.out.println(stu.get(i).getCourses().get(0).getCourseName());   
                                 System.out.println(stu.get(i).gettokenCourses());  
 
                i++;  
             } 
 
         }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Schedule conflict
    @Test
        void testStudentConflict2(){
             String filePath = "src/test/resources/student.txt";

        ArrayList<student> stu = new ArrayList<>();
        ArrayList<course> a =new ArrayList<>();

        a.add(courses.get(3));
        System.out.println(courses.get(3).getPrerequisites());
       a.add(courses.get(1));
      
       
           tokenCourses();
    
    
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                int i=0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    String contactDetails = parts[1].trim();

                    student s = registar.addNewStudent(name, contactDetails, a,list3);
                   
                    stu.add(s);

                    assertEquals(s.getName(), name);
    
                                 System.out.println(stu.get(i).gettokenCourses());  
 
                i++;  
             } 
 
         }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Missing prerequisites
      @Test
        void testStudentConflict3(){
             String filePath = "src/test/resources/student.txt";

        ArrayList<student> stu = new ArrayList<>();
        ArrayList<course> a =new ArrayList<>();

        a.add(courses.get(4));
      
       a.add(courses.get(2));
       
       
           tokenCourses();
      
    
    
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
                 
                                 System.out.println(stu.get(i).gettokenCourses());  
 
                i++;  
             } 
 
         }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //read from the grades 

    @Test 
    void grade(){
        String filePath =   "src/test/resources/grades.txt";;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
             
                String[] values = line.split(",");

            
                for (String value : values) {
                   
                     
                        selectedValues.add(value.trim());
                  
                }
                assertEquals(selectedValues.get(0), "A");
                 assertEquals(selectedValues.get(1), "B+");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  //calculateGpaStudent
    @Test
        void calculateGpaStudent(){
             String filePath = "src/test/resources/student.txt";

        ArrayList<student> stu = new ArrayList<>();
        ArrayList<course> a =new ArrayList<>();

        a.add(courses.get(2));
       
       a.add(courses.get(3));
       
       
           tokenCourses();
       
    
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                int i=0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) { 
                    String name = parts[0].trim();
                    String contactDetails = parts[1].trim();

                    student s = registar.addNewStudent(name, contactDetails, a,list1);
                   
                    stu.add(s);

                    assertEquals(s.getName(), name);
                   
 
                i++;  
             } 
 
         }
         grade();
        stu.get(0).calculateGpa(selectedValues); 
      System.out.println(stu.get(0).getGpa());  
   
         System.out.println(registar.academicStanding(stu.get(0)));
        } catch (IOException e) {
            e.printStackTrace();
        }}

        //read from grade
         @Test 
    void grade2(){
        String filePath =   "src/test/resources/grade.txt";;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
             
                String[] values = line.split(",");

            
                for (String value : values) {
                   
                     
                        selectedValues.add(value.trim());
                  
                }
                assertEquals(selectedValues.get(0), "D");
                 assertEquals(selectedValues.get(1), "D+");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //calculateGpaStudent
 @Test
        void calculateGpaStudent2(){
             String filePath = "src/test/resources/student.txt";

        ArrayList<student> stu = new ArrayList<>();
        ArrayList<course> a =new ArrayList<>();

        a.add(courses.get(2));
       
       a.add(courses.get(3));
       
       
           tokenCourses();
       
    
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                int i=0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    String contactDetails = parts[1].trim();

                    student s = registar.addNewStudent(name, contactDetails, a,list1);
                   
                    stu.add(s);

                    assertEquals(s.getName(), name);
                   
 
                i++;  
             } 
 
         }
         grade2();
        stu.get(0).calculateGpa(selectedValues); 
      System.out.println(stu.get(0).getGpa());  
   
         System.out.println(registar.academicStanding(stu.get(0)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        }}