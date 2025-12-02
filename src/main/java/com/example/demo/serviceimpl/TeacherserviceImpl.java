package com.example.demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.Teacherlogin;
import com.example.demo.dto.Teacherregister;
import com.example.demo.dto.Teacherresponse;
import com.example.demo.exception.Exceptionhandle;
import com.example.demo.mapper.Teachermapper;
import com.example.demo.model.Teacher;
import com.example.demo.repo.TeacherRepository;
import com.example.demo.service.Teacherservice;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TeacherserviceImpl implements Teacherservice {


	private final TeacherRepository teacherRepository;

	@Override
	public Teacherresponse register(Teacherregister reg) {
		teacherRepository.findByEmail(reg.getEmail()).ifPresent(
				t->{
					 throw new Exceptionhandle("email is already exist");
				});
		
//		 DTO===>toEntity covert
		Teacher tech=Teachermapper.toEntity(reg);
//	     toEntity data saved in DB
		Teacher savedTeacher=teacherRepository.save(tech);
//		 return DTO data response
		return Teachermapper.toDTo(savedTeacher);
	}

	@Override
	public Teacherresponse login(Teacherlogin log) {
	 Teacher teacher=teacherRepository.findByEmail(log.getEmail()).orElseThrow(()->
			  new Exceptionhandle("email not found")
			 );
	 if(!teacher.getPwd().equals(log.getPwd())) {
		 throw new Exceptionhandle("password is not found");
	 }
		
		return Teachermapper.toDTo(teacher);
	}
  
	@Override
	public Teacherresponse getteacher(int roll) {
		Teacher teacher=teacherRepository.findById(roll).orElseThrow(()->
		         new Exceptionhandle("I'D is not found of users")
				);
		
		return Teachermapper.toDTo(teacher);
	}

	@Override
	public List<Teacherresponse> getallTeacher() {
		List<Teacher> teachers=teacherRepository.findAll();
		List<Teacherresponse> responselist=new ArrayList<>();
		for(Teacher t:teachers) {
			responselist.add(Teachermapper.toDTo(t));
		}
		return responselist;
	}

	@Override
	public Teacherresponse update(Teacherregister update,String email) {
		Teacher updateteacher=teacherRepository.findByEmail(update.getEmail()).orElseThrow(()->
		      new Exceptionhandle("email is not found !")
				);
		updateteacher.setName(update.getName());
		updateteacher.setFathername(update.getFathername());
		updateteacher.setMothername(update.getMothername());
		updateteacher.setAddress(update.getAddress());
		updateteacher.setEmail(update.getEmail());
		updateteacher.setPhone(update.getPhone());
		Teacher  res=teacherRepository.save(updateteacher);
		Teacherresponse result=Teachermapper.toDTo(updateteacher);
		return result;
	}

	@Override
	public void delete(int roll) {
	    Teacher teacher = teacherRepository.findById(roll)
	            .orElseThrow(() -> new Exceptionhandle("Roll not found"));
	    teacherRepository.delete(teacher);
	}
    


}
