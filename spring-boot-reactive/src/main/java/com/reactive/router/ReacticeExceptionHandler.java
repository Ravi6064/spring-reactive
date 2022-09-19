package com.reactive.router;

import java.util.Map;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class ReacticeExceptionHandler extends DefaultErrorWebExceptionHandler {

	public ReacticeExceptionHandler(ErrorAttributes errorAttributes, Resources resources,
			ErrorProperties errorProperties, ApplicationContext applicationContext, ServerCodecConfigurer config) {
		super(errorAttributes, resources, errorProperties, applicationContext);
		this.setMessageReaders(config.getReaders());
		this.setMessageWriters(config.getWriters());
	}

	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
		return RouterFunctions.route(RequestPredicates.all(), this::getErrorMsg);
	}

	private Mono<ServerResponse> getErrorMsg(ServerRequest request) {
		Map<String, Object> error = getErrorAttributes(request, ErrorAttributeOptions.defaults());
		//error.remove("status");
		//error.remove("requestId");
		return ServerResponse.status(HttpStatus.BAD_REQUEST).body(BodyInserters.fromValue(error));
	}

}
