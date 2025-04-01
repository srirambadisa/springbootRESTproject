package com.sathya.rest.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sathya.rest.entity.Employee;
import com.sathya.rest.errorResponse.ErrorResponse;
import com.sathya.rest.service.EmployeeService;

@RestController
public class EmployeeController 
{
	@Autowired
	EmployeeService employeeService;

	@PostMapping("/saveemployee")
	public ResponseEntity<Employee> saveEmployeeData(@RequestBody Employee employee)
	{
		Employee emp=employeeService.saveEmployeeData(employee);
		return ResponseEntity.status(HttpStatus.CREATED).header("info", "Data saved successfully...").body(emp);
	}

	@PostMapping("/saveallemployee")
	public ResponseEntity<List<Employee>> saveEmployeesData(@RequestBody List<Employee> employees)
	{
		List<Employee> emps=employeeService.saveEmployeesData(employees);
		return ResponseEntity.status(HttpStatus.CREATED)
				.header("info", "Data saved successfully...")
				.body(emps);
	}

	@GetMapping("/getempbyemail/{email}")
	public ResponseEntity<?> getEmpByEmail(@PathVariable("email") String email)
	{
		Optional<Employee> optional=employeeService.getEmpByEmail(email);
		if(optional.isPresent())
		{
			Employee emp=optional.get();
			return ResponseEntity.status(HttpStatus.OK)
					.header("info", "Data retrived successfully...")
					.body(emp);
		}
		else
		{
			ErrorResponse errorResponse=new ErrorResponse();
			errorResponse.setTimeStamp(LocalDateTime.now());
			errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
			errorResponse.setErrorMessage("no employee found with "+email);

			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.header("info", "no employee")
					.body(errorResponse);
		}
	}

	@GetMapping("/getempbyname/{name}")
	public ResponseEntity<?> getEmpByName(@PathVariable("name") String name)
	{
		Optional<Employee> optional=employeeService.getEmpByName(name);
		if(optional.isPresent())
		{
			Employee emp=optional.get();
			return ResponseEntity.status(HttpStatus.OK)
					.header("info", "Data retrived successfully...")
					.body(emp);
		}
		else
		{
			ErrorResponse errorResponse=new ErrorResponse();
			errorResponse.setTimeStamp(LocalDateTime.now());
			errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
			errorResponse.setErrorMessage("no employee found with "+name);

			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.header("info", "no employee")
					.body(errorResponse);
		}
	}



	@GetMapping("/getempbydeptandgender")
	public ResponseEntity<List<Employee>> getEmp1(@RequestParam String department,String gender)
	{
		List<Employee> emps=employeeService.getEmpByDepartmentAndGender(department,gender);
		return ResponseEntity.status(HttpStatus.CREATED)
				.header("info", "Data saved successfully...")
				.body(emps);
	}
	@GetMapping("/getempbydeptorgender")
	public ResponseEntity<List<Employee>> getEmp2(@RequestParam String department,String gender)
	{
		List<Employee> emps=employeeService.getEmpByDepartmentOrGender(department,gender);
		return ResponseEntity.status(HttpStatus.CREATED)
				.header("info", "Data saved successfully...")
				.body(emps);
	}
	@GetMapping("/getempbysalbtw")
	public ResponseEntity<List<Employee>> getEmp3(@RequestParam double minSalary,double maxSalary)
	{
		List<Employee> emps=employeeService.getEmpBySalaryBtw(minSalary,maxSalary);
		return ResponseEntity.status(HttpStatus.CREATED)
				.header("info", "Data saved successfully...")
				.body(emps);
	}
	@GetMapping("/getempbysalgre")
	public ResponseEntity<List<Employee>> getEmp4(@RequestParam double salary)
	{
		List<Employee> emps=employeeService.getEmpBySalaryGreater(salary);
		return ResponseEntity.status(HttpStatus.CREATED)
				.header("info", "Data saved successfully...")
				.body(emps);
	}
	@GetMapping("/getempbysalless")
	public ResponseEntity<List<Employee>> getEmp5(@RequestParam double salary)
	{
		List<Employee> emps=employeeService.getEmpBySalaryLess(salary);
		return ResponseEntity.status(HttpStatus.CREATED)
				.header("info", "Data saved successfully...")
				.body(emps);
	}

	@DeleteMapping("/delempbyid/{id}")
	public ResponseEntity delEmpById(@PathVariable("id") long id)
	{
		boolean status=employeeService.deleteById(id);
		if(status)
		{
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.header("info", "Data deleted")
					.build();
		}
		else
		{
			ErrorResponse errorResponse=new ErrorResponse();
			errorResponse.setTimeStamp(LocalDateTime.now());
			errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
			errorResponse.setErrorMessage("no employee found with "+id);

			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.header("info", "no employee")
					.body(errorResponse);
		}
	}
	
	@DeleteMapping("/delempbyemail")
	public ResponseEntity delEmpById(@RequestParam String email)
	{
		boolean status=employeeService.deleteByEmail(email);
		if(status)
		{
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.header("info", "Data deleted")
					.build();
		}
		else
		{
			ErrorResponse errorResponse=new ErrorResponse();
			errorResponse.setTimeStamp(LocalDateTime.now());
			errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
			errorResponse.setErrorMessage("no employee found with "+email);

			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.header("info", "no employee")
					.body(errorResponse);
		}
	}


	@DeleteMapping("/deleteall")
	public ResponseEntity<?> deleteall()
	{

		boolean status=employeeService.deleteAll();
		if(status)
		{
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.header("info", "Data deleted")
					.build();
		}
		else
		{
			ErrorResponse errorResponse=new ErrorResponse();
			errorResponse.setTimeStamp(LocalDateTime.now());
			errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
			errorResponse.setErrorMessage("no employees found");

			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.header("info", "no employee")
					.body(errorResponse);
		}
		
	}
	
	@PutMapping("/updateemp/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable("id") long id, @RequestBody Employee newEmployee)
	{
		Employee employee=employeeService.updateEmployee(id,newEmployee);
		if(employee!=null)
		{
			return ResponseEntity.status(HttpStatus.OK)
					.header("info", "Data updated successfully...")
					.body(employee);
		}
		else
		{
			ErrorResponse errorResponse=new ErrorResponse();
			errorResponse.setTimeStamp(LocalDateTime.now());
			errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
			errorResponse.setErrorMessage("no employees found");

			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.header("info", "no employee found")
					.body(errorResponse);
		}
	}
		
		
		@PutMapping("/updateemp")
		public ResponseEntity<?> updateEmployee(@RequestParam("email") String email, @RequestBody Employee newEmployee)
		{
			Employee employee=employeeService.updateEmployee(email,newEmployee);
			if(employee!=null)
			{
				return ResponseEntity.status(HttpStatus.OK)
						.header("info", "Data updated successfully...")
						.body(employee);
			}
			else
			{
				ErrorResponse errorResponse=new ErrorResponse();
				errorResponse.setTimeStamp(LocalDateTime.now());
				errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
				errorResponse.setErrorMessage("no employees found");

				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.header("info", "no employee found")
						.body(errorResponse);
			}
	}






}
