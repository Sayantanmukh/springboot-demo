package com.capgemini.springboot.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.springboot.demo.exception.DepartmentNotFoundException;
import com.capgemini.springboot.demo.exception.EmployeeNotFoundException;
import com.capgemini.springboot.demo.model.Employee;
import com.capgemini.springboot.demo.repository.DepartmentRepository;
import com.capgemini.springboot.demo.repository.EmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	DepartmentRepository departmentRepository;


	@Override
	public Employee getEmpById(int employeeId) {
		LOG.info("getEmpById " + employeeId);
		Optional<Employee> empOptional = employeeRepository.findById(employeeId);
		if (empOptional.isPresent()) {
			return empOptional.get();
		} else {
			String exceptionMessage = "Employee with employeeId " + employeeId + " not found";
			LOG.warn(exceptionMessage);
			throw new EmployeeNotFoundException(exceptionMessage);
		}
	}

	@Override
	public List<Employee> getEmpsByFirstName(String firstName) {
		LOG.info("getEmpByFirstName " + firstName);
		List<Employee> empList = employeeRepository.findByFirstName(firstName);
		if (empList.isEmpty()) {
			String exceptionMessage = "Any employee with the firstName " + firstName + " not found";
			LOG.warn(exceptionMessage);
			throw new EmployeeNotFoundException(exceptionMessage);
		} else {
			return empList;
		}
	}

	@Override
	public List<Employee> getAllEmps() {
		LOG.info("getAllEmps");
		return employeeRepository.findAll();
	}

	@Override
	public Employee addEmp(Employee employee) {
		LOG.info(employee.toString());
		
//		if ( departmentRepository.findById(employee.getDepartment().getDepartmentId()) != null )
//			if ( departmentRepository.findById(employee.getDepartment().getDepartmentId()).isPresent() )
		
		if (employee.getDepartment() == null
				|| departmentRepository.findById(employee.getDepartment().getDepartmentId()).isPresent()) {
			return employeeRepository.save(employee);
		} else {
			String exceptionMessage = "Department with departmentId " + employee.getDepartment().getDepartmentId()
					+ " does not exist.";
			LOG.warn(exceptionMessage);
			throw new DepartmentNotFoundException(exceptionMessage);
		}
	}

	@Override
	public Employee updateEmp(Employee employee) {
		LOG.info(employee.toString());
		Optional<Employee> empOptional = employeeRepository.findById(employee.getEmployeeId());
		if (empOptional.isPresent()) {
			if (employee.getDepartment() == null
					|| departmentRepository.findById(employee.getDepartment().getDepartmentId()).isPresent()) {
				return employeeRepository.save(employee);
			} else {
				String exceptionMessage = "Department with departmentId " + employee.getDepartment().getDepartmentId()
						+ " does not exist.";
				LOG.warn(exceptionMessage);
				throw new DepartmentNotFoundException(exceptionMessage);
			}
		} else {
			String exceptionMessage = "Employee with employeeIdId " + employee.getEmployeeId() + " does not exist.";
			LOG.warn(exceptionMessage);
			throw new EmployeeNotFoundException(exceptionMessage);
		}
	}

	@Override
	public Employee deleteEmp(int employeeId) {
		LOG.info(Integer.toString(employeeId));
		Optional<Employee> empOptional = employeeRepository.findById(employeeId);
		if (empOptional.isPresent()) {
			Employee emp = empOptional.get();
			employeeRepository.delete(emp);
			return emp;
		} else {
			throw new EmployeeNotFoundException("Employee with employeeId " + employeeId + " not found");
		}
	}
}

//package com.capgemini.springboot.demo.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.capgemini.springboot.demo.exception.EmployeeNotFoundException;
//import com.capgemini.springboot.demo.model.Employee;
//import com.capgemini.springboot.demo.repository.EmployeeRepository;
//
//@Service
//public class EmployeeService implements IEmployeeService {
//
//	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
//
//	@Autowired
//	EmployeeRepository employeeRepository;
//
//	public Employee getEmpById(int employeeId) {
//		LOG.info("getEmpById " + employeeId);
//		Optional<Employee> empOptional = employeeRepository.findById(employeeId);
//		if (empOptional.isPresent())
//			return empOptional.get();
//		else
//			throw new EmployeeNotFoundException("Employee with employeeId " + employeeId + " not found");
//	}
//
//	public List<Employee> getEmpsByFirstName(String firstName) {
//		LOG.info("getEmpByFirstName " + firstName);
//		List<Employee> empList = employeeRepository.findByFirstName(firstName);
//		if (empList.isEmpty())
//			throw new EmployeeNotFoundException("Any employee with the firstName " + firstName + " not found");
//		else
//			return empList;
//	}
//
//	public List<Employee> getAllEmps() {
//		LOG.info("getAllEmps");
//		return employeeRepository.findAll();
//	}
//
//	public Employee addEmp(Employee employee) {
//		LOG.info("addEmp");
//		
////		employeeRepository.
//		
//		return employeeRepository.save(employee);
//	}
//
//	public Employee updateEmp(Employee employee) {
//		LOG.info("updateEmp");
//		Optional<Employee> empOptional = employeeRepository.findById(employee.getEmployeeId());
//		if (empOptional.isPresent())
//			return employeeRepository.save(employee);
//		else
//			throw new EmployeeNotFoundException("Employee not found");
//
//	}
//
//	public Employee deleteEmp(int employeeId) {
//		LOG.info("deleteEmp");
//		Optional<Employee> empOptional = employeeRepository.findById(employeeId);
//		Employee emp;
//		if (empOptional.isPresent()) {
//			emp = empOptional.get();
//			employeeRepository.delete(emp);
//			return emp;
//		} else {
//			throw new EmployeeNotFoundException("Employee with employeeId " + employeeId + " not found");
//		}
//	}
//}

//package com.capgemini.springboot.demo.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.capgemini.springboot.demo.model.Employee;
//import com.capgemini.springboot.demo.repository.EmployeeRepository;
//
//@Service
//public class EmployeeService implements IEmployeeService {
//
//	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
//
//	@Autowired
//	EmployeeRepository employeeRepository;
//
//	public Employee getEmpById(int employeeId) {
//		LOG.info("EmployeeService getEmpById");
//		return employeeRepository.findById(employeeId).get();
//	}
//
//	public List<Employee> getAllEmps() {
//		LOG.info("EmployeeService getAllEmps");
//		return employeeRepository.findAll();
//	}
//
//	public Employee addEmp(Employee employee) {
//		return employeeRepository.save(employee);
//	}
//
//	public Employee updateEmp(Employee employee) {
//		return employeeRepository.save(employee);
//	}
//
//	public Employee deleteEmp(int employeeId) {
//		Optional<Employee> empOptional = employeeRepository.findById(employeeId); // 106
//		Employee emp = empOptional.get();
//		employeeRepository.delete(emp);
//		return emp;
//	}
//}

//package com.capgemini.springboot.demo.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import com.capgemini.springboot.demo.model.Employee;
//
//@Service
//public class EmployeeService implements IEmployeeService {
//
//	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
//
//	private List<Employee> empList;
//
//	{
//		empList = new ArrayList<>();
//		empList.add(new Employee(101, "Sonu", 50000));
//		empList.add(new Employee(102, "Monu", 60000));
//		empList.add(new Employee(103, "Tonu", 40000));
//		empList.add(new Employee(104, "Ponu", 75000));
//		empList.add(new Employee(105, "Gonu", 55000));
//	}
//
//	public Employee getEmpById(int employeeId) {
//		LOG.info("EmployeeService getEmpById");
////		Employee emp = get data from DB using repository
//		Employee emp = empList.stream().filter(e -> employeeId == e.getEmployeeId()).findAny().orElse(null);
//		if (null == emp)
//			LOG.warn("Employee with the id " + employeeId + " not found!");
//		else
//			LOG.info(emp.toString());
//		return emp;
//	}
//
//	public List<Employee> getAllEmps() {
//		LOG.info("EmployeeService getAllEmps");
//		LOG.warn("EmployeeService getAllEmps");
//		LOG.error("EmployeeService getAllEmps");
//		return empList;
//	}
//
//	public Employee addEmp(Employee employee) {
//		empList.add(employee);
//		return employee;
//	}
//
//	public Employee updateEmp(Employee employee) {
//		empList.forEach(e -> {
//			if (e.getEmployeeId() == employee.getEmployeeId())
//				empList.set(empList.indexOf(e), employee);
//		});
//		return employee;
//	}
//
//	public Employee deleteEmp(int employeeId) {
//		Employee emp = this.getEmpById(employeeId);
//		empList.forEach(e -> {
//			if (e.getEmployeeId() == employeeId) {
//				empList.remove(e);
//			}
//		});
//		return emp;
//	}
//}

//package com.capgemini.springboot.demo.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import com.capgemini.springboot.demo.model.Employee;
//
//@Service
//public class EmployeeService {
//
//
//	private List<Employee> empList;
//
//	{
//		empList = new ArrayList<>();
//		empList.add(new Employee(101, "Sonu", 50000));
//		empList.add(new Employee(102, "Monu", 60000));
//		empList.add(new Employee(103, "Tonu", 40000));
//		empList.add(new Employee(104, "Ponu", 75000));
//		empList.add(new Employee(105, "Gonu", 55000));
//	}
//
//	public Employee getEmpById(int employeeId) {
//		System.out.println("EmployeeService getEmpById");
////		Employee emp = get data from DB using repository
//		Employee emp = empList.stream().filter(e -> employeeId == e.getEmployeeId()).findAny().orElse(null);
//		if (null == emp)
//			System.out.println("Employee with the id " + employeeId + " not found!");
//		else
//			System.out.println(emp.toString());
//		return emp;
//	}
//
//	public List<Employee> getAllEmps() {
//		System.out.println("EmployeeService getEmpById");
//		return empList;
//	}
//
//	public Employee addEmp(Employee employee) {
//		empList.add(employee);
//		return employee;
//	}
//
//	public Employee updateEmp(Employee employee) {
//		empList.forEach(e -> {
//			if (e.getEmployeeId() == employee.getEmployeeId())
//				empList.set(empList.indexOf(e), employee);
//		});
//		return employee;
//	}
//
//	public Employee deleteEmp(int employeeId) {
//		Employee emp = this.getEmpById(employeeId);
//		empList.forEach(e -> {
//			if (e.getEmployeeId() == employeeId) {
//				empList.remove(e);
//			}
//		});
//		return emp;
//	}
//}





//package com.capgemini.springboot.demo.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.capgemini.springboot.demo.exception.EmployeeNotFoundException;
//import com.capgemini.springboot.demo.model.Employee;
//import com.capgemini.springboot.demo.repository.EmployeeRepository;
//
//@Service
//public class EmployeeService implements IEmployeeService {
//
//	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
//
//	@Autowired
//	EmployeeRepository employeeRepository;
//
//	public Employee getEmpById(int employeeId) {
//		LOG.info("getEmpById " + employeeId);
//		Optional<Employee> empOptional = employeeRepository.findById(employeeId);
//		if (empOptional.isPresent())
//			return empOptional.get();
//		else
//			throw new EmployeeNotFoundException("Employee with employeeId " + employeeId + " not found");
//	}
//
//	public List<Employee> getEmpsByFirstName(String firstName) {
//		LOG.info("getEmpByFirstName " + firstName);
//		List<Employee> empList = employeeRepository.findByFirstName(firstName);
//		if (empList.isEmpty())
//			throw new EmployeeNotFoundException("Any employee with the firstName " + firstName + " not found");
//		else
//			return empList;
//	}
//
//	public List<Employee> getAllEmps() {
//		LOG.info("getAllEmps");
//		return employeeRepository.findAll();
//	}
//
//	public Employee addEmp(Employee employee) {
//		LOG.info("addEmp");
//		
////		employeeRepository.
//		
//		return employeeRepository.save(employee);
//	}
//
//	public Employee updateEmp(Employee employee) {
//		LOG.info("updateEmp");
//		Optional<Employee> empOptional = employeeRepository.findById(employee.getEmployeeId());
//		if (empOptional.isPresent())
//			return employeeRepository.save(employee);
//		else
//			throw new EmployeeNotFoundException("Employee not found");
//
//	}
//
//	public Employee deleteEmp(int employeeId) {
//		LOG.info("deleteEmp");
//		Optional<Employee> empOptional = employeeRepository.findById(employeeId);
//		Employee emp;
//		if (empOptional.isPresent()) {
//			emp = empOptional.get();
//			employeeRepository.delete(emp);
//			return emp;
//		} else {
//			throw new EmployeeNotFoundException("Employee with employeeId " + employeeId + " not found");
//		}
//	}
//}
//
////package com.capgemini.springboot.demo.service;
////
////import java.util.List;
////import java.util.Optional;
////
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////
////import com.capgemini.springboot.demo.model.Employee;
////import com.capgemini.springboot.demo.repository.EmployeeRepository;
////
////@Service
////public class EmployeeService implements IEmployeeService {
////
////	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
////
////	@Autowired
////	EmployeeRepository employeeRepository;
////
////	public Employee getEmpById(int employeeId) {
////		LOG.info("EmployeeService getEmpById");
////		return employeeRepository.findById(employeeId).get();
////	}
////
////	public List<Employee> getAllEmps() {
////		LOG.info("EmployeeService getAllEmps");
////		return employeeRepository.findAll();
////	}
////
////	public Employee addEmp(Employee employee) {
////		return employeeRepository.save(employee);
////	}
////
////	public Employee updateEmp(Employee employee) {
////		return employeeRepository.save(employee);
////	}
////
////	public Employee deleteEmp(int employeeId) {
////		Optional<Employee> empOptional = employeeRepository.findById(employeeId); // 106
////		Employee emp = empOptional.get();
////		employeeRepository.delete(emp);
////		return emp;
////	}
////}
//
////package com.capgemini.springboot.demo.service;
////
////import java.util.ArrayList;
////import java.util.List;
////
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////import org.springframework.stereotype.Service;
////
////import com.capgemini.springboot.demo.model.Employee;
////
////@Service
////public class EmployeeService implements IEmployeeService {
////
////	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
////
////	private List<Employee> empList;
////
////	{
////		empList = new ArrayList<>();
////		empList.add(new Employee(101, "Sonu", 50000));
////		empList.add(new Employee(102, "Monu", 60000));
////		empList.add(new Employee(103, "Tonu", 40000));
////		empList.add(new Employee(104, "Ponu", 75000));
////		empList.add(new Employee(105, "Gonu", 55000));
////	}
////
////	public Employee getEmpById(int employeeId) {
////		LOG.info("EmployeeService getEmpById");
//////		Employee emp = get data from DB using repository
////		Employee emp = empList.stream().filter(e -> employeeId == e.getEmployeeId()).findAny().orElse(null);
////		if (null == emp)
////			LOG.warn("Employee with the id " + employeeId + " not found!");
////		else
////			LOG.info(emp.toString());
////		return emp;
////	}
////
////	public List<Employee> getAllEmps() {
////		LOG.info("EmployeeService getAllEmps");
////		LOG.warn("EmployeeService getAllEmps");
////		LOG.error("EmployeeService getAllEmps");
////		return empList;
////	}
////
////	public Employee addEmp(Employee employee) {
////		empList.add(employee);
////		return employee;
////	}
////
////	public Employee updateEmp(Employee employee) {
////		empList.forEach(e -> {
////			if (e.getEmployeeId() == employee.getEmployeeId())
////				empList.set(empList.indexOf(e), employee);
////		});
////		return employee;
////	}
////
////	public Employee deleteEmp(int employeeId) {
////		Employee emp = this.getEmpById(employeeId);
////		empList.forEach(e -> {
////			if (e.getEmployeeId() == employeeId) {
////				empList.remove(e);
////			}
////		});
////		return emp;
////	}
////}
//
////package com.capgemini.springboot.demo.service;
////
////import java.util.ArrayList;
////import java.util.List;
////
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////import org.springframework.stereotype.Service;
////
////import com.capgemini.springboot.demo.model.Employee;
////
////@Service
////public class EmployeeService {
////
////
////	private List<Employee> empList;
////
////	{
////		empList = new ArrayList<>();
////		empList.add(new Employee(101, "Sonu", 50000));
////		empList.add(new Employee(102, "Monu", 60000));
////		empList.add(new Employee(103, "Tonu", 40000));
////		empList.add(new Employee(104, "Ponu", 75000));
////		empList.add(new Employee(105, "Gonu", 55000));
////	}
////
////	public Employee getEmpById(int employeeId) {
////		System.out.println("EmployeeService getEmpById");
//////		Employee emp = get data from DB using repository
////		Employee emp = empList.stream().filter(e -> employeeId == e.getEmployeeId()).findAny().orElse(null);
////		if (null == emp)
////			System.out.println("Employee with the id " + employeeId + " not found!");
////		else
////			System.out.println(emp.toString());
////		return emp;
////	}
////
////	public List<Employee> getAllEmps() {
////		System.out.println("EmployeeService getEmpById");
////		return empList;
////	}
////
////	public Employee addEmp(Employee employee) {
////		empList.add(employee);
////		return employee;
////	}
////
////	public Employee updateEmp(Employee employee) {
////		empList.forEach(e -> {
////			if (e.getEmployeeId() == employee.getEmployeeId())
////				empList.set(empList.indexOf(e), employee);
////		});
////		return employee;
////	}
////
////	public Employee deleteEmp(int employeeId) {
////		Employee emp = this.getEmpById(employeeId);
////		empList.forEach(e -> {
////			if (e.getEmployeeId() == employeeId) {
////				empList.remove(e);
////			}
////		});
////		return emp;
////	}
////}
//
//
//
//
//
//
//
//
////package com.capgemini.springboot.demo.service;
////
////import java.util.List;
////import java.util.Optional;
////
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////
////import com.capgemini.springboot.demo.exception.EmployeeNotFoundException;
////import com.capgemini.springboot.demo.model.Employee;
////import com.capgemini.springboot.demo.repository.EmployeeRepository;
////
////@Service
////public class EmployeeService implements IEmployeeService {
////
////	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
////
////	@Autowired
////	EmployeeRepository employeeRepository;
////
////	public Employee getEmpById(int employeeId) {
////		LOG.info("getEmpById " + employeeId);
////		Optional<Employee> empOptional = employeeRepository.findById(employeeId);
////		if (empOptional.isPresent())
////			return empOptional.get();
////		else
////			throw new EmployeeNotFoundException("Employee with employeeId " + employeeId + " not found");
////	}
////
////	public List<Employee> getEmpByFirstName(String firstName) {
////		LOG.info("getEmpByFirstName " + firstName);
////		List<Employee> empList = employeeRepository.findByFirstName(firstName);
////		if (empList.isEmpty())
////			throw new EmployeeNotFoundException("Any employee with the firstName " + firstName + " not found");
////		else
////			return empList;
////	}
////
////	public List<Employee> getAllEmps() {
////		LOG.info("getAllEmps");
////		return employeeRepository.findAll();
////	}
////
////	public Employee addEmp(Employee employee) {
////		LOG.info("addEmp");
////		return employeeRepository.save(employee);
////	}
////
////	public Employee updateEmp(Employee employee) {
////		LOG.info("updateEmp");
////		Optional<Employee> empOptional = employeeRepository.findById(employee.getEmployeeId());
////		if (empOptional.isPresent())
////			return employeeRepository.save(employee);
////		else
////			throw new EmployeeNotFoundException("Employee not found");
////
////	}
////
////	public Employee deleteEmp(int employeeId) {
////		LOG.info("deleteEmp");
////		Optional<Employee> empOptional = employeeRepository.findById(employeeId);
////		Employee emp;
////		if (empOptional.isPresent()) {
////			emp = empOptional.get();
////			employeeRepository.delete(emp);
////			return emp;
////		} else {
////			throw new EmployeeNotFoundException("Employee with employeeId " + employeeId + " not found");
////		}
////	}
////}
////
//////package com.capgemini.springboot.demo.service;
//////
//////import java.util.List;
//////import java.util.Optional;
//////
//////import org.slf4j.Logger;
//////import org.slf4j.LoggerFactory;
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.stereotype.Service;
//////
//////import com.capgemini.springboot.demo.model.Employee;
//////import com.capgemini.springboot.demo.repository.EmployeeRepository;
//////
//////@Service
//////public class EmployeeService implements IEmployeeService {
//////
//////	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
//////
//////	@Autowired
//////	EmployeeRepository employeeRepository;
//////
//////	public Employee getEmpById(int employeeId) {
//////		LOG.info("EmployeeService getEmpById");
//////		return employeeRepository.findById(employeeId).get();
//////	}
//////
//////	public List<Employee> getAllEmps() {
//////		LOG.info("EmployeeService getAllEmps");
//////		return employeeRepository.findAll();
//////	}
//////
//////	public Employee addEmp(Employee employee) {
//////		return employeeRepository.save(employee);
//////	}
//////
//////	public Employee updateEmp(Employee employee) {
//////		return employeeRepository.save(employee);
//////	}
//////
//////	public Employee deleteEmp(int employeeId) {
//////		Optional<Employee> empOptional = employeeRepository.findById(employeeId); // 106
//////		Employee emp = empOptional.get();
//////		employeeRepository.delete(emp);
//////		return emp;
//////	}
//////}
////
//////package com.capgemini.springboot.demo.service;
//////
//////import java.util.ArrayList;
//////import java.util.List;
//////
//////import org.slf4j.Logger;
//////import org.slf4j.LoggerFactory;
//////import org.springframework.stereotype.Service;
//////
//////import com.capgemini.springboot.demo.model.Employee;
//////
//////@Service
//////public class EmployeeService implements IEmployeeService {
//////
//////	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
//////
//////	private List<Employee> empList;
//////
//////	{
//////		empList = new ArrayList<>();
//////		empList.add(new Employee(101, "Sonu", 50000));
//////		empList.add(new Employee(102, "Monu", 60000));
//////		empList.add(new Employee(103, "Tonu", 40000));
//////		empList.add(new Employee(104, "Ponu", 75000));
//////		empList.add(new Employee(105, "Gonu", 55000));
//////	}
//////
//////	public Employee getEmpById(int employeeId) {
//////		LOG.info("EmployeeService getEmpById");
////////		Employee emp = get data from DB using repository
//////		Employee emp = empList.stream().filter(e -> employeeId == e.getEmployeeId()).findAny().orElse(null);
//////		if (null == emp)
//////			LOG.warn("Employee with the id " + employeeId + " not found!");
//////		else
//////			LOG.info(emp.toString());
//////		return emp;
//////	}
//////
//////	public List<Employee> getAllEmps() {
//////		LOG.info("EmployeeService getAllEmps");
//////		LOG.warn("EmployeeService getAllEmps");
//////		LOG.error("EmployeeService getAllEmps");
//////		return empList;
//////	}
//////
//////	public Employee addEmp(Employee employee) {
//////		empList.add(employee);
//////		return employee;
//////	}
//////
//////	public Employee updateEmp(Employee employee) {
//////		empList.forEach(e -> {
//////			if (e.getEmployeeId() == employee.getEmployeeId())
//////				empList.set(empList.indexOf(e), employee);
//////		});
//////		return employee;
//////	}
//////
//////	public Employee deleteEmp(int employeeId) {
//////		Employee emp = this.getEmpById(employeeId);
//////		empList.forEach(e -> {
//////			if (e.getEmployeeId() == employeeId) {
//////				empList.remove(e);
//////			}
//////		});
//////		return emp;
//////	}
//////}
////
//////package com.capgemini.springboot.demo.service;
//////
//////import java.util.ArrayList;
//////import java.util.List;
//////
//////import org.slf4j.Logger;
//////import org.slf4j.LoggerFactory;
//////import org.springframework.stereotype.Service;
//////
//////import com.capgemini.springboot.demo.model.Employee;
//////
//////@Service
//////public class EmployeeService {
//////
//////
//////	private List<Employee> empList;
//////
//////	{
//////		empList = new ArrayList<>();
//////		empList.add(new Employee(101, "Sonu", 50000));
//////		empList.add(new Employee(102, "Monu", 60000));
//////		empList.add(new Employee(103, "Tonu", 40000));
//////		empList.add(new Employee(104, "Ponu", 75000));
//////		empList.add(new Employee(105, "Gonu", 55000));
//////	}
//////
//////	public Employee getEmpById(int employeeId) {
//////		System.out.println("EmployeeService getEmpById");
////////		Employee emp = get data from DB using repository
//////		Employee emp = empList.stream().filter(e -> employeeId == e.getEmployeeId()).findAny().orElse(null);
//////		if (null == emp)
//////			System.out.println("Employee with the id " + employeeId + " not found!");
//////		else
//////			System.out.println(emp.toString());
//////		return emp;
//////	}
//////
//////	public List<Employee> getAllEmps() {
//////		System.out.println("EmployeeService getEmpById");
//////		return empList;
//////	}
//////
//////	public Employee addEmp(Employee employee) {
//////		empList.add(employee);
//////		return employee;
//////	}
//////
//////	public Employee updateEmp(Employee employee) {
//////		empList.forEach(e -> {
//////			if (e.getEmployeeId() == employee.getEmployeeId())
//////				empList.set(empList.indexOf(e), employee);
//////		});
//////		return employee;
//////	}
//////
//////	public Employee deleteEmp(int employeeId) {
//////		Employee emp = this.getEmpById(employeeId);
//////		empList.forEach(e -> {
//////			if (e.getEmployeeId() == employeeId) {
//////				empList.remove(e);
//////			}
//////		});
//////		return emp;
//////	}
//////}
////
////
////
////
////
////
////
////
//////package com.capgemini.springboot.demo.service;
//////
//////import java.util.List;
//////import java.util.Optional;
//////
//////import org.slf4j.Logger;
//////import org.slf4j.LoggerFactory;
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.stereotype.Service;
//////
//////import com.capgemini.springboot.demo.model.Employee;
//////import com.capgemini.springboot.demo.repository.EmployeeRepository;
//////
//////@Service
//////public class EmployeeService implements IEmployeeService {
//////
//////	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
//////
//////	@Autowired
//////	EmployeeRepository employeeRepository;
//////
//////	public Employee getEmpById(int employeeId) {
//////		LOG.info("EmployeeService getEmpById");
//////		return employeeRepository.findById(employeeId).get();
//////	}
//////
//////	public List<Employee> getAllEmps() {
//////		LOG.info("EmployeeService getAllEmps");
//////		return employeeRepository.findAll();
//////	}
//////
//////	public Employee addEmp(Employee employee) {
//////		return employeeRepository.save(employee);
//////	}
//////
//////	public Employee updateEmp(Employee employee) {
//////		return employeeRepository.save(employee);
//////	}
//////
//////	public Employee deleteEmp(int employeeId) {
//////		Optional<Employee> empOptional = employeeRepository.findById(employeeId); // 106
//////		Employee emp = empOptional.get();
//////		employeeRepository.delete(emp);
//////		return emp;
//////	}
//////}
//////
////////package com.capgemini.springboot.demo.service;
////////
////////import java.util.ArrayList;
////////import java.util.List;
////////
////////import org.slf4j.Logger;
////////import org.slf4j.LoggerFactory;
////////import org.springframework.stereotype.Service;
////////
////////import com.capgemini.springboot.demo.model.Employee;
////////
////////@Service
////////public class EmployeeService implements IEmployeeService {
////////
////////	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
////////
////////	private List<Employee> empList;
////////
////////	{
////////		empList = new ArrayList<>();
////////		empList.add(new Employee(101, "Sonu", 50000));
////////		empList.add(new Employee(102, "Monu", 60000));
////////		empList.add(new Employee(103, "Tonu", 40000));
////////		empList.add(new Employee(104, "Ponu", 75000));
////////		empList.add(new Employee(105, "Gonu", 55000));
////////	}
////////
////////	public Employee getEmpById(int employeeId) {
////////		LOG.info("EmployeeService getEmpById");
//////////		Employee emp = get data from DB using repository
////////		Employee emp = empList.stream().filter(e -> employeeId == e.getEmployeeId()).findAny().orElse(null);
////////		if (null == emp)
////////			LOG.warn("Employee with the id " + employeeId + " not found!");
////////		else
////////			LOG.info(emp.toString());
////////		return emp;
////////	}
////////
////////	public List<Employee> getAllEmps() {
////////		LOG.info("EmployeeService getAllEmps");
////////		LOG.warn("EmployeeService getAllEmps");
////////		LOG.error("EmployeeService getAllEmps");
////////		return empList;
////////	}
////////
////////	public Employee addEmp(Employee employee) {
////////		empList.add(employee);
////////		return employee;
////////	}
////////
////////	public Employee updateEmp(Employee employee) {
////////		empList.forEach(e -> {
////////			if (e.getEmployeeId() == employee.getEmployeeId())
////////				empList.set(empList.indexOf(e), employee);
////////		});
////////		return employee;
////////	}
////////
////////	public Employee deleteEmp(int employeeId) {
////////		Employee emp = this.getEmpById(employeeId);
////////		empList.forEach(e -> {
////////			if (e.getEmployeeId() == employeeId) {
////////				empList.remove(e);
////////			}
////////		});
////////		return emp;
////////	}
////////}
//////
////////package com.capgemini.springboot.demo.service;
////////
////////import java.util.ArrayList;
////////import java.util.List;
////////
////////import org.slf4j.Logger;
////////import org.slf4j.LoggerFactory;
////////import org.springframework.stereotype.Service;
////////
////////import com.capgemini.springboot.demo.model.Employee;
////////
////////@Service
////////public class EmployeeService {
////////
////////
////////	private List<Employee> empList;
////////
////////	{
////////		empList = new ArrayList<>();
////////		empList.add(new Employee(101, "Sonu", 50000));
////////		empList.add(new Employee(102, "Monu", 60000));
////////		empList.add(new Employee(103, "Tonu", 40000));
////////		empList.add(new Employee(104, "Ponu", 75000));
////////		empList.add(new Employee(105, "Gonu", 55000));
////////	}
////////
////////	public Employee getEmpById(int employeeId) {
////////		System.out.println("EmployeeService getEmpById");
//////////		Employee emp = get data from DB using repository
////////		Employee emp = empList.stream().filter(e -> employeeId == e.getEmployeeId()).findAny().orElse(null);
////////		if (null == emp)
////////			System.out.println("Employee with the id " + employeeId + " not found!");
////////		else
////////			System.out.println(emp.toString());
////////		return emp;
////////	}
////////
////////	public List<Employee> getAllEmps() {
////////		System.out.println("EmployeeService getEmpById");
////////		return empList;
////////	}
////////
////////	public Employee addEmp(Employee employee) {
////////		empList.add(employee);
////////		return employee;
////////	}
////////
////////	public Employee updateEmp(Employee employee) {
////////		empList.forEach(e -> {
////////			if (e.getEmployeeId() == employee.getEmployeeId())
////////				empList.set(empList.indexOf(e), employee);
////////		});
////////		return employee;
////////	}
////////
////////	public Employee deleteEmp(int employeeId) {
////////		Employee emp = this.getEmpById(employeeId);
////////		empList.forEach(e -> {
////////			if (e.getEmployeeId() == employeeId) {
////////				empList.remove(e);
////////			}
////////		});
////////		return emp;
////////	}
////////}
////////package com.capgemini.springboot.demo.service;
////////
////////import java.util.ArrayList;
////////import java.util.List;
////////
////////import org.slf4j.Logger;
////////import org.slf4j.LoggerFactory;
////////import org.springframework.stereotype.Service;
////////
////////import com.capgemini.springboot.demo.model.Employee;
////////
////////@Service
////////public class EmployeeService {
////////
////////
////////	private List<Employee> empList;
////////
////////	{
////////		empList = new ArrayList<>();
////////		empList.add(new Employee(101, "Sonu", 50000));
////////		empList.add(new Employee(102, "Monu", 60000));
////////		empList.add(new Employee(103, "Tonu", 40000));
////////		empList.add(new Employee(104, "Ponu", 75000));
////////		empList.add(new Employee(105, "Gonu", 55000));
////////	}
////////
////////	public Employee getEmpById(int employeeId) {
////////		System.out.println("EmployeeService getEmpById");
//////////		Employee emp = get data from DB using repository
////////		Employee emp = empList.stream().filter(e -> employeeId == e.getEmployeeId()).findAny().orElse(null);
////////		if (null == emp)
////////			System.out.println("Employee with the id " + employeeId + " not found!");
////////		else
////////			System.out.println(emp.toString());
////////		return emp;
////////	}
////////
////////	public List<Employee> getAllEmps() {
////////		System.out.println("EmployeeService getEmpById");
////////		return empList;
////////	}
////////
////////	public Employee addEmp(Employee employee) {
////////		empList.add(employee);
////////		return employee;
////////	}
////////
////////	public Employee updateEmp(Employee employee) {
////////		empList.forEach(e -> {
////////			if (e.getEmployeeId() == employee.getEmployeeId())
////////				empList.set(empList.indexOf(e), employee);
////////		});
////////		return employee;
////////	}
////////
////////	public Employee deleteEmp(int employeeId) {
////////		Employee emp = this.getEmpById(employeeId);
////////		empList.forEach(e -> {
////////			if (e.getEmployeeId() == employeeId) {
////////				empList.remove(e);
////////			}
////////		});
////////		return emp;
////////	}
////////}
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
////////package com.capgemini.springboot.demo.service;
////////
////////import java.util.ArrayList;
////////import java.util.List;
////////
////////import org.slf4j.Logger;
////////import org.slf4j.LoggerFactory;
////////import org.springframework.stereotype.Service;
////////
////////import com.capgemini.springboot.demo.model.Employee;
////////
////////@Service
////////public class EmployeeService {
////////
////////	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
////////
////////	private List<Employee> empList;
////////
////////	{
////////		empList = new ArrayList<>();
////////		empList.add(new Employee(101, "Sonu", 50000));
////////		empList.add(new Employee(102, "Monu", 60000));
////////		empList.add(new Employee(103, "Tonu", 40000));
////////		empList.add(new Employee(104, "Ponu", 75000));
////////		empList.add(new Employee(105, "Gonu", 55000));
////////	}
////////
////////	public Employee getEmpById(int employeeId) {
////////		LOG.info("EmployeeService getEmpById");
//////////		Employee emp = get data from DB using repository
////////		Employee emp = empList.stream().filter(e -> employeeId == e.getEmployeeId()).findAny().orElse(null);
////////		if (null == emp)
////////			LOG.warn("Employee with the id " + employeeId + " not found!");
////////		else
////////			LOG.info(emp.toString());
////////		return emp;
////////	}
////////
////////	public List<Employee> getAllEmps() {
////////		LOG.info("EmployeeService getAllEmps");
//////////		LOG.warn("EmployeeService getAllEmps");
//////////		LOG.error("EmployeeService getAllEmps");
////////		return empList;
////////	}
////////
////////	public Employee addEmp(Employee employee) {
////////		empList.add(employee);
////////		return employee;
////////	}
////////
////////	public Employee updateEmp(Employee employee) {
////////		empList.forEach(e -> {
////////			if (e.getEmployeeId() == employee.getEmployeeId())
////////				empList.set(empList.indexOf(e), employee);
////////		});
////////		return employee;
////////	}
////////
////////	public Employee deleteEmp(int employeeId) {
////////		Employee emp = this.getEmpById(employeeId);
////////		empList.forEach(e -> {
////////			if (e.getEmployeeId() == employeeId) {
////////				empList.remove(e);
////////			}
////////		});
////////		//System.out.println(emp.toString());
////////		LOG.info(emp.toString());
////////		return emp;
////////	}
////////	
//////////	}
//////////	public List<Employee> deleteEmp(int employeeId) {
//////////		Employee emp = this.getEmpById(employeeId);
//////////		if(emp!=null) {
//////////			empList.remove(emp);
//////////			return empList;
//////////		}
//////////		return null;
//////////	}
////////}
////////
//////////package com.capgemini.springboot.demo.service;
//////////
//////////import java.util.ArrayList;
//////////import java.util.List;
//////////
//////////import org.slf4j.Logger;
//////////import org.slf4j.LoggerFactory;
//////////import org.springframework.stereotype.Service;
//////////
//////////import com.capgemini.springboot.demo.model.Employee;
//////////
//////////@Service
//////////public class EmployeeService {
//////////
//////////
//////////	private List<Employee> empList;
//////////
//////////	{
//////////		empList = new ArrayList<>();
//////////		empList.add(new Employee(101, "Sonu", 50000));
//////////		empList.add(new Employee(102, "Monu", 60000));
//////////		empList.add(new Employee(103, "Tonu", 40000));
//////////		empList.add(new Employee(104, "Ponu", 75000));
//////////		empList.add(new Employee(105, "Gonu", 55000));
//////////	}
//////////
//////////	public Employee getEmpById(int employeeId) {
//////////		System.out.println("EmployeeService getEmpById");
////////////		Employee emp = get data from DB using repository
//////////		Employee emp = empList.stream().filter(e -> employeeId == e.getEmployeeId()).findAny().orElse(null);
//////////		if (null == emp)
//////////			System.out.println("Employee with the id " + employeeId + " not found!");
//////////		else
//////////			System.out.println(emp.toString());
//////////		return emp;
//////////	}
//////////
//////////	public List<Employee> getAllEmps() {
//////////		System.out.println("EmployeeService getEmpById");
//////////		return empList;
//////////	}
//////////
//////////	public Employee addEmp(Employee employee) {
//////////		empList.add(employee);
//////////		return employee;
//////////	}
//////////
//////////	public Employee updateEmp(Employee employee) {
//////////		empList.forEach(e -> {
//////////			if (e.getEmployeeId() == employee.getEmployeeId())
//////////				empList.set(empList.indexOf(e), employee);
//////////		});
//////////		return employee;
//////////	}
//////////
//////////	public Employee deleteEmp(int employeeId) {
//////////		Employee emp = this.getEmpById(employeeId);
//////////		empList.forEach(e -> {
//////////			if (e.getEmployeeId() == employeeId) {
//////////				empList.remove(e);
//////////			}
//////////		});
//////////		return emp;
//////////	}
//////////}
////////
//////////package com.capgemini.springboot.demo.service;
//////////import java.util.ArrayList;
//////////import java.util.List;
//////////
//////////import org.springframework.stereotype.Service;
//////////
//////////import com.capgemini.springboot.demo.model.Employee;
//////////
//////////@Service
//////////public class EmployeeService {
//////////
//////////	private List<Employee> empList;
//////////
//////////	{
//////////		empList = new ArrayList<>();
//////////		empList.add(new Employee(101, "Sonu", 50000));
//////////		empList.add(new Employee(102, "Monu", 60000));
//////////		empList.add(new Employee(103, "Tonu", 40000));
//////////		empList.add(new Employee(104, "Ponu", 75000));
//////////		empList.add(new Employee(105, "Gonu", 55000));
//////////	}
//////////
//////////	public Employee getEmpById(int employeeId) {
//////////		System.out.println("EmployeeService getEmpById");
////////////		Employee emp = get data from DB using repository
//////////		for(int i=0;i<empList.size();i++) {
//////////			if(empList.get(i).getEmployeeId()==employeeId) {
//////////				return empList.get(i);
//////////			}
//////////		}
//////////		
//////////		//Employee emp = null;
//////////		return null;
//////////	}
//////////
//////////	public List<Employee> getAllEmps() {
//////////		System.out.println("EmployeeService getEmpById");
//////////		return empList;
//////////	}
//////////	
//////////	public Employee addEmployee(Employee e) {
//////////		empList.add(e);
//////////		return e;
//////////	}
//////////	
//////////	public Employee updateEmployee(Employee e) {
//////////		for(int i=0;i<empList.size();i++) {
//////////			if(empList.get(i).getEmployeeId()==e.getEmployeeId()) {
//////////				empList.set(i, e);
//////////				return e;
//////////			}
//////////		}
//////////		
//////////		//Employee emp = null;
//////////		return null;
//////////	}
//////////	
//////////	public Employee deleteEmployee(int id) {
//////////		for(int i=0;i<empList.size();i++) {
//////////			if(empList.get(i).getEmployeeId()==id) {
//////////				Employee e=empList.get(i);
//////////				empList.remove(i);
//////////				return e;
//////////			}
//////////		}
//////////		return null;
//////////		
//////////	}
//////////}