package com.reactive.mongo;

import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.reactive.mongo.controller.ProductController;
import com.reactive.mongo.dto.ProductDto;
import com.reactive.mongo.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
class SpringBootReactiveMongoApplicationTests {

	// WebTestClient is used for testing the Reactive endpoints.
	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private ProductService productService;

	@Test
	public void testSaveProduct() {
		Mono<ProductDto> monoProductDto = Mono.just(new ProductDto(UUID.randomUUID().toString(), "Opticals", 100.90));
		when(productService.saveProduct(monoProductDto)).thenReturn(monoProductDto);
		webTestClient.post().uri("/products/save").body(monoProductDto, ProductDto.class).exchange().expectStatus()
				.isOk();
	}

	@Test
	public void getAllProducts() {
		Flux<ProductDto> fluxProductDto = Flux.just(new ProductDto("100", "Opticals", 100.90),
				new ProductDto("200", "TV", 200.90));
		when(productService.findAllProducts()).thenReturn(fluxProductDto);
		Flux<ProductDto> responseBody = webTestClient.get().uri("/products/all").exchange().expectStatus().isOk()
				.returnResult(ProductDto.class).getResponseBody();

		StepVerifier.create(responseBody).expectSubscription().expectNext(new ProductDto("100", "Opticals", 100.90))
				.expectNext(new ProductDto("200", "TV", 200.90)).verifyComplete();
	}

	@Test
	public void getProductsByIdTest() {
		Mono<ProductDto> monoProductDto = Mono.just(new ProductDto("100", "Opticals", 100.90));
		when(productService.findById("100")).thenReturn(monoProductDto);
		Flux<ProductDto> responseBody = webTestClient.get().uri("/products/100").exchange().expectStatus().isOk()
				.returnResult(ProductDto.class).getResponseBody();
		StepVerifier.create(responseBody).expectSubscription()
				.expectNextMatches(p -> p.getProductId().equalsIgnoreCase("100")).verifyComplete();
	}

	@Test
	public void updateProductTest() {
		Mono<ProductDto> monoProductDto = Mono.just(new ProductDto("100", "Opticals", 100.90));
		when(productService.updateProduct(monoProductDto, "100")).thenReturn(monoProductDto);

		webTestClient.put().uri("/products/update/100").body(monoProductDto, ProductDto.class).exchange().expectStatus()
				.isOk();
	}

	@Test
	public void deleteTest() {
		when(productService.deleteById("100")).thenReturn(Mono.empty());
		webTestClient.delete().uri("/products/delete/100").exchange().expectStatus().isOk();
	}

}
