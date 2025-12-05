package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.domain.UserRole;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String phone;
    private String fullName;
    private UserRole role;
    private LocalDateTime lastLogin;
    private Long teacherId;


    public UserDTO(Long id, String email, String fullName,
                   UserRole role, String branchName,
                   Long teacherId, LocalDateTime lastLogin) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
        this.teacherId = teacherId;
        this.password = null;
        this.phone = null;
        this.lastLogin=lastLogin;

    }
}
