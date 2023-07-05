package com.restapi.crudapi.rest;

import com.restapi.crudapi.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    //define @PostConstruct to load student data... only once!
    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Michael", "Scott"));
        theStudents.add(new Student("Jim", "Halpert"));
        theStudents.add(new Student("Dwight", "Schrute"));
    }

    //endpoint for students
    @GetMapping("/students")
    public List<Student> getStudents(){

        return theStudents;
    }

    //endpoint for a single student
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        //exception handling
        if(studentId < 0 || studentId >= theStudents.size()){
            throw new StudentNotFoundException("Student id not found: " + studentId );
        }

        //just index into the list
        return theStudents.get(studentId);
    }


}
