/**
 * 
 */
package com.twilio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author B0216853
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetDto {

	private String userName;
	private String phoneNumber;
	private String otp;

}
