package com.techcode.pro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techcode.pro.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

  @Query("select a from Student a where a.id = ?1")
  Student findById(Long id);

}
