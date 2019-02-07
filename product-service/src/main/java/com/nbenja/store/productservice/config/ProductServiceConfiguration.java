package com.nbenja.store.productservice.config;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.nbenja.store.productservice.adapter.api.ProductHandler;
import com.nbenja.store.productservice.adapter.datastore.ProductRepository;
import com.nbenja.store.productservice.domain.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

@Configuration
public class ProductServiceConfiguration {

  /**
   * Initialize your database
   * @param productRepository
   * @return Save products in to database
   */
  @Bean
  public CommandLineRunner initialize(
      ProductRepository productRepository) {
    return args -> {
      Flux<Product> products = Flux.just(
          new Product("IPhone X", "Apple IPhone X, 32 GB, Gold Color", 649.99),
          new Product("IPhone XR", "Apple IPhone XS, 64 GB, Gold Color", 749.99),
          new Product("IPhone XS", "Apple IPhone XS, 128 GB, Gold Color", 949.99),
          new Product("IPhone X", "Apple IPhone 8, 32 GB, Gold Color", 549.99),
          new Product("IPhone XR", "Apple IPhone 7, 16 GB, Gold Color", 449.99),
          new Product("IPhone XS", "Apple IPhone 6, 8 GB, Gold Color", 349.99)
      ).flatMap(productRepository::save);

      products.thenMany(productRepository.findAll()).subscribe(System.out::println);
    };
  }

  /**
   * Router function with separate route for each endpoint
   * @param productHandler
   * @return RouterFunction
   */
  @Bean
  public RouterFunction<ServerResponse> productRouterFunction(ProductHandler productHandler) {
    return nest(path("/service/products"),
                route(GET("/"), productHandler::getAllProducts)
                .andRoute(GET("/{id}"), productHandler::getProduct)
                .andRoute(POST("/"), productHandler::saveProduct)
                .andRoute(PUT("/"), productHandler::updateProduct));
  }

}
