package com.example.DoAn.repository;

import com.example.DoAn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String name);
    User getUserByEmail(String name);
}
