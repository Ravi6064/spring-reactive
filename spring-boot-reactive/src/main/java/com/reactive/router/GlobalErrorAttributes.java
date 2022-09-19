/**
 * 
 */
package com.reactive.router;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

/**
 * @author B0216853
 *
 */
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

	@Override
	public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
		Map<String, Object> map = new HashMap<>();
		Throwable error = getError(request);
		map.put("endpoint url", request.path());
		map.put("message", error.getMessage());
		return map;
	}
}
