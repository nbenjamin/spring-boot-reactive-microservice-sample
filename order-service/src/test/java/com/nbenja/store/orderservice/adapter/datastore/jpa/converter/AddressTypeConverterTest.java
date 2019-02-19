package com.nbenja.store.orderservice.adapter.datastore.jpa.converter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.nbenja.store.orderservice.domain.AddressType;
import org.junit.jupiter.api.Test;

public class AddressTypeConverterTest {

  private AddressTypeConverter subject = new AddressTypeConverter();

  @Test
  void convertToDatabaseColumn_withValidAddressType_returnString() {
    assertThat(subject.convertToDatabaseColumn(AddressType.BILLING),
        is(equalTo(AddressType.BILLING.getValue())));
  }

  @Test
  void convertToEntityAttribute_withValidAddressTypeString_returnEnum() {
    assertThat(subject.convertToEntityAttribute(AddressType.BILLING.getValue()),
        is(equalTo(AddressType.BILLING)));
  }
}