package com.nbenja.store.orderservice.adapter.datastore.jpa.converter;

import com.nbenja.store.orderservice.domain.AddressType;
import java.util.stream.Stream;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.apache.commons.lang3.StringUtils;

@Converter(autoApply = true)
public class AddressTypeConverter implements AttributeConverter<AddressType, String> {

  @Override
  public String convertToDatabaseColumn(AddressType addressType) {
    return addressType != null ? addressType.getValue() : null;
  }

  @Override
  public AddressType convertToEntityAttribute(String s) {
    return StringUtils.isNotBlank(s) ? Stream.of(AddressType.values())
        .filter(address -> s.equalsIgnoreCase(address.getValue())).findFirst().get() : null;
  }
}
