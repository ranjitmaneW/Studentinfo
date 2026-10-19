package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.Student;

public interface StudentService {
	
	
	Student  savedata(Student st);
	List<Student> getAlldat(Student st);
	
	
   String  deleteById(Long rollno );
	
	Optional<Student> getbyId(Long rollno);  // 
	
	
	public String updatebyStudendid(Long rollno ,Student student );
	
	 public List<Student> getStudentsByName(String name);
	  List<Student> findBySalaryBetween(int minSalary,int maxSalary);


}
