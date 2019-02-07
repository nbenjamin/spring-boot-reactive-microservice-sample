package com.nbenja.store.productservice.adapter.datastore;

import com.nbenja.store.productservice.domain.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

}
