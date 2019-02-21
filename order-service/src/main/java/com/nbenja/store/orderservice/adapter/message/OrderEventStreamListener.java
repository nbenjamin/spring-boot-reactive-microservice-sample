package com.nbenja.store.orderservice.adapter.message;

import com.nbenja.store.orderservice.domain.OrderService;
import com.nbenja.store.orderservice.domain.event.EventType;
import com.nbenja.store.orderservice.domain.event.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;

@Slf4j
@EnableBinding(Processor.class)
public class OrderEventStreamListener {

  private OrderService orderService;

  public OrderEventStreamListener(OrderService orderService) {
    this.orderService = orderService;
  }

  @StreamListener(target = Sink.INPUT)
  public void createOrder(OrderEvent orderEvent) {
    log.info("Order request - {}", orderEvent);

    if(EventType.ORDER_CREATED.equals(orderEvent.getEventType().ORDER_CREATED)) {
      orderService.createOrder(orderEvent.getRequest());
    }
    // Need to send an event to update the product count
    // Send an event Shipping service, will update once its ready
    System.out.println("Order request - "+ orderEvent);
  }

}
