package com.example.demo.service;


import java.util.List;
import java.util.Set;

import com.example.demo.domain.UserRole;
import com.example.demo.exception.Exceptionhandle;
import com.example.demo.model.User;


public interface UserService {
	User getUserByEmail(String email) throws Exceptionhandle;
	User getUserFromJwtToken(String jwt) throws Exceptionhandle;
	User getUserById(Long id) throws Exceptionhandle;
	Set<User> getUserByRole(UserRole role) throws Exceptionhandle;
	List<User> getUsers() throws Exceptionhandle;
	User getCurrentUser() throws Exceptionhandle;
}
