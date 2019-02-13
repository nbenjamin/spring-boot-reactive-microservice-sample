package com.nbenja.store.orderservice.adapter.datastore;

import com.nbenja.store.orderservice.domain.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {

}
