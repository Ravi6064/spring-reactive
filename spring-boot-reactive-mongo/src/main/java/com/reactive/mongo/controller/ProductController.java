/**
 * 
 */
package com.reactive.mongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.mongo.dto.ProductDto;
import com.reactive.mongo.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author B0216853
 *
 */
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/all")
	public Flux<ProductDto> getAllProducts() {
		return productService.findAllProducts();
	}

	@GetMapping("/{id}")
	public Mono<ProductDto> getProductsById(@PathVariable String id) {
		return productService.findById(id);
	}

	@GetMapping("/range")
	public Flux<ProductDto> getProductsByRange(@RequestParam("min") double min, @RequestParam("max") double max) {
		return productService.findByPriceRangeBetween(min, max);
	}

	@PostMapping("/save")
	public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDto) {
		return productService.saveProduct(productDto);
	}

	@DeleteMapping("/delete/{id}")
	public Mono<Void> deleteProductById(@PathVariable String id) {
		return productService.deleteById(id);
	}

	@PutMapping("/update/{id}")
	public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDto, @PathVariable String id) {
		return productService.updateProduct(productDto, id);
	}

}
