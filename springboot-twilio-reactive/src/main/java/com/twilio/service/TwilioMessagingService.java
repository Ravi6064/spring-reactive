/**
 * 
 */
package com.twilio.service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twilio.config.TwilioConfig;
import com.twilio.dto.PasswordResetDto;
import com.twilio.dto.PasswordResetResponseDTO;
import com.twilio.rest.api.v2010.account.Message;

import reactor.core.publisher.Mono;

/**
 * @author B0216853
 *
 */
//Twilio is a Paas system which provides paid services for SMS,email etc.
//We need to use Twilio sdk library inorder to use the functionality of Twilio.
//For trail purpose we need to register our number and get the trail sample number.
//We need to have accountSid, authtoken and trail number for interaction with Twilio.
@Service
public class TwilioMessagingService {

	@Autowired
	private TwilioConfig twilioConfig;

	Map<String, String> map = new HashMap<>();

	public Mono<PasswordResetResponseDTO> sendForgotPasswordResponse(final PasswordResetDto passwordResetDto) {

		String to = passwordResetDto.getPhoneNumber();
		String from = twilioConfig.getTrailNumber();
		PasswordResetResponseDTO resp = null;
		try {
			StringBuilder otp = generateOtp();
			String otpValue = otp.toString();

			Message message = Message.creator(new com.twilio.type.PhoneNumber(to),
					new com.twilio.type.PhoneNumber(from), otp.append(" is your one time password").toString())
					.create();
			resp = new PasswordResetResponseDTO();
			resp.setStatus("DELIVERED");
			resp.setMessage(otp.toString());
			map.put(passwordResetDto.getUserName(), otpValue);
		} catch (Exception e) {
			resp = new PasswordResetResponseDTO("FAILED", e.getMessage());
		}
		return Mono.just(resp);
	}

	private StringBuilder generateOtp() {
		return new StringBuilder(new DecimalFormat("000000").format(new Random().nextInt(999999)));
	}

	public Mono<String> validateOtp(String userName, String otp) {
		System.out.println(map);
		System.out.println(map.get(userName));
		if (map.get(userName).equals(otp)) {
			return Mono.just("Validation successful");
		} else {
			return Mono.error(() -> new IllegalArgumentException("Invalid otp "));
		}
	}

}
