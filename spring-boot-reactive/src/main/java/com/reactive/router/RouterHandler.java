/**
 * 
 */
package com.reactive.router;

import org.springframework.beans.factory.annotation.Autowired;
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
public class RouterHandler {

	@Autowired
	private EmployeeDao employeeDao;

	public Mono<ServerResponse> getEmployeeList(ServerRequest serverRequest) {
		Flux<Employee> empList = employeeDao.fetchEmployeesListByRouter();
		return ServerResponse.ok().body(empList, Employee.class);
	}

	public Mono<ServerResponse> getEmployeeById(ServerRequest serverRequest) {
		String empId = serverRequest.pathVariable("empid");
		Mono<Employee> monoEmp = employeeDao.fetchEmployeesListByRouter()
				.filter(e -> e.getEmpId().equalsIgnoreCase(empId)).next().switchIfEmpty(Mono.error(() -> new Exception("Employee Details not found")));
		Mono<String> monoString = monoEmp.map(e -> e.getEmpId() + ":" + e.getEmpName());
		return ServerResponse.ok().body(monoString, String.class);
	}

	public Mono<ServerResponse> saveEmployee(ServerRequest serverRequest) {
		Mono<Employee> employee = serverRequest.bodyToMono(Employee.class);
		Mono<String> monoString = employee.map(e -> e.getEmpId() + ":" + e.getEmpName());
		return ServerResponse.ok().body(monoString, String.class);
	}

}
