package com.nbenja.store.orderservice.domain.event;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public abstract class BaseEvent<T, ID> implements Serializable {

  private ID id;
  private T request;
  private LocalDateTime createdTime;
  private Long userId;
  private EventType eventType;

}
