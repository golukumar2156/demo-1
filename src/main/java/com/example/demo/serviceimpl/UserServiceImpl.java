package com.example.demo.serviceimpl;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.config.JwtProvider;
import com.example.demo.domain.UserRole;
import com.example.demo.exception.Exceptionhandle;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;


	@Override
	public User getUserByEmail(String email) throws Exceptionhandle {
		User user=userRepository.findByEmail(email);
		if(user==null){
			throw new Exceptionhandle("User not found with email: "+email);
		}
		return user;
	}

	@Override
	public User getUserFromJwtToken(String jwt) throws Exceptionhandle {
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		User user = userRepository.findByEmail(email);
		if(user==null) throw new Exceptionhandle("user not exist with email "+email);
		return user;
	}

	@Override
	public User getUserById(Long id) throws Exceptionhandle {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public Set<User> getUserByRole(UserRole role) throws Exceptionhandle {
		return userRepository.findByRole(role);
	}

	@Override
	public User getCurrentUser() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user= userRepository.findByEmail(email);
		if(user == null) {
			throw new EntityNotFoundException("User not found");
		}
		return user;
	}

	@Override
	public List<User> getUsers() throws Exceptionhandle {
		return userRepository.findAll();
	}
}
