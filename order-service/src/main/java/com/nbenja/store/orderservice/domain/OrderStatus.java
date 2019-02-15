package com.nbenja.store.orderservice.domain;

public enum OrderStatus {
  CREATED("Created"), IN_PROGRESS("In Progress"), COMPLETED("Completed"), CANCELLED("Cancelled");
  private String value;

  OrderStatus(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}
