package com.example.demo.mapper;

import com.example.demo.dto.Teacherregister;
import com.example.demo.dto.Teacherresponse;
import com.example.demo.model.Teacher;

public class Teachermapper {
//	entity -> DTO
    public static Teacherresponse toDTo(Teacher teacher) {
    	   return Teacherresponse.builder()
    			   .id(teacher.getId())
    			   .name(teacher.getName())
    			   .fathername(teacher.getFathername())
    			   .mothername(teacher.getMothername())
    			   .address(teacher.getAddress())
    			   .phone(teacher.getPhone())
    			   .email(teacher.getEmail())
    			   .build();
    }
    
    
//    DTO->entity
    public static Teacher toEntity(Teacherregister res) {
    	      return Teacher.builder()
    	    		  .name(res.getName())
    	    		  .fathername(res.getFathername())
    	    		  .mothername(res.getMothername())
    	    		  .phone(res.getPhone())
    	    		  .email(res.getEmail())
    	    		  .address(res.getAddress())
    	    		  .pwd(res.getPwd())
    	    		  .build();
    }
}
