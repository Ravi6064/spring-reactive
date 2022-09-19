/**
 * 
 */
package com.reactive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactive.dao.EmployeeDao;
import com.reactive.model.Employee;

import reactor.core.publisher.Flux;

/**
 * @author B0216853
 *
 */
@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao empDao;

	public List<Employee> fetchEmployees() {
		long time = System.currentTimeMillis();
		List<Employee> empList = empDao.fetchEmployees();
		System.out.println(" Time taken " + (System.currentTimeMillis() - time));
		return empList;
	}
	
	public Flux<Employee> fetchEmployeesByStream() {
		long time = System.currentTimeMillis();
		Flux<Employee> empList = empDao.fetchEmployeesByStream();
		System.out.println(" Time taken " + (System.currentTimeMillis() - time));
		return empList;
	}


}
