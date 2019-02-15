package com.nbenja.store.orderservice.domain;

import com.nbenja.store.orderservice.adapter.datastore.jpa.converter.OrderStatusConverter;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
  private Long userId;
  // Bi-directional
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "order",cascade = CascadeType.ALL, orphanRemoval = true)
  private List<LineItem> lineItems;
  @Convert(converter = OrderStatusConverter.class)
  private OrderStatus orderStatus;
  // Bi-directional
  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "address_id")
  private Address address;

  public Double getTotal(){
    return lineItems.stream().mapToDouble(LineItem::getPrice).sum();
  }
}
