package com.nbenja.store.orderservice.domain;

import java.util.Optional;

public interface OrderService {

  Optional<Order> createOrder(Order order);

  Optional<Order> updateOrder(Order order);

  Optional<Order> getOrder(Long id);
}
