package com.employee.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.domain.Employees;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Long> {

	Optional<Employees> findOneByEmailIgnoreCase(String email);

	Optional<Employees> findOneByMobileNumber(Long mobileNumber);
}
