package com.hit.buoi5.repository;

import com.hit.buoi5.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);
    Boolean existsByUsername(String username);
}
