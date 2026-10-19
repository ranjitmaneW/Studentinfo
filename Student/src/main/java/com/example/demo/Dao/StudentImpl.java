package com.example.demo.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Student;
import com.example.demo.Repostiory.StudRepo;
import com.example.demo.Service.StudentService;
@Service
public class StudentImpl implements StudentService {

	
	@Autowired
	StudRepo srepo;
	
	@Override
	public Student savedata(Student st) {

		
		return srepo.save(st);
	}

	@Override
	public List<Student> getAlldat(Student st) {
		// TODO Auto-generated method stub
		return srepo.findAll();
	}

	@Override
	public String deleteById(Long rollno) {
	    if (srepo.existsById(rollno)) {
	        srepo.deleteById(rollno); // Perform deletion
	        return "Student with roll number " + rollno + " deleted successfully!";
	    } else {
	        return "Student with roll number " + rollno + " not found!";
	    }
	}
	@Override
	public Optional<Student> getbyId(Long rollno) {
		// TODO Auto-generated method stub
		return srepo.findById(rollno);
	}
	@Override
	public String updatebyStudendid(Long rollno, Student student) {
		Optional<Student> stu =	   srepo.findById(rollno);//

		if(stu.isPresent()) 
		{
			Student  ss  	=stu.get();  //ss 
		
			ss.setName(student.getName());
			ss.setEmail(student.getEmail());
		  srepo.save(student);
		
		  return "Student id updated :" +rollno;
		}
		
		else{
	
		return  "Student id  not found :" +rollno;
		
		}
		
		
	
		
		
		
	}

	
	
	public List<Student> search(String keyword) {
		
		
		
        return srepo.searchStudents(keyword);
    }
	
	
	
	
	
	

 public 	Student GetfindByEmail(String email) {
		
		
		
		return srepo.findByEmail(email);
		
		
		
		
		
	}
 
 
 @Override
 public List<Student> getStudentsByName(String name) {
     return srepo.findByStudentName(name);
 }

@Override
public List<Student> findBySalaryBetween(int minSalary, int maxSalary) {
	// TODO Auto-generated method stub
	return srepo.findBySalaryBetween(minSalary, maxSalary);
}
}
	
	
	
	
	
	
	
	
	

   /* @Override
    public String updateById(Long rollno, Student st) {
        Optional<Student> existingStudent = srepo.findById(rollno);
        if (existingStudent.isPresent()) {
            Student updatedStudent = existingStudent.get();
            updatedStudent.setName(st.getName());
            updatedStudent.setEmail(st.getEmail());
            srepo.save(updatedStudent);
            return "Student updated successfully.";
        } else {
            return "Student with rollno " + rollno + " not found.";
        }
    }	
	*/
	


	
	


