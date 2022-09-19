/**
 * 
 */
package com.twilio.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.twilio.service.TwilioMessagingService;

import reactor.core.publisher.Mono;

/**
 * @author B0216853
 *
 */
@Component
public class NotificationHandler {

	@Autowired
	private TwilioMessagingService messagingService;

	public Mono<ServerResponse> sendOtp(ServerRequest serverRequest) {
		return serverRequest.bodyToMono(PasswordResetDto.class)
				.flatMap(dto -> messagingService.sendForgotPasswordResponse(dto))
				.flatMap(r -> ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(r)));
	}

	public Mono<ServerResponse> validateOtp(ServerRequest serverRequest) {
		return serverRequest.bodyToMono(PasswordResetDto.class)
				.flatMap(dto -> messagingService.validateOtp(dto.getUserName(), dto.getOtp()))
				.flatMap(r -> ServerResponse.status(HttpStatus.OK).bodyValue(r));
	}

}
