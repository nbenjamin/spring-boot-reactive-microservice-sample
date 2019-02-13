package com.nbenja.store.orderservice.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "orders")
public class Order {

  @Id
  private Long id;
  @Column(value = "user_id")
  private Long userId;

  public Order() {
  }

  public Order(Long id, Long userId) {
    this.id = id;
    this.userId = userId;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

//  public List<LineItem> getLineItem() {
//    return lineItem;
//  }
//
//  public void setLineItem(List<LineItem> lineItem) {
//    this.lineItem = lineItem;
//  }
//
//  public Address getShippingAddress() {
//    return shippingAddress;
//  }
//
//  public void setShippingAddress(Address shippingAddress) {
//    this.shippingAddress = shippingAddress;
//  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj, false);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, false);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
