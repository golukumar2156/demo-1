package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Teacherlogin {
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
    private String email;
	@NotBlank(message = "pwd is required")
    private String pwd;
    
}
