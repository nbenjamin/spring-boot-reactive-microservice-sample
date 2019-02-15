package com.nbenja.store.orderservice.adapter.datastore.jpa.converter;

import com.nbenja.store.orderservice.domain.AddressType;
import com.nbenja.store.orderservice.domain.OrderStatus;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.apache.commons.lang3.StringUtils;

@Converter
public class AddressTypeConverter implements AttributeConverter<AddressType, String> {

  @Override
  public String convertToDatabaseColumn(AddressType addressType) {
    return addressType != null ? addressType.getValue(): null;
  }

  @Override
  public AddressType convertToEntityAttribute(String s) {
    return StringUtils.isNotBlank(s) ? AddressType.valueOf(s): null;
  }
}
