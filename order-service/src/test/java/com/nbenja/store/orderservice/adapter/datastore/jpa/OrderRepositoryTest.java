package com.nbenja.store.orderservice.adapter.datastore.jpa;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.context.support.TestPropertySourceUtils.addInlinedPropertiesToEnvironment;

import com.nbenja.store.orderservice.domain.Address;
import com.nbenja.store.orderservice.domain.AddressType;
import com.nbenja.store.orderservice.domain.LineItem;
import com.nbenja.store.orderservice.domain.Order;
import java.util.ArrayList;
import java.util.List;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.springframework.test.context.support.TestPropertySourceUtils.addInlinedPropertiesToEnvironment;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(initializers = OrderRepositoryTest.TestEnvInitializer.class)
@Testcontainers
public class OrderRepositoryTest {


  private ObjectMapper objectMapper;
  @Autowired
  private OrderRepository subject;
  @Container
  public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer();

  private Resource orderResource = new ClassPathResource("order.json");

  @BeforeEach
  public void before() {
    objectMapper = new ObjectMapper();
  }

  @Test
  public void save_andRetrieve_order() throws Exception {

    Order order = objectMapper.readValue(orderResource.getInputStream(), Order.class);
    order.getLineItems().stream().forEach(lineItem -> lineItem.setOrder(order));

    Order orderTemp = subject.save(order);
    Order actual = subject.findById(orderTemp.getId()).get();
    // LineItem 1 "price": 899.99,
    // LineItem 2 "price": 999.99,
    assertThat(actual.getTotal(), is(equalTo(1899.98)));
  }

  public static class TestEnvInitializer implements
      ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      addInlinedPropertiesToEnvironment(configurableApplicationContext, "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl());
      addInlinedPropertiesToEnvironment(configurableApplicationContext, "spring.datasource.username=" + postgreSQLContainer.getUsername());
      addInlinedPropertiesToEnvironment(configurableApplicationContext, "spring.datasource.password=" + postgreSQLContainer.getPassword());
      addInlinedPropertiesToEnvironment(configurableApplicationContext, "spring.datasource.databasee=" + postgreSQLContainer.getDatabaseName());

    }
  }
}