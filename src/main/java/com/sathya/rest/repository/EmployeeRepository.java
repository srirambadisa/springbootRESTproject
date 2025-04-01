package com.sathya.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sathya.rest.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{

	Optional<Employee> findByEmail(String email);
	
	Optional<Employee> findByName(String name);

	List<Employee> findByDepartmentAndGender(String department, String gender);

	List<Employee> findByDepartmentOrGender(String department, String gender);

	List<Employee> findBySalaryBetween(double minSalary, double maxSalary);

	List<Employee> findBySalaryGreaterThan(double salary);

	List<Employee> findBySalaryLessThan(double salary);

	void deleteByEmail(String email);

	
	
}

