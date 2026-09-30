package com.sarthak.demo.rest;

import com.sarthak.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;
    //@postConstruct used only once
    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Poornima","Patel"));
        theStudents.add(new Student("Mario","Rossi"));
        theStudents.add(new Student("Marie","Curie"));
    }
    @GetMapping("/students")
    public List<Student> getStudents(){

        return theStudents;
    }
    //define endpoint /students/{studentId}
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        //just index into the list ... keeping simple for now
        //check the student id against list size
        if((studentId >= theStudents.size()) || (studentId < 0)){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return theStudents.get(studentId);
    }
}
