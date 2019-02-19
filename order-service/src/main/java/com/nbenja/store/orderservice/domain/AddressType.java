package com.nbenja.store.orderservice.domain;

public enum AddressType {
  BILLING("Billing"), SHIPPING("Shipping");

  private String value;

  AddressType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
