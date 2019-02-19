package com.nbenja.store.orderservice.adapter.datastore.jpa.converter;

import com.nbenja.store.orderservice.domain.OrderStatus;
import java.util.stream.Stream;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.apache.commons.lang3.StringUtils;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {

  @Override
  public String convertToDatabaseColumn(OrderStatus orderStatus) {
    return orderStatus != null ? orderStatus.getValue(): null;
  }

  @Override
  public OrderStatus convertToEntityAttribute(String s) {
    return StringUtils.isNotBlank(s) ? Stream.of(OrderStatus.values())
        .filter(status -> s.equalsIgnoreCase(status.getValue())).findFirst().get() : null;
  }
}
