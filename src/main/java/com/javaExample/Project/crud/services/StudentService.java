package com.javaExample.Project.crud.services;

import com.javaExample.Project.crud.entities.Student;
import com.javaExample.Project.crud.exceptions.StudentNotFoundException;
import com.javaExample.Project.crud.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    @Cacheable(value= "allStudentsCache", unless= "#result.size() == 0")
    public ArrayList<Student> getAllStudents(){
        return (ArrayList<Student>) repository.findAll();
    }

    @Cacheable(value = "studentsCache",key = "#id")
    public Student getStudentById(int id){
        Optional<Student> student = repository.findById(id);
        if(!student.isPresent()){
            throw new StudentNotFoundException("Student with id = "+id+" doesn't exist");
        }
        return student.get();
    }

    @Caching(
            put = {@CachePut(value = "studentsCache",key = "#student.id")},
            evict = {@CacheEvict(value = "allStudentsCache",allEntries = true)}
    )
    public Student createStudent(Student student){
        return repository.save(student);
    }

    @Caching(
            put = {@CachePut(value = "studentsCache",key = "#id")},
            evict = {@CacheEvict(value = "allStudentsCache",allEntries = true)}
    )
    public Student updateStudent(int id, String name,double average){
        Optional<Student> student = repository.findById(id);
        if(!student.isPresent()){
            throw new StudentNotFoundException("Student with id = "+id+" doesn't exist");
        }
        Student s = student.get();
        s.setName(name);
        s.setAverage(average);
        return repository.save(s);
    }

    @Caching(
            evict= {
                    @CacheEvict(value= "studentsCache", key= "#id"),
                    @CacheEvict(value= "allStudentsCache", allEntries= true)
            }
    )
    public void deleteStudent(int id){
        Optional<Student> student = repository.findById(id);
        if(!student.isPresent()){
            throw new StudentNotFoundException("Student with id = "+id+" doesn't exist");
        }
        repository.deleteById(id);
    }

    @Caching(
            evict= {
                    @CacheEvict(value= "studentsCache", allEntries = true),
                    @CacheEvict(value= "allStudentsCache", allEntries= true)
            }
    )
    public void deleteAllStudents(){
        repository.deleteAll();
    }


}
