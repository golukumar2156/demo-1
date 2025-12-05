package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.exception.Exceptionhandle;
import com.example.demo.response.AuthResponse;

public interface AuthService {
    AuthResponse login(String username, String password) throws Exceptionhandle;
    AuthResponse signup(UserDTO req) throws Exceptionhandle;

}
