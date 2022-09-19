/**
 * 
 */
package com.twilio.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author B0216853
 *
 */
@Configuration
public class NotificationRouter {

	@Autowired
	private NotificationHandler notificationHandler;

	@Bean
	public RouterFunction<ServerResponse> routerBuilder() {
		return RouterFunctions
				.route()
				.POST("/send/otp", notificationHandler::sendOtp)
				.POST("/validate/otp",notificationHandler::validateOtp)
				.build();
	}

}
