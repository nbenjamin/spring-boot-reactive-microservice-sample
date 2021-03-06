# Order microservice

### Technology used
  1. Spring boot
  2. Spring data jpa
  3. PostgreSQL
  4. Junit 5
  5. TestContainer


#### Start application locally
This project use docker for starting postgreSQL (database) and adminer (UI for database)

To Start both postgreSQL and Adminer, execute the following command

`docker-compose -f docker/docker-compose.yaml up`

#### internationalization for error messages
configuration required

add messages.properties file under `resources` folder

```java
  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename(MESSAGE_RESOURCE_BASENAME);
    messageSource.setDefaultEncoding(DEFAULT_ENCODING);
    return messageSource;
  }

  @Bean
  public LocalValidatorFactoryBean localValidatorFactoryBean() {
    LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
    validatorFactoryBean.setValidationMessageSource(messageSource());
    return validatorFactoryBean;
  }

```

Need to add additional header parameter called  `Accept-Language`


Request 
```properties
curl -X POST \
  http://localhost:8088/service/orders \
  -H 'Accept-Language: en' \
  -H 'Content-Type: application/json' \
  -d '{

  "lineItems": [

  ],
  "orderStatus": null,
  "address": {
    "type": "SHIPPING",
    "addressLine1": "sadler",
    "addressLine2": null,
    "city": "Richmond",
    "state": "VA",
    "country": "USA"
  }
  }' | jsonpp
```

Response:
```json
{
  "status": "UNPROCESSABLE_ENTITY",
  "timestamp": "02/19/2019 11:26:14",
  "message": "Request body validation failure, please check your request.",
  "errors": [
    {
      "field": "userId",
      "message": "User Id cannot be null or empty."
    },
    {
      "field": "lineItems",
      "message": "There should be at least one item in the order."
    }
  ]
}
```

#### Synchronize and Asynchronize implementation for Order service
   
   1. Rest endpoint for Synchronize
   
 
   2. Kafka message listener for Asynchronize
   
#### Canonical messaging data model using Avro

  1. Data Serialization and exchange
  2. Store the data in compact and efficient format

#### Dockerize Order service
Commands to create docker image
```properties
docker build -t order-service .
```

after the successful build, you can find those images locally 
```properties
docker images   // will list all the images available in local
```
Remove docker image
```properties
docker rmi imageName:version
```
Remove docker containers
```properties
docker rm containerId
```


#### Junit testing

##### Database layer Testing
[Testcontainer](https://www.testcontainers.org/) library is used for integration testing, where postgres will be started using testContainer.
