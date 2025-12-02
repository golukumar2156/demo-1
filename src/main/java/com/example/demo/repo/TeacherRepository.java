package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Teacher;


public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
     Optional<Teacher> findByEmail(String email);
     Optional<Teacher> findByPwd(String pwd);
}
