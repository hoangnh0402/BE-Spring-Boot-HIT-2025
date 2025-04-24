package com.hit.buoi4.service;

import com.hit.buoi4.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    public List<Student> getAllStudents();
    public Optional<Student> getStudentById(Integer id);
    public Student addStudent(Student student);
    public Student updateStudent(Integer id, Student student);
    public void deleteStudent(Integer id);
}
