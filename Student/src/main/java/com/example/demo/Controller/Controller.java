package com.example.demo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dao.StudentImpl;
import com.example.demo.Entity.ApiResponse;
import com.example.demo.Entity.Student;
import com.example.demo.StudentNotFoundException.StudentNotFoundException;




@RestController
@RequestMapping("/stucon")
public class Controller {
	

@Autowired
	StudentImpl slmpl;
/*
	@PostMapping("/savedata")
	public Student studentdatasave(@RequestBody Student st ) 
	
	{
		return slmpl.savedata(st);
		
	}
*/
@GetMapping("/search")
public List<Student> searchStudents(@RequestParam String keyword) {
    return slmpl.search(keyword);
}



@PostMapping("/savedata1")
public ResponseEntity<Student> Studentsavedata(@RequestBody Student st )
{
	  Student savedata = slmpl.savedata(st);
	  
	
	
	
	return new ResponseEntity<Student>(savedata, HttpStatus.CREATED);
	
	
	
}

 


/*
@PostMapping("/savedata")
public ResponseEntity<Student> Studentdatasave(@RequestBody Student st  ){
	
Student newStudentResponse	=slmpl.savedata(st);
	
	return new ResponseEntity<Student>(newStudentResponse, HttpStatus.CREATED);
	
}

*/
	
	/*@PostMapping("/savedata")
	public ResponseEntity<Student> StudentAlldatasave(@RequestBody Student st ){
		Student savedataStudent		=slmpl.savedata(st);
		
		return new ResponseEntity<Student>(savedataStudent, HttpStatus.CREATED);
	}*/


@GetMapping("/salary-range")
public ResponseEntity<ApiResponse> getStudentsBySalaryRange(
        @RequestParam int minSalary,
        @RequestParam int maxSalary) {
    
    List<Student> students = slmpl.findBySalaryBetween(minSalary, maxSalary);
    
    if (students.isEmpty()) {
        ApiResponse response = new ApiResponse("Employee salary between min and max not retrieved", 404, null);
        return ResponseEntity.status(404).body(response);
    }

    ApiResponse response = new ApiResponse("Employee salary between min and max retrieved successfully", 200, students);
    return ResponseEntity.ok(response);
}



/*
@GetMapping("/salary-range")
public ResponseEntity<ApiResponse> getStudentsBySalaryRange(
        @RequestParam int minSalary,
        @RequestParam int maxSalary) {
    
    List<Student> students = slmpl.findBySalaryBetween(minSalary, maxSalary);
    
    if (students.isEmpty()) {
        ApiResponse response = new ApiResponse("Employee salary between min and max not retrieved", 404, null);
        return ResponseEntity.status(404).body(response);
    }

    // Get min and max salary using Java 8 Streams
    OptionalInt minSal = students.stream().mapToInt(Student::getSalary).min();
    OptionalInt maxSal = students.stream().mapToInt(Student::getSalary).max();

    // Extract Student IDs and Salaries
    List<Map<String, Object>> employeeSalaryList = students.stream()
            .map(student -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", student.getRollno());
                map.put("salary", student.getSalary());
                return map;
            })
            .collect(Collectors.toList());

    // Create response map
    Map<String, Object> responseData = new HashMap<>();
    responseData.put("minSalary", minSal.orElse(0));  // Default to 0 if not found
    responseData.put("maxSalary", maxSal.orElse(0));
    responseData.put("employees", employeeSalaryList);

    ApiResponse response = new ApiResponse("Employee salary between min and max retrieved successfully", 200, responseData);
    return ResponseEntity.ok(response);
}
   /* // 1. Add Employee
	@PostMapping("/savedata")
    public ResponseEntity<ApiResponse> addEmployee(@RequestBody Student st) {
    	Student savedEmployee = slmpl.savedata(st);
        ApiResponse response = new ApiResponse("Employee added successfully", 201, savedEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

	*/

    // 2. Get Employee by ID
    @GetMapping("/getStudentId/{rollno}")
    public ResponseEntity<ApiResponse> getEmployee(@PathVariable Long rollno) {
        Optional<Student> employee = slmpl.getbyId(rollno);
        if (employee.isPresent()) {
            ApiResponse response = new ApiResponse("Employee retrieved successfully", 209, employee.get());
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse("Employee not found", 408, null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
    
    

    @GetMapping("/getStudentById2/{rollno}")
    public ResponseEntity<ApiResponse> getStudentById(@PathVariable Long rollno) {
        Optional<Student> student = slmpl.getbyId(rollno);
        
        if (student.isEmpty()) {
            throw new StudentNotFoundException("Student with roll number " + rollno + " not found.");
        }

        ApiResponse response = new ApiResponse("Student retrieved successfully", HttpStatus.OK.value(), student.get());
        return ResponseEntity.ok(response);
    }
    
    
    
    // Global Exception Handler for StudentNotFoundException
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ApiResponse> handleStudentNotFoundException(StudentNotFoundException ex) {
        ApiResponse errorResponse = new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

/*
@PostMapping("/savedata")
public  ResponseEntity<Student> saveStudent(@RequestBody Student st ){
	Student    saveStudent      =slmpl.savedata(st);
	
	return new ResponseEntity<Student>(saveStudent,HttpStatus.CREATED);
	    	
}
*/

	
	/*
	@GetMapping("/alllist")
	public List<Student> getAlldatastudent(Student st){
		return slmpl.getAlldat(st);
		
		
		
	}
	
	*/
  /*  
@GetMapping("/alllist")
public  ResponseEntity<List<Student>> GetAllstudent(Student st){
	List<Student>   Students      =slmpl.getAlldat(st);
	
	
	return new ResponseEntity<List<Student>>(Students, HttpStatus.FOUND);
	
	
	
}*/
// 3. Get All Students
@GetMapping("/alllist")
public ResponseEntity<ApiResponse> getAllStudents(Student st) {
    List<Student> students = slmpl.getAlldat(st);
    ApiResponse response = new ApiResponse("Students retrieved successfully", 200, students);

    
    return ResponseEntity.ok(response);
}
	

	/* @GetMapping
	    public ResponseEntity<List<Student>> getAllStudents() {
	        List<Student> students = studentService.getAllStudents();
	        return new ResponseEntity<>(students, HttpStatus.OK);
	    }
	
	
	*/
	

	/*@DeleteMapping("/deleteid/{rollno}")
	public void studentid(@PathVariable Long rollno) {
		slmpl.deleteById(rollno);
		
		
	}
	
	*/

/*
@DeleteMapping("/deleteid/{rollno}")
public ResponseEntity<String> deletestudentid(@PathVariable Long rollno) {
    String message = slmpl.deleteById(rollno);

    HttpStatus status = message.contains("not found") ? HttpStatus.NOT_FOUND : HttpStatus.OK;
    return new ResponseEntity<String>(message, status);
}
*/
	
	
	// 4. Delete Student by ID
    @DeleteMapping("/deleteid/{rollno}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable Long rollno) {
        String message = slmpl.deleteById(rollno);
        HttpStatus status = message.contains("not found") ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        ApiResponse response = new ApiResponse(message, status.value(), null);
        return ResponseEntity.status(status).body(response);
    }
	
	/*
	// Delete Student
    @DeleteMapping("/{rollno}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long rollno) {
        String message = studentService.deleteStudentById(rollno);
        return new ResponseEntity<>(message, message.equals("Student deleted successfully!") ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
	
	
	*/
	
	/*
	@GetMapping("/getid/{rollno}")
	public Optional<Student>Getbyid(@PathVariable Long rollno){
		
		return slmpl.getbyId(rollno);
		

	}
	*/


	/*@GetMapping("/getid/{rollno}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long rollno){
		Optional<Student> student	=slmpl.getbyId(rollno);
		if(student.isPresent()) {
			
			return new ResponseEntity<Student>(student.get(), HttpStatus.OK);
			
		}
		else {
			
			
		}
		
		return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		
		
		
		
	}
	
	
	
	*/
	/*
	// Get Student by ID
    @GetMapping("/{rollno}")
    public ResponseEntity<?> getStudentById(@PathVariable Long rollno) {
        Optional<Student> student = studentService.getStudentById(rollno);
        if (student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Student not found!", HttpStatus.NOT_FOUND);
        }
    }
	
	
	*/
	/*
	@PutMapping("/updatebyid/{rollno}")
	public String updatebyid(@PathVariable Long rollno ,@RequestBody Student stu){
		
		
		return slmpl.updatebyStudendid(rollno, stu);
		 	
		
		
	}
	*/
	// 5. Update Student by ID
    @PutMapping("/updatebyid/{rollno}")
    public ResponseEntity<ApiResponse> updateStudent(@PathVariable Long rollno, @RequestBody Student stu) {
        String message = slmpl.updatebyStudendid(rollno, stu);
        ApiResponse response = new ApiResponse(message, 200, stu);
        return ResponseEntity.ok(response);
    }


	
	
	/*
	// API to get students dynamically by name
    @GetMapping("/name")
    public List<Student> getStudentsByName(@RequestParam String name) {
        return slmpl.getStudentsByName(name);
    }
	*/
 // 6. Get Students Dynamically by Name
    @GetMapping("/name")
    public ResponseEntity<ApiResponse> getStudentsByName(@RequestParam String name) {
        List<Student> students = slmpl.getStudentsByName(name);
       // ApiResponse response = new ApiResponse("Students retrieved successfully by name", 200, students);
        
        ApiResponse response   = new ApiResponse("Students retrieved successfully by name", 200, students);
        return ResponseEntity.ok(response);
    }


    
    /*
	@GetMapping("/email/{email}")
	Student getEmail(@PathVariable String email){
		
		
		
		return slmpl.GetfindByEmail(email);
		
			
	}
	*/
	  // 7. Get Student by Email
    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse> getStudentByEmail(@PathVariable String email) {
        Student student = slmpl.GetfindByEmail(email);
        if (student != null) {
            ApiResponse response = new ApiResponse("Student found by email", 200, student);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse("Student not found by email", 404, null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
	
	
	
/*
    @PutMapping("/update/{rollno}")
    public String updateStudent(@PathVariable Long rollno, @RequestBody Student student) {
        return slmpl.updateById(rollno, student);
    }
	
	*/
	
}
