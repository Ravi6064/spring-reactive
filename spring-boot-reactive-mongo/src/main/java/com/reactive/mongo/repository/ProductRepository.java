/**
 * 
 */
package com.reactive.mongo.repository;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.reactive.mongo.entity.Product;

import reactor.core.publisher.Flux;

/**
 * @author B0216853
 *
 */
@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

	Flux<Product> findByPriceBetween(Range<Double> closed);

}
