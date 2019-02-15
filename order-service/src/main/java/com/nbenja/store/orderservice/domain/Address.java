package com.nbenja.store.orderservice.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Entity
public class Address implements Serializable {

  @Id
  @GeneratedValue
  private Long id;
  private AddressType type;
  private String addressLine1;
  private String addressLine2;
  private String city;
  private String state;
  private String country;

}
