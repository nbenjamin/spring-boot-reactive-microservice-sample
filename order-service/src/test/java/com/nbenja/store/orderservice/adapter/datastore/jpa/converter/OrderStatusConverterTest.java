package com.nbenja.store.orderservice.adapter.datastore.jpa.converter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.nbenja.store.orderservice.domain.OrderStatus;
import org.junit.jupiter.api.Test;

public class OrderStatusConverterTest {

  private OrderStatusConverter subject = new OrderStatusConverter();

  @Test
  void convertToDatabaseColumn_withValidOrderStatuse_returnString() {
    assertThat(subject.convertToDatabaseColumn(OrderStatus.CREATED),
        is(equalTo(OrderStatus.CREATED.getValue())));
  }

  @Test
  void convertToEntityAttribute_withValidOrderStatusString_returnEnum() {
    assertThat(subject.convertToEntityAttribute(OrderStatus.CREATED.getValue()),
        is(equalTo(OrderStatus.CREATED)));
  }
}