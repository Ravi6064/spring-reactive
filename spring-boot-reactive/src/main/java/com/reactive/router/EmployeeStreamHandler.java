/**
 * 
 */
package com.reactive.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reactive.dao.EmployeeDao;
import com.reactive.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author B0216853
 *
 */
@Service
public class EmployeeStreamHandler {
	
	@Autowired
	private EmployeeDao employeeDao;

	public Mono<ServerResponse> getEmployeeStream(ServerRequest serverRequest) {
		Flux<Employee> empList = employeeDao.fetchEmployeesByStream();
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(empList, Employee.class);
	}


}
