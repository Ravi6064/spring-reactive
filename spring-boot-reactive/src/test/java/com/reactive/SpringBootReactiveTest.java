package com.reactive;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class SpringBootReactiveTest {

	// @Test
	public void monoTest() {

		Mono<String> mono = Mono.just("Hello Ravi").log();
		mono.subscribe(System.out::println);
	}

	// @Test
	public void monoTestExcepton() {
		System.out.println("-------------------");
		Mono<?> m = Mono.just("Hello Ram").then(Mono.error(new Exception(""))).log();
		m.subscribe(System.out::println);
	}

	// @Test
	public void fluxTest() {
		Flux<String> fluxString = Flux.just("Spring Boot", "Hibernate", "AWS", "Python")
				.concatWithValues("Hello", "Hai").log();
		fluxString.subscribe(System.out::println);
	}

	@Test
	public void fluxTestError() {
		Flux<String> fluxString = Flux.just("Spring Boot", "Hibernate", "AWS", "Python")
				.concatWith(Flux.error(new Exception("Flux exception occured"))).concatWithValues("Hello", "Hai").log();
		fluxString.subscribe(System.out::println, e -> System.out.println(e.getMessage()));

	}
}
