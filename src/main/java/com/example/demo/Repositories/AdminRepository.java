package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.classes.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmailId(String email);
    Admin findByEmailIdAndPassword(String email, String password);
}
