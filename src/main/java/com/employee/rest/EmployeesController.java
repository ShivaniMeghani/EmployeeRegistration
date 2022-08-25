package com.employee.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.Responses;
import com.employee.domain.Employees;
import com.employee.service.EmployeesService;

@RestController
@RequestMapping("/api")
public class EmployeesController {

	@Autowired
	private EmployeesService service;
	
	/**
	 * Post request to register employee
	 * @param employee
	 * @return status 201 Created if successful else 
	 * 		   status 400 badRequest if email or mobile number is null
	 * 		   status 400 if mobile number or email address already used
	 */
	@PostMapping("/createEmployee")
	public ResponseEntity<Responses> createEmployee(@RequestBody Employees employee) {
		Responses response = service.create(employee);
		return new ResponseEntity<Responses>(response, response.getHttpStatus());
	}
	
	/**
	 * Put Request to update the employees details
	 * @param id
	 * @param employee
	 * @return Updated Employee Details
	 */
	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<Responses> updateEmployee(@PathVariable long id, @RequestBody Employees employee) {
		Responses response = service.update(id, employee);
		return new ResponseEntity<Responses>(response, response.getHttpStatus());
	}
	
	/**
	 * Delete Mapping for deleting the employee
	 * @param id
	 * @return 200 status if deleted else 400 NotFound
	 */
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Responses> deleteEmployee(@PathVariable long id){
		Responses response = service.deleteEmployee(id);
		return new ResponseEntity<Responses>(response, response.getHttpStatus());
	}
	
	/**
	 * Get Request to get the employee details by id
	 * @param id
	 * @return Details of particular employee with 200 Ststuscode
	 */
	@GetMapping("/getEmployee/{id}")
	public ResponseEntity<Responses> getEmployeeById(@PathVariable long id){
		Responses response = service.getEmployeeById(id);
		return new ResponseEntity<Responses>(response, response.getHttpStatus());
	}
	
	/**
	 * Get Request to get the employee's list
	 * @param id
	 * @return List of the registerd employees
	 */
	@GetMapping("/listEmployees")
	public ResponseEntity<Responses> getAllEmployees(){
		Responses response = service.getAllEmployeesList();
		return new ResponseEntity<Responses>(response, response.getHttpStatus());
	}
	
}
