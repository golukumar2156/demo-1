package com.example.demo.service;


import java.util.List;

import com.example.demo.dto.Teacherlogin;
import com.example.demo.dto.Teacherregister;
import com.example.demo.dto.Teacherresponse;

public interface Teacherservice {
     Teacherresponse register(Teacherregister reg);
     Teacherresponse  login(Teacherlogin log); 
     Teacherresponse getteacher(int roll);
     List<Teacherresponse> getallTeacher();
     Teacherresponse update(Teacherregister update,String email);
     void delete(int roll);
}
