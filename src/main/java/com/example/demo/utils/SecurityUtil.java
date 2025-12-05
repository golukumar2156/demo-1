package com.example.demo.utils;

import org.springframework.stereotype.Component;

import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityUtil {


    private final UserService userService;



}