/**
 * 
 */
package com.reactive.mongo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author B0216853
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

	private String productId;
	private String productName;
	private double price;

}
