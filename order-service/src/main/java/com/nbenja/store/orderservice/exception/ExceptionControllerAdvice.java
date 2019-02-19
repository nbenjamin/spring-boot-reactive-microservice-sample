package com.nbenja.store.orderservice.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import com.nbenja.store.orderservice.exception.domain.Error;
import com.nbenja.store.orderservice.exception.domain.Errors;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.stream.Collectors;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

  private static final String STATUS_NOT_FOUND = "http.status.notfound";
  private static final String STATUS_UNPROCESSABLE_ENTITY = "http.status.unprocessableEntity";
  private static final String VALIDATION_FAILURE = "Request body validation failure, please check your request.";
  private MessageSource messageSource;

  public ExceptionControllerAdvice(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  /**
   * Generic error message handler for all notFoundException
   *
   * @param ex
   * @return {@link Error}
   */
  @ResponseStatus(NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  public Errors handleNotFoundException(NotFoundException ex) {

    return new Errors(getMessage(STATUS_NOT_FOUND, null), LocalDateTime.now(),
        getMessage(ex.getResourceId(), ex.getArgs()), null);
  }

  /**
   * Generic error message from any object validation constraints, doing lookup from message
   * resources file based on @{@link Locale}
   *
   * @param {@link MethodArgumentNotValidException}
   * @return {@link Error}
   */
  @ResponseStatus(UNPROCESSABLE_ENTITY)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Errors handleValidationException(MethodArgumentNotValidException ex) {

    return new Errors(getMessage(STATUS_UNPROCESSABLE_ENTITY, null), LocalDateTime.now(), VALIDATION_FAILURE,
        ex.getBindingResult().getFieldErrors().stream()
            .map(e -> new Error(e.getField(), e.getDefaultMessage())).collect(Collectors
            .toList()));
  }

  /**
   * Look up method to find the message based on resource key
   *
   * @param resourceId
   * @param args
   * @return Resource value from messages(_xx).properties
   */
  private String getMessage(String resourceId, Object[] args) {
    Locale locale = LocaleContextHolder.getLocale();
    return messageSource.getMessage(resourceId, args, locale);
  }
}
