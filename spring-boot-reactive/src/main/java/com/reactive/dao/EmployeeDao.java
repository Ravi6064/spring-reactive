/**
 * 
 */
package com.reactive.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Repository;

import com.reactive.model.Employee;

import reactor.core.publisher.Flux;

/**
 * @author B0216853
 *
 */
@Repository
public class EmployeeDao {

	public static void sleepExecution(int i) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Employee> fetchEmployees() {
		return IntStream.rangeClosed(1, 10).peek(EmployeeDao::sleepExecution)
				.peek(i -> System.out.println("processing count in normal " + i))
				.mapToObj(i -> new Employee(i + "", "Employee " + i)).collect(Collectors.toList());
	}

	public Flux<Employee> fetchEmployeesByStream() {
		return Flux.range(1, 10).delayElements(Duration.ofSeconds(1))
				.doOnNext(e -> System.out.println("processing count in stream " + e))
				.map(e -> new Employee(e + " ", "Employee " + e));
	}

	public Flux<Employee> fetchEmployeesListByRouter() {
		return Flux.range(1, 10).doOnNext(e -> System.out.println("processing count in handler " + e))
				.map(e -> new Employee(e + "", "Employee " + e));
	}

}
