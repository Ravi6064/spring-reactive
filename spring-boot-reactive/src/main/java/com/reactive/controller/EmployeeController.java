/**
 * 
 */
package com.reactive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.model.Employee;
import com.reactive.service.EmployeeService;

import reactor.core.publisher.Flux;

/**
 * @author B0216853
 *
 */
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@GetMapping("/fetch/employees")
	public List<Employee> fetchEmployees() {
		return empService.fetchEmployees();
	}
	
	@GetMapping(value = "/fetch/employees/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Employee> fetchEmployeesByStream() {
		return empService.fetchEmployeesByStream();
	}


}
