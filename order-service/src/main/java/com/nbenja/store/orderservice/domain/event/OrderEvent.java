package com.nbenja.store.orderservice.domain.event;

import com.nbenja.store.orderservice.domain.Order;
import lombok.Data;

@Data
public class OrderEvent extends BaseEvent<Order, Integer> {

}
