package com.nbenja.store.orderservice.config;

import com.nbenja.store.orderservice.adapter.datastore.OrderRepository;
import com.nbenja.store.orderservice.domain.Order;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.Result;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
@EnableR2dbcRepositories
public class OrderServiceConfiguration {

  @Value("${order.datasource.host}")
  private String host;

  @Value("${order.datasource.database}")
  private String database;

  @Value("${order.datasource.username}")
  private String username;

  @Value("${order.datasource.password}")
  private String password;

  /**
   * https://spring.io/blog/2018/12/12/spring-data-r2dbc-1-0-m1-released
   *
   * @return
   */
  @Bean
  public DatabaseClient databaseClient() {
    return DatabaseClient.create(connectionFactory());
  }

  private ConnectionFactory connectionFactory() {
    PostgresqlConnectionConfiguration configuration = PostgresqlConnectionConfiguration
        .builder()
        .database(database)
        .host(host)
        .port(5432)
        .username(username)
        .password(password)
        .build();
    return new PostgresqlConnectionFactory(configuration);
  }

  //Seed data

  @Bean
  CommandLineRunner commandLineRunner() {
    return args -> {
//      Mono<Connection> connection = Mono.from(connectionFactory().create());
//      Flux<Result> ids = connection.flatMapMany(con ->
//        con.createStatement("INSERT INTO ORDER (id) values ($1)")
//        .bind("$1", 10)
//          .add().execute()
//      );

      databaseClient().execute().sql("DELETE FROM orders").then().block();

      databaseClient().insert()
          .into(Order.class)
          .using(new Order(1L, 100L))
          .then().block();

    };
  }
}
