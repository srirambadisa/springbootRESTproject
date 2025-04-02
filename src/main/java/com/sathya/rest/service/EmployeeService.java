package com.sathya.rest.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathya.rest.entity.Employee;
import com.sathya.rest.repository.EmployeeRepository;

@Service
public class EmployeeService 
{
	@Autowired
	EmployeeRepository employeeRepository;

	public Employee saveEmployeeData(Employee employee) 
	{
		Employee emp=employeeRepository.save(employee);
		return emp;
		
	}

	public List<Employee> saveEmployeesData(List<Employee> employees) 
	{
		List<Employee> emps=employeeRepository.saveAll(employees);
		return emps;
	}

	public Optional<Employee> getEmpByEmail(String email) 
	{
		return employeeRepository.findByEmail(email);
		
	}
	
	public Optional<Employee> getEmpByName(String name) 
	{
		return employeeRepository.findByName(name);
		
	}

	public List<Employee> getEmpByDepartmentAndGender(String department, String gender) 
	{
		List<Employee> emps=employeeRepository.findByDepartmentAndGender(department,gender);
		return emps;
	}

	public List<Employee> getEmpByDepartmentOrGender(String department, String gender) 
	{
		List<Employee> emps=employeeRepository.findByDepartmentOrGender(department,gender);
		return emps;
	}

	public List<Employee> getEmpBySalaryBtw(double minSalary, double maxSalary) 
	{
		List<Employee> emps=employeeRepository.findBySalaryBetween(minSalary,maxSalary);
		return emps;
	}

	public List<Employee> getEmpBySalaryGreater(double salary) 
	{
		List<Employee> emps=employeeRepository.findBySalaryGreaterThan(salary);
		return emps;
	}

	public List<Employee> getEmpBySalaryLess(double salary) 
	{
		List<Employee> emps=employeeRepository.findBySalaryLessThan(salary);
		return emps;
	}

	public boolean deleteById(long id) 
	{
		boolean status=employeeRepository.existsById(id);
		if(status)
		{
			employeeRepository.deleteById(id);
			return true;
		}
		else
		{
			return false;
		}
		
	}

	public boolean deleteAll() 
	{
		long count=employeeRepository.count();
		if(count>0)
		{
			employeeRepository.deleteAll();
			return true;
		}
		else
		{
			return false;
		}
		
	}

	public Employee updateEmployee(long id, Employee newEmployee) 
	{
		Optional<Employee> optionalEmployee=employeeRepository.findById(id);
		if(optionalEmployee.isPresent())
		{
			Employee employee=optionalEmployee.get();
			employee.setName(newEmployee.getName());
			employee.setSalary(newEmployee.getSalary());
			employee.setGender(newEmployee.getGender());
			employee.setDepartment(newEmployee.getDepartment());
			employee.setEmail(newEmployee.getEmail());
			
			employeeRepository.save(employee);
			
			return employee;
		}
		else
		{
			return null;
		}
		
	}

	public Employee updateEmployee(String email, Employee newEmployee) 
	{
		Optional<Employee> optionalEmployee=employeeRepository.findByEmail(email);
		if(optionalEmployee.isPresent())
		{
			Employee employee=optionalEmployee.get();
			employee.setName(newEmployee.getName());
			employee.setSalary(newEmployee.getSalary());
			employee.setGender(newEmployee.getGender());
			employee.setDepartment(newEmployee.getDepartment());
			employee.setEmail(newEmployee.getEmail());
			
			employeeRepository.save(employee);
			
			return employee;
		}
		else
		{
			return null;
		}
	}

	public boolean deleteByEmail(String email) 
	{
		Optional<Employee> optionalEmployee=employeeRepository.findByEmail(email);
		if(optionalEmployee.isPresent())
		{
			employeeRepository.delete(optionalEmployee.get());
			return true;
		}
		else
		{
			return false;
		}
	}

	public Optional<Employee> partialUpdateEmp(long id, Map<String, Object> updates) 
	{
		Optional<Employee> optionalEmployee=employeeRepository.findById(id);
		if(optionalEmployee.isPresent())
		{
			Employee existingEmp=optionalEmployee.get();
			updates.forEach((key,value) -> 
			{
				switch (key) 
				{
				case "name": 
					existingEmp.setName((String)value);
					break;
				case "salary": 
					existingEmp.setSalary((double)value);
					break;
				case "department": 
					existingEmp.setDepartment((String)value);
					break;
				case "gender": 
					existingEmp.setGender((String)value);
					break;
				case "email":
		            existingEmp.setEmail((String) value);
		            break;
				
				default:
					throw new IllegalArgumentException("Unexpected value: " + key);
				}
			});
			Employee employee=employeeRepository.save(existingEmp);
			return Optional.of(employee);	
		}
		else
		{
			return Optional.empty();
		}
		
	}

}
