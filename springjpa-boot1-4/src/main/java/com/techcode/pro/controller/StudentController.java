package com.techcode.pro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techcode.pro.entity.Student;
import com.techcode.pro.entity.StudentBuilder;
import com.techcode.pro.repository.StudentRepository;

@RestController
public class StudentController {

  @Autowired
  StudentRepository studentRepository;

  @RequestMapping(value = "student", method = RequestMethod.POST, produces = "application/json")
  public Student insertStudent(Student student) {
    Student insertedStudent = StudentBuilder.buildUpon(student).build();
    return studentRepository.save(insertedStudent);
  }

  @RequestMapping(value = "student/all", method = RequestMethod.GET, produces = "application/json")
  public List<Student> retrieveStudent() {
    return studentRepository.findAll();
  }

}
