package com.hit.buoi4.controller;

import com.hit.buoi4.dto.ApiResponse;
import com.hit.buoi4.model.Student;
import com.hit.buoi4.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(new ApiResponse<>("Danh sách sinh viên", students));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable Integer id) {
        return studentService.getStudentById(id)
                .map(student -> ResponseEntity.ok(new ApiResponse<>("Sinh viên tìm thấy", student)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>("Không tìm thấy sinh viên", null)));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Student>> addStudent(@RequestBody Student student) {
        Student savedStudent = studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Thêm sinh viên thành công", savedStudent));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(@PathVariable Integer id, @RequestBody Student updatedStudent) {
        return ResponseEntity.ok(new ApiResponse<>("Cập nhật thành công", studentService.updateStudent(id, updatedStudent)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(new ApiResponse<>("Xóa sinh viên thành công", null));
    }
}
