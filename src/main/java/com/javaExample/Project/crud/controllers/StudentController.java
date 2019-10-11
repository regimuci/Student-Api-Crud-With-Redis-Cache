package com.javaExample.Project.crud.controllers;

import com.javaExample.Project.crud.entities.Student;
import com.javaExample.Project.crud.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;

@RestController
public class StudentController {
    @Autowired
    private StudentService service;

    @RequestMapping("/Students")
    public ResponseEntity<ArrayList<Student>> getStudents(){
        ArrayList<Student> allStudents = service.getAllStudents();
        return new ResponseEntity<ArrayList<Student>>(allStudents,HttpStatus.OK);
    }

    @RequestMapping("/Student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id,WebRequest request){
        Student student = service.getStudentById(id);
        return new ResponseEntity<Student>(student,HttpStatus.OK);
    }

    @PostMapping("/Student")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student s = service.createStudent(student);
        return new ResponseEntity<Student>(s,HttpStatus.CREATED);
    }

    @PutMapping("/Student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id,@RequestParam String name,
                                                 @RequestParam double average){
        Student s = service.updateStudent(id,name,average);
        return new ResponseEntity<Student>(s,HttpStatus.OK);
    }

    @DeleteMapping("/Student/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id){
        service.deleteStudent(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("Students/deleteAll")
    public ResponseEntity<Void> deleteAllStudents(){
        service.deleteAllStudents();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
