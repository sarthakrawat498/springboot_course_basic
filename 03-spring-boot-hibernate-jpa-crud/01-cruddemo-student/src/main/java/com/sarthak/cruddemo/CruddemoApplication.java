package com.sarthak.cruddemo;

import com.sarthak.cruddemo.dao.StudentDAO;
import com.sarthak.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
        return runner -> {
            //createStudent(studentDAO);
            createMultipleStudents(studentDAO);
            //readStudent(studentDAO);
            //queryForStudents(studentDAO);
            //queryForStudentsByLastName(studentDAO);
            //updateStudent(studentDAO);
            //deleteStudent(studentDAO);
            //deleteAllStudents(studentDAO);
        };
    }

    private void deleteAllStudents(StudentDAO studentDAO) {
        System.out.println("Deleting all students");
        int numRowsDeleted = studentDAO.deleteAll();
        System.out.println("Deleted row count : " + numRowsDeleted);
    }

    private void deleteStudent(StudentDAO studentDAO) {
        int studentId = 3;
        System.out.println("Deleting student id : " + studentId);
        studentDAO.delete(studentId);
    }

    private void updateStudent(StudentDAO studentDAO) {
        //retrieve student based on id: primary key
        int studentId = 1 ;
        System.out.println("Getting student with id : " + studentId);
        Student myStudent = studentDAO.findById(studentId);
        System.out.println("Updating student...");
        //change first name to John
        myStudent.setFirstName("John");
        studentDAO.update(myStudent);
        //display results
        System.out.println("Updated student : " + myStudent);
    }

    private void queryForStudentsByLastName(StudentDAO studentDAO) {
        //get a list of students
        List<Student> theStudents=  studentDAO.findByLastName("Duck");
        //display list
        for(Student tempStudent : theStudents){
            System.out.println(tempStudent);
        }
    }

    private void queryForStudents(StudentDAO studentDAO) {
        //get list of students
        List<Student> theStudents = studentDAO.findAll();
        //display the list
        for (Student tempStudent : theStudents){
            System.out.println(tempStudent);
        }
    }

    private void readStudent(StudentDAO studentDAO) {
        //create student object
        System.out.println("Creating new student object ...");
        Student tempStudent = new Student("Daffy","Duck","daffy@duck.com");
        //save the student
        System.out.println("Saving the student...");
        studentDAO.save(tempStudent);
        //display id of saved student
        int theId = tempStudent.getId();
        System.out.println("Saved student. Generated id : " + theId);
        //retrieve student based on id: primary key
        System.out.println("Retrieving student with id : " + theId);
        Student myStudent = studentDAO.findById(theId);
        //display student
        System.out.println("Found the student : " + myStudent);
    }

    private void createMultipleStudents(StudentDAO studentDAO) {
        //create multiple students
        System.out.println("Creating 3 student objects ...");
        Student tempStudent1 = new Student("John","Doe","john@doe.com");
        Student tempStudent2 = new Student("Mary","Public","mary@public.com");
        Student tempStudent3 = new Student("Bonita","Applebum","bon@app.com");
        //save student objects
        System.out.println("Saving 3 students ...");
        studentDAO.save(tempStudent1);
        studentDAO.save(tempStudent2);
        studentDAO.save(tempStudent3);
    }

    private void createStudent(StudentDAO studentDAO) {
        //create the student obj
        System.out.println("Creating new student object ...");
        Student tempStudent = new Student("Paul","Doe","paul@doe.com");
        //save the student obj
        System.out.println("Saving the student ...");
        studentDAO.save(tempStudent);
        //display id of saved student
        System.out.println("Saved student. Generated id : " + tempStudent.getId());
    }
}
