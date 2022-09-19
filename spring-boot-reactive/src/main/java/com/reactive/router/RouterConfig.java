package com.reactive.router;

import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reactive.model.Employee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Configuration
public class RouterConfig {

	@Autowired
	private RouterHandler routerHandler;

	@Autowired
	private EmployeeStreamHandler empStreamHandler;
	
	@Bean
	public WebProperties.Resources resources(){
		return new WebProperties.Resources();
	}
	
	@Bean
	public ErrorProperties errorProperties(){
		return new ErrorProperties();
	}


	@Bean
	@RouterOperations({ 
		@RouterOperation(path = "/fetch/all/employees"
	, produces = {
			MediaType.APPLICATION_JSON_VALUE },
	method = RequestMethod.GET,
	beanClass = RouterHandler.class,
	beanMethod = "getEmployeeList",
	operation = @Operation(operationId = "getEmployeeList",
	responses = {
					@ApiResponse(
							responseCode = "200",
							description = "successful operation",
							content = @Content(
									schema = @Schema(
											implementation = Employee.class
											
											)
									)

					) }

			)),@RouterOperation(path = "/fetch/employee/by/{empid}"
					, produces = {
							MediaType.APPLICATION_JSON_VALUE },
					method = RequestMethod.GET,
					beanClass = RouterHandler.class,
					beanMethod = "getEmployeeById",
					operation = @Operation(operationId = "getEmployeeById",
					responses = {
									@ApiResponse(
											responseCode = "200",
											description = "successful operation",
											content = @Content(
													schema = @Schema(
															implementation = Employee.class
															
															)
													)

									),
									@ApiResponse(responseCode = "404",
									description = "employee not found"
											)
									},
					parameters = {@Parameter(
							in = ParameterIn.PATH,name="empid"
							)}

							) 
		),@RouterOperation(path = "/save/employee"
		, produces = {
				MediaType.APPLICATION_JSON_VALUE },
		method = RequestMethod.POST,
		beanClass = RouterHandler.class,
		beanMethod = "saveEmployee",
		operation = @Operation(operationId = "saveEmployee",
		responses = {
						@ApiResponse(
								responseCode = "200",
								description = "successful operation",
								content = @Content(
										schema = @Schema(
												implementation = String.class
												
												)
										)

						)
						
						},
		requestBody = @RequestBody(
				content = @Content(
						schema = @Schema(
								implementation = Employee.class
								
								)
						)
				
				)

				) 
)})
	public RouterFunction<ServerResponse> routeRequests() {
		return RouterFunctions.route().GET("/fetch/all/employees", routerHandler::getEmployeeList)
				.GET("/fetch/all/employees/stream", empStreamHandler::getEmployeeStream)
				.GET("/fetch/employee/by/{empid}", routerHandler::getEmployeeById)
				.POST("/save/employee", routerHandler::saveEmployee).build();
	}

}
