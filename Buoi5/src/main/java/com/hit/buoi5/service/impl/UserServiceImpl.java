package com.hit.buoi5.service.impl;

import com.hit.buoi5.domain.entity.User;
import com.hit.buoi5.repository.UserRepository;
import com.hit.buoi5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User addUser(User request) {
        User user = new User();

        if(userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Existing Username");
        }
        user.setPassword(request.getPassword());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Integer id, User updateUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updateUser.getUsername());
            user.setPassword(updateUser.getPassword());
            user.setEmail(updateUser.getEmail());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
