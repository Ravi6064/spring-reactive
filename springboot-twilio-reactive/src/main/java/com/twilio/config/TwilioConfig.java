/**
 * 
 */
package com.twilio.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author B0216853
 *
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "twilio")
public class TwilioConfig {

	private String accountSid;
	private String authToken;
	private String trailNumber;

}
