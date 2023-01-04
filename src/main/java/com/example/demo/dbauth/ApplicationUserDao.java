package com.example.demo.dbauth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserDao extends JpaRepository<UserRole, Integer>{
     Optional<UserRole> findByUsername(String username);
}
