package com.hit.buoi5.controller;

import com.hit.buoi5.domain.dto.ApiRespone;
import com.hit.buoi5.domain.entity.User;
import com.hit.buoi5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiRespone<List<User>>> getAll(){
        List<User> students = userService.getAllUsers();
        return ResponseEntity.ok(new ApiRespone<>("Danh sach", students));
    }
}
