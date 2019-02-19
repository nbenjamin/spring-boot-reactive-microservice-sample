package com.nbenja.store.orderservice.adapter.service;

import com.nbenja.store.orderservice.adapter.datastore.jpa.OrderRepository;
import com.nbenja.store.orderservice.domain.Order;
import com.nbenja.store.orderservice.domain.OrderService;
import com.nbenja.store.orderservice.domain.OrderStatus;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl  implements OrderService {

  private OrderRepository orderRepository;

  public OrderServiceImpl(
      OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public Optional<Order> createOrder(Order order) {
    order.setOrderStatus(OrderStatus.CREATED);
    order.getLineItems().stream().forEach(lineItem -> lineItem.setOrder(order));
    return Optional.ofNullable(orderRepository.save(order));
  }

  @Override
  public Optional<Order> updateOrder(Order order) {
    order.getLineItems().stream().forEach(lineItem -> lineItem.setOrder(order));
    return Optional.ofNullable(orderRepository.save(order));
  }

  @Override
  public Optional<Order> getOrder(Long id) {
    return orderRepository.findById(id);
  }
}
