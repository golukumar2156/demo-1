package com.example.demo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.UserRole;
import com.example.demo.dto.UserDTO;
import com.example.demo.exception.Exceptionhandle;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jdk.jshell.spi.ExecutionControl.UserException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;	

	
	@GetMapping("/api/users/profile")
	public ResponseEntity<UserDTO> getUserProfileFromJwtHandler(
			@RequestHeader("Authorization") String jwt) throws Exceptionhandle {
		User user = userService.getUserFromJwtToken(jwt);
		UserDTO userDTO=UserMapper.toDTO(user);

		return new ResponseEntity<>(userDTO,HttpStatus.OK);
	}

	@GetMapping("/api/users/customer")
	public ResponseEntity<Set<UserDTO>> getCustomerList(
			@RequestHeader("Authorization") String jwt) throws Exceptionhandle {
		Set<User> users = userService.getUserByRole(UserRole.ROLE_TEACHER);
		Set<UserDTO> userDTO=UserMapper.toDTOSet(users);

		return new ResponseEntity<>(userDTO,HttpStatus.OK);
	}

	@GetMapping("/api/users/cashier")
	public ResponseEntity<Set<UserDTO>> getCashierList(
			@RequestHeader("Authorization") String jwt) throws Exceptionhandle {
		Set<User> users = userService.getUserByRole(UserRole.ROLE_STUDENT);
		Set<UserDTO> userDTO=UserMapper.toDTOSet(users);

		return new ResponseEntity<>(userDTO,HttpStatus.OK);
	}

	@GetMapping("/users/list")
	public ResponseEntity<List<User>> getUsersListHandler(
			@RequestHeader("Authorization") String jwt) throws Exceptionhandle {
		List<User> users = userService.getUsers();

		return new ResponseEntity<>(users,HttpStatus.OK);
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<UserDTO> getUserByIdHandler(
			@PathVariable Long userId
	) throws UserException {
		User user = userService.getUserById(userId);
		UserDTO userDTO=UserMapper.toDTO(user);

		return new ResponseEntity<>(userDTO,HttpStatus.OK);
	}




}
