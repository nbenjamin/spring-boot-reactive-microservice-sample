package com.nbenja.store.orderservice.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class LineItem implements Serializable {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String productId;
  private String name;
  private String description;
  private double price;
  private int quatity;
  @ManyToOne
  private Order order;
}
