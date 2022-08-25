package com.employee.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.employee.Responses;
import com.employee.domain.Employees;
import com.employee.repo.EmployeesRepository;

@Service
public class EmployeesService {

	@Autowired
	private EmployeesRepository repository;
	
	/**
	 * Create the employee entity and pass to the repository to store in the database
	 * @param employee
	 * @return Responses object with httpStatus, message and data if available
	 */
	public Responses create(Employees employee) {
		
		Responses response = new Responses();
		
		if(employee.getEmail() == null || employee.getMobileNumber() == null) {
			response.setStatus("Error");
			response.setHttpStatus(HttpStatus.BAD_REQUEST);
			if(employee.getEmail() == null) {
				response.setMessage("Please enter EmailId!");
			}
			else if (employee.getMobileNumber() == null) {
				response.setMessage("Please enter Mobile Number!");
			}
			else {
				response.setMessage("Please enter EmailId and Mobile Number!");
			}
		}
		else if (repository.findOneByEmailIgnoreCase(employee.getEmail()).isPresent()) {
			response.setMessage("EmailId Already Used");
			response.setStatus("Error");
			response.setHttpStatus(HttpStatus.BAD_REQUEST);
		}
		else if (repository.findOneByMobileNumber(employee.getMobileNumber()).isPresent()) {
			response.setMessage("Mobile Number Already Used");
			response.setStatus("Error");
			response.setHttpStatus(HttpStatus.BAD_REQUEST);
		}
		else {
			repository.save(employee);
			response.setMessage("Employee Registered Successfully!");
			response.setStatus("Success");
			response.setHttpStatus(HttpStatus.CREATED);
		}
		return response;
	}

	/**
	 * Getting Employee Details by Id
	 * @param Employee id
	 * @return Details of the Employee
	 */
	public Responses getEmployeeById(long id) {
		
		Responses response = new Responses();
		
		Optional<Employees> employee = repository.findById(id);
		
		if(employee.isPresent()) {
			return new Responses(employee.get(), " ", "Success", HttpStatus.OK);
		}
		return new Responses(null, "Employee not found!", "Error", HttpStatus.NOT_FOUND);
	}

	/**
	 * Getting the list of the registered Employees
	 * @return Employees List
	 */
	public Responses getAllEmployeesList() {
		return new Responses(repository.findAll(), " ", "Success", HttpStatus.OK);
	}

	/**
	 * Delete particular employee
	 * @param id
	 * @return success message or error
	 */
	public Responses deleteEmployee(long id) {

		Responses response = new Responses();
		
		Optional<Employees> employee = repository.findById(id);
		
		if(employee.isPresent()) {
			repository.deleteById(id);
			return new Responses(null , "Deleted Successfully!", "Success", HttpStatus.OK);
		}
		return new Responses(null, "Employee not found!", "Error", HttpStatus.NOT_FOUND);
	}

	/**
	 * Updating the employee's details
	 * @param id 
	 * @param employee
	 * @return updated employee
	 */
	public Responses update(long id, Employees employee) {
		
		Employees existingEmployee = repository.findById(id).get();
		
		Responses response = new Responses();
		
		if (employee.getEmail() != null) {
			existingEmployee.setEmail(employee.getEmail());
		}
		if (employee.getMobileNumber() != null) {
			existingEmployee.setMobileNumber(employee.getMobileNumber());
		}
		if (employee.getFirstName() != null) {
			existingEmployee.setFirstName(employee.getFirstName());
		}
		if (employee.getLastName() != null) {
			existingEmployee.setLastName(employee.getLastName());
		}
		return new Responses(repository.save(existingEmployee), "Updated Successfully!", "Success", HttpStatus.OK);
	}

	
}
