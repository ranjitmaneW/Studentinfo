package com.example.demo.Repostiory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Student;
@Repository
public interface StudRepo extends JpaRepository<Student, Long>{
	
	Student findByEmail(String email);
	
	//public List<Student> findByName(String name);
	
	public Optional<Student> findByName(String name);
	
	 @Query("SELECT s FROM Student s WHERE s.name = :name")
	    List<Student> findByStudentName(@Param("name") String name);
	 
	  List<Student> findBySalaryBetween(int minSalary,int maxSalary);
	  
	  
	 
	  

	    @Query("SELECT s FROM Student s WHERE " +
	           "LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	           "LOWER(s.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	           "CAST(s.salary AS string) LIKE CONCAT('%', :keyword, '%')")
	    List<Student> searchStudents(@Param("keyword") String keyword);
	  
	      
	    
	    
}