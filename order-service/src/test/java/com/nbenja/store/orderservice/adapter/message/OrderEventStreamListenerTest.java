package com.nbenja.store.orderservice.adapter.message;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.messaging.support.MessageBuilder.withPayload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbenja.store.orderservice.adapter.datastore.jpa.OrderRepository;
import com.nbenja.store.orderservice.domain.Order;
import com.nbenja.store.orderservice.domain.event.EventType;
import com.nbenja.store.orderservice.domain.event.OrderEvent;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import javax.xml.transform.Source;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrderEventStreamListenerTest {

  private Resource orderResource = new ClassPathResource("order.json");

  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private Processor processor;
  @Autowired
  private MessageCollector messageCollector;
  @Autowired
  private OrderRepository orderRepository;


  @Test
  public void createOrder_withOrderEvent_saveSuccessfully() throws Exception {

    Order order = objectMapper.readValue(orderResource.getInputStream(), Order.class);
    OrderEvent orderEvent = new OrderEvent();
    orderEvent.setId(UUID.randomUUID().hashCode());
    orderEvent.setEventType(EventType.ORDER_CREATED);
    orderEvent.setCreatedTime(LocalDateTime.now());
    orderEvent.setRequest(order);

    Message message = new GenericMessage(objectMapper.writeValueAsString(orderEvent));
    processor.input().send(message);

    Optional<Order> orderCreated = orderRepository.findById(1L);

    assertThat(orderCreated.get().getUserId(), is(equalTo(10001L)));
  }

}