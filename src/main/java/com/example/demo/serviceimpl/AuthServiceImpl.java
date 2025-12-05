package com.example.demo.serviceimpl;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.JwtProvider;
import com.example.demo.domain.UserRole;
import com.example.demo.dto.UserDTO;
import com.example.demo.exception.Exceptionhandle;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.response.AuthResponse;
import com.example.demo.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserImplementation customUserImplementation;

    @Override
    public AuthResponse signup(UserDTO req) throws Exceptionhandle {

        User user = userRepository.findByEmail(req.getEmail());
        if(user != null) {
            throw new Exceptionhandle("Email id already registered ");
        }

        if(req.getRole().equals(UserRole.ROLE_ADMIN)){
            throw new Exceptionhandle("Role admin is not allowed");
        }


        User createdUser = new User();
        createdUser.setEmail(req.getEmail());
        createdUser.setPassword(passwordEncoder.encode(req.getPassword()));
        createdUser.setCreatedAt(LocalDateTime.now());
        createdUser.setPhone(req.getPhone());
        createdUser.setFullName(req.getFullName());
        createdUser.setLastLogin(LocalDateTime.now());
        createdUser.setRole(req.getRole());


        User savedUser = userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse response = new AuthResponse();
        response.setTitle("Welcome " + createdUser.getEmail());
        response.setMessage("Register success");
        response.setUser(UserMapper.toDTO(savedUser));
        response.setJwt(jwt);
        return response;
    }

    @Override
    public AuthResponse login(String username, String password) throws Exceptionhandle {
        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role =  authorities.iterator().next().getAuthority();
        String token = jwtProvider.generateToken(authentication);

        User user = userRepository.findByEmail(username);

//        update last Login
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        AuthResponse response = new AuthResponse();
        response.setTitle("Login success");
        response.setMessage("Welcome Back" + username);
        response.setJwt(token);
        response.setUser(UserMapper.toDTO(user));

        return response;
    }

    public Authentication authenticate(String email, String password) throws Exceptionhandle {

        UserDetails userDetails = customUserImplementation.loadUserByUsername(email);
        if(userDetails == null) {
            throw new Exceptionhandle("email id doesn't exist "+ email);
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new Exceptionhandle("Wrong Password ");
        }
        return new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());
    }

   


}
