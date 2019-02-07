package com.nbenja.store.productservice.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

  Flux<Product> getAllProducts();

  Mono<Product> getProduct(String id);

  Mono<Product> save(Product product);
}
