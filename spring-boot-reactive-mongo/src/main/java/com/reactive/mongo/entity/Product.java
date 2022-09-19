/**
 * 
 */
package com.reactive.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection = "product_details")
public class Product {
	
	@Id
	private String productId;
	private String productName;
	private double price;


}
