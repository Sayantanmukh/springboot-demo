package com.capgemini.springboot.demo.controller;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.springboot.demo.model.Employee;
import com.capgemini.springboot.demo.service.EmployeeService;

@RestController
public class EmployeeController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeService service;

//	http://localhost:8088/get-employee-by-id/102
	@GetMapping("/get-employee-by-id/{eid}")
	public Employee getEmployeeById(@PathVariable(name="eid") int employeeId) {
		//int employeeId = new Random().nextInt(6) + 100;
		LOG.info("EmployeeController getEmployeeById " + employeeId);
		return service.getEmpById(employeeId);
	}

//	http://localhost:8088/get-all-employees
	@GetMapping("/get-all-employees")
	public List<Employee> getAllEmployees() {
		LOG.info("get-all-employees");
		return service.getAllEmps();
	}

	@PostMapping("/add-employee")
	public Employee addEmployee(@RequestBody Employee employee ) {
		//Employee employee = new Employee();
		LOG.info(employee.toString());
		return service.addEmp(employee);
	}

	public Employee updateEmployee() {
		return null;
	}
	
	public Employee deleteEmployee() {
		return null;
	}

}

//package com.capgemini.springboot.demo.controller;
//
//import java.util.List;
//import java.util.Random;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.capgemini.springboot.demo.model.Employee;
//import com.capgemini.springboot.demo.service.EmployeeService;
//
//@RestController
//public class EmployeeController {
//
//	@Autowired
//	private EmployeeService service;
//
////	http://localhost:8088/get-employee-by-id
//	@GetMapping("/get-employee-by-id")
//	public Employee getEmployeeById() {
//		int employeeId = new Random().nextInt(6) + 100;
//		System.out.println("EmployeeController getEmployeeById " + employeeId);
//		return service.getEmpById(employeeId);
//	}
//
////	http://localhost:8088/get-all-employees
//	@GetMapping("/get-all-employees")
//	public List<Employee> getAllEmployees() {
//		System.out.println("get-all-employees");
//		return service.getAllEmps();
//	}
//
//	@PostMapping("/add-employee")
//	public Employee addEmployee() {
//		Employee employee = new Employee();
//		return service.addEmp(employee);
//	}
//
////	public Employee updateEmployee() {
////	return null;
////}
//
////	public Employee deleteEmployee() {
////	return null;
////}
//
//}

//package com.capgemini.springboot.demo.controller;
//
//import java.util.List;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.capgemini.springboot.demo.model.Employee;
//
//@RestController
//public class EmployeeController {
//
////	http://localhost:8088/get-employee-by-id
//	@GetMapping("/get-employee-by-id")
//	public Employee getEmployeeById() {
//		Employee emp = new Employee(101, "Sonu", 50000);
//		System.out.println(emp.toString());
//		return emp;
//	}
//
////	http://localhost:8088/get-all-employees
//	@GetMapping("/get-all-employees")
//	public List<Employee> getAllEmployees() {
//		System.out.println("get-all-employees");
//		return null;
//	}
//
////  public Employee addEmployee() {
////		return null;
////	}
//
////	public Employee updateEmployee() {
////	return null;
////}
//
////	public Employee deleteEmployee() {
////	return null;
////}
//
//}


//package com.capgemini.springboot.demo.controller;
//
//import java.util.List;
//import java.util.Random;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.capgemini.springboot.demo.model.Employee;
//import com.capgemini.springboot.demo.service.EmployeeService;
//
//@RestController
//public class EmployeeController {
//
//	@Autowired
//	private EmployeeService service;
//
////	http://localhost:8088/get-employee-by-id
//	@GetMapping("/get-employee-by-id")
//	public Employee getEmployeeById() {
//		int employeeId = new Random().nextInt(6) + 100;
//		System.out.println("EmployeeController getEmployeeById " + employeeId);
//		return service.getEmpById(employeeId);
//	}
//
////	http://localhost:8088/get-all-employees
//	@GetMapping("/get-all-employees")
//	public List<Employee> getAllEmployees() {
//		System.out.println("get-all-employees");
//		return service.getAllEmps();
//	}
//	
//	@GetMapping("/add-employee")
//	public Employee addEmployee() {
//	  Employee e=new Employee(106,"Sayantan",10000);
//	  System.out.println("Added an employee");
//	  return service.addEmployee(e);
//		//return null;
//	}
//	@GetMapping("/update-employee")
//	public Employee updateEmployee() {
//		Employee e=new Employee(104,"Sayantan",30000);
//		  System.out.println("Updated an employee");
//		  return service.updateEmployee(e);
//	}
//	
//	@GetMapping("/delete-employee")
//	public Employee deleteEmployee() {
//		//Employee e=new Employee(104,"Sayantan",30000);
//		int employeeId = new Random().nextInt(6) + 100;
//		  System.out.println("deleted an employee");
//		  return service.deleteEmployee(employeeId);
//		
//	//return null;
//	}
//
//}
//
////package com.capgemini.springboot.demo.controller;
////
////import java.util.List;
////
////import org.springframework.web.bind.annotation.GetMapping;
////import org.springframework.web.bind.annotation.RestController;
////
////import com.capgemini.springboot.demo.model.Employee;
////
////@RestController
////public class EmployeeController {
////
//////	http://localhost:8088/get-employee-by-id
////	@GetMapping("/get-employee-by-id")
////	public Employee getEmployeeById() {
////		Employee emp = new Employee(101, "Sonu", 50000);
////		System.out.println(emp.toString());
////		return emp;
////	}
////
//////	http://localhost:8088/get-all-employees
////	@GetMapping("/get-all-employees")
////	public List<Employee> getAllEmployees() {
////		System.out.println("get-all-employees");
////		return null;
////	}
////
//////  public Employee addEmployee() {
//////		return null;
//////	}
////
//////	public Employee updateEmployee() {
//////	return null;
//////}
////
//////	public Employee deleteEmployee() {
//////	return null;
//////}
////
////}