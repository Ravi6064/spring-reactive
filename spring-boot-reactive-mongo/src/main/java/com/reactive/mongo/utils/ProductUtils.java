/**
 * 
 */
package com.reactive.mongo.utils;

import org.springframework.beans.BeanUtils;

import com.reactive.mongo.dto.ProductDto;
import com.reactive.mongo.entity.Product;

/**
 * @author B0216853
 *
 */
public class ProductUtils {

	public static ProductDto entityToDto(Product product) {
		ProductDto productDto = new ProductDto();
		BeanUtils.copyProperties(product, productDto);
		return productDto;
	}

	public static Product dtoToEntity(ProductDto productDto) {
		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);
		return product;
	}

}
