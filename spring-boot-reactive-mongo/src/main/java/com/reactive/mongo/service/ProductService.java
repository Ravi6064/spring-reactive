/**
 * 
 */
package com.reactive.mongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import com.reactive.mongo.dto.ProductDto;
import com.reactive.mongo.entity.Product;
import com.reactive.mongo.repository.ProductRepository;
import com.reactive.mongo.utils.ProductUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author B0216853
 *
 */
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Flux<ProductDto> findAllProducts() {
		return productRepository.findAll().map(ProductUtils::entityToDto);
	}

	public Mono<ProductDto> findById(String id) {
		return productRepository.findById(id).map(ProductUtils::entityToDto);
	}

	public Flux<ProductDto> findByPriceRangeBetween(double min, double max) {
		return productRepository.findByPriceBetween(Range.closed(min, max)).map(ProductUtils::entityToDto);
	}

	public Mono<ProductDto> saveProduct(Mono<ProductDto> productDto) {
		return productDto.map(ProductUtils::dtoToEntity).flatMap(productRepository::insert)
				.map(ProductUtils::entityToDto);
	}

	public Mono<ProductDto> updateProduct(Mono<ProductDto> productDto, String id) {
		return productRepository.findById(id)
				.flatMap(p -> productDto.map(ProductUtils::dtoToEntity))
				.doOnNext(e -> e.setProductId(id))
				.flatMap(productRepository::save)
				.map(ProductUtils::entityToDto);
	}
	
	public Mono<Void> deleteById(String id){
		return productRepository.deleteById(id);
	}

}
