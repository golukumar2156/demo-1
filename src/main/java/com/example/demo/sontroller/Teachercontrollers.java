package com.example.demo.sontroller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Teacherlogin;
import com.example.demo.dto.Teacherregister;
import com.example.demo.dto.Teacherresponse;
import com.example.demo.serviceimpl.TeacherserviceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/teacher")
@AllArgsConstructor
public class Teachercontrollers {
	
     private final TeacherserviceImpl teacherserviceImpl;
     
     @PostMapping("/created")
     public ResponseEntity<Teacherresponse> register(@RequestBody Teacherregister reg ){
    	   Teacherresponse resopTeacherresponse=teacherserviceImpl.register(reg);
    	        return ResponseEntity.ok(resopTeacherresponse);
     }
     
     @PostMapping("/login")
     public ResponseEntity<Teacherresponse> login(@Valid @RequestBody Teacherlogin log){
    	         Teacherresponse teacherresponse=teacherserviceImpl.login(log);
    	         return ResponseEntity.ok(teacherresponse);
     }
     
     @GetMapping("/all")
     public ResponseEntity<List<Teacherresponse>> getallteacher(){
    	      return ResponseEntity.ok(teacherserviceImpl.getallTeacher());
     }
     
     @PutMapping("/update/{email}")
     public ResponseEntity<Teacherresponse> update(@RequestBody Teacherregister upd,@PathVariable String email){
    	     Teacherresponse teacherresponse=teacherserviceImpl.update(upd, email);
    	     return ResponseEntity.ok(teacherresponse);
     }
     
     @DeleteMapping("/delete/{roll}")
     public ResponseEntity<Void> delete(@PathVariable int roll){
    	       teacherserviceImpl.delete(roll);
    	       return ResponseEntity.noContent().build();
     }
     
}
