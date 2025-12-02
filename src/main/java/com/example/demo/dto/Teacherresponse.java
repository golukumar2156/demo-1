package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Teacherresponse {
    private int id;
    private String name;
    private String  fathername ;
    private String mothername;
    private String address;
    private long phone;
    private String email;
}
