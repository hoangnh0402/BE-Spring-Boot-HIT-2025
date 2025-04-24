package com.hit.buoi4.repository;

import com.hit.buoi4.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByNameContaining(String name);
    List<Student> findByClassEntity_Id(Integer classId);
}

