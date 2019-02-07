package com.nbenja.store.productservice.adapter.api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.ServerResponse.status;

import com.nbenja.store.productservice.domain.Product;
import com.nbenja.store.productservice.domain.ProductService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {

  private ProductService productService;

  public ProductHandler(
      ProductService productService) {
    this.productService = productService;
  }

  public Mono<ServerResponse> getAllProducts(ServerRequest serverRequest) {
    return ServerResponse.ok()
        .body(productService.getAllProducts(), Product.class);
  }

  public Mono<ServerResponse> getProduct(ServerRequest serverRequest) {
    Mono<Product> product = productService.getProduct(serverRequest.pathVariable("id"));
    return product
            .flatMap(p -> ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromObject(p)))
            .switchIfEmpty(notFound().build());
  }


  public Mono<ServerResponse> saveProduct(ServerRequest serverRequest) {
    Mono<Product> product = serverRequest.bodyToMono(Product.class);
    return product
        .flatMap(p -> status(CREATED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(productService.save(p), Product.class));
  }

  public Mono<ServerResponse> updateProduct(ServerRequest serverRequest) {
    Mono<Product> existingProduct = productService.getProduct(serverRequest.pathVariable("id"));

    return existingProduct
        .flatMap(p -> ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(productService.save(p), Product.class))
            .switchIfEmpty(notFound().build());
  }

}
