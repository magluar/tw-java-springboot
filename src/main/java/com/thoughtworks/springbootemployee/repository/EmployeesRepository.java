package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.controller.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesRepository extends JpaRepository<Employee, Integer> {
}
