package com.nbenja.store.orderservice.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "order_id")
  private Long id;
  @NotNull(message = "{order.userId}")
  private Long userId;
  // Bi-directional
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "order",cascade = CascadeType.ALL, orphanRemoval = true)
  @Size(   message = "{order.lineItems}",  min = 1)
  private List<LineItem> lineItems = new ArrayList();
  private OrderStatus orderStatus;
  // Bi-directional
  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "address_id")
  private Address address;

  @Transient
  private Double total;

  public Double getTotal(){
    return lineItems.stream().mapToDouble(LineItem::getPrice).sum();
  }
}
