package com.nbenja.store.orderservice.adapter.api;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testcontainers.shaded.com.google.common.base.CharMatcher.any;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbenja.store.orderservice.domain.Order;
import com.nbenja.store.orderservice.domain.OrderService;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

  private Resource resource = new ClassPathResource("order.json");
  private Order order;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private OrderService orderService;

  @BeforeEach
  public void before() throws IOException {
    order = objectMapper.readValue(resource.getInputStream(), Order.class);
  }

  @Test
  void createOrder_withValidOrder_return201() throws Exception {
    Order response = order;
    response.setId(1L);
    when(orderService.createOrder(order)).thenReturn(Optional.of(response));
    mockMvc.perform(post("/service/orders")
        .content(objectMapper.writeValueAsString(order))
        .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content().string(containsString("10001")));
  }

  @Test
  void updateOrder_withValidOrder_return202() throws Exception {
    Order response = order;
    response.setId(1L);
    when(orderService.updateOrder(order)).thenReturn(Optional.of(response));
    mockMvc.perform(put("/service/orders")
        .content(objectMapper.writeValueAsString(order))
        .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        .andDo(print())
        .andExpect(status().isAccepted())
        .andExpect(content().string(containsString("10001")));
  }

  @Test
  void getOrder_withValidId_return200() throws Exception {
    Order response = order;
    response.setId(1L);
    when(orderService.getOrder(1L)).thenReturn(Optional.of(response));
    mockMvc.perform(get("/service/orders/1")
        .content(objectMapper.writeValueAsString(order))
        .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("\"id\":1")));
  }
}