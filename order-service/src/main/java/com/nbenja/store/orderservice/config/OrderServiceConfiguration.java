package com.nbenja.store.orderservice.config;


import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OrderServiceConfiguration implements WebMvcConfigurer {

  private static final String MESSAGE_RESOURCE_BASENAME = "classpath:messages";
  private static final String DEFAULT_ENCODING = "UTF-8";
  private static final String DATE_FORMAT = "MM/dd/uuuu";
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DATE_FORMATTER));
    javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DATE_FORMATTER));
    objectMapper.registerModule(javaTimeModule);
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    objectMapper.setSerializationInclusion(Include.NON_EMPTY);
    return objectMapper;
  }

  /**
   * Used for loading message resources
   * @return {@link MessageSource}
   */
  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename(MESSAGE_RESOURCE_BASENAME);
    messageSource.setDefaultEncoding(DEFAULT_ENCODING);
    return messageSource;
  }

  @Bean
  @Primary
  public LocalValidatorFactoryBean localValidatorFactoryBean() {
    LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
    validatorFactoryBean.setValidationMessageSource(messageSource());
    return validatorFactoryBean;
  }


}
