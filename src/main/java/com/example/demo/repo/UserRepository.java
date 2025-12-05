package com.example.demo.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.UserRole;
import com.example.demo.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
	Set<User> findByRole(UserRole role);
	User findByEmail(String email);

}
