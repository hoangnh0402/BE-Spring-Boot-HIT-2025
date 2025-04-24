package com.hit.buoi5.service;

import com.hit.buoi5.domain.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User getUserById(int id);
    public User addUser(User user);
    public User updateUser(Integer id, User updateUser);
    public void deleteUser(int id);
}
