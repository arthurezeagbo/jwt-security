package com.example.securitydemo.repository;

import com.example.securitydemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
