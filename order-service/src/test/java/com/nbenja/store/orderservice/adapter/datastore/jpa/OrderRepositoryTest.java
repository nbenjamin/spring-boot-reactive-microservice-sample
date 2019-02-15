package com.nbenja.store.orderservice.adapter.datastore.jpa;

import com.nbenja.store.orderservice.domain.Address;
import com.nbenja.store.orderservice.domain.AddressType;
import com.nbenja.store.orderservice.domain.LineItem;
import com.nbenja.store.orderservice.domain.Order;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.context.support.TestPropertySourceUtils.addInlinedPropertiesToEnvironment;

@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
//    "spring.datasource.url=jdbc:tc:postgresql:11-alpine:///order-service",
//    "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver"
//})
@SpringBootTest
@ContextConfiguration
public class OrderRepositoryTest {

  @Autowired
  private OrderRepository subject;
  @ClassRule
  public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer();

  @Test
  public void test() {
    Order order = new Order();
    Address address = new Address();
    address.setType(AddressType.SHIPPING);
    address.setAddressLine1("sadler");
    address.setCity("Richmond");
    address.setState("VA");
    address.setCountry("USA");
    order.setAddress(address);
    LineItem lineItem1 = new LineItem();
    lineItem1.setName("Iphone");
    lineItem1.setDescription("Iphone X 64 GB Gold");
    lineItem1.setPrice(899.99);

    LineItem lineItem2 = new LineItem();
    lineItem2.setName("Iphone");
    lineItem2.setDescription("Iphone X 128 GB White");
    lineItem2.setPrice(999.99);

    List<LineItem> lineItemSet = new ArrayList<>();
    lineItemSet.add(lineItem1);
    lineItemSet.add(lineItem2);

    lineItem1.setOrder(order);
    lineItem2.setOrder(order);

    order.setLineItems(lineItemSet);
    order.setUserId(10001L);

    Order orderTemp = subject.save(order);
    Order actual = subject.findById(orderTemp.getId()).get();
    System.out.println(actual.getUserId());
    assertThat(actual.getTotal(), is(equalTo(1899.98)));
  }

}