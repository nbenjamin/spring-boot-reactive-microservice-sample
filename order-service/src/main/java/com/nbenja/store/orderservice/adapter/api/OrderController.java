package com.nbenja.store.orderservice.adapter.api;

import com.nbenja.store.orderservice.domain.Order;
import com.nbenja.store.orderservice.domain.OrderService;
import com.nbenja.store.orderservice.exception.ApplicationException;
import com.nbenja.store.orderservice.exception.NotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service/orders")
public class OrderController {

  private static final String ORDER_NOT_FOUND = "order.notfound";
  private OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  public ResponseEntity createOrder(@NotNull @Valid @RequestBody Order order) {
    return orderService.createOrder(order)
        .map( o -> new ResponseEntity(o, HttpStatus.CREATED))
        .orElseThrow(() -> new ApplicationException("Unable to create Order now"));
  }

  @PutMapping
  public ResponseEntity updateOrder(@RequestBody Order order) {
    return orderService.updateOrder(order)
        .map( o -> new ResponseEntity(o, HttpStatus.ACCEPTED))
        .orElseThrow(() -> new ApplicationException("Unable to update Order now"));
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity getOrder(@PathVariable(name= "id") Long id) {
    return orderService.getOrder(id)
        .map( o -> new ResponseEntity(o, HttpStatus.OK))
        .orElseThrow(() -> new NotFoundException("Invalid order id", ORDER_NOT_FOUND, id ));
  }
}
