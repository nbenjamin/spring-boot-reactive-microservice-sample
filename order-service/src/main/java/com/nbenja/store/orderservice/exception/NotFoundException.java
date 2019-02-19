package com.nbenja.store.orderservice.exception;

public class NotFoundException extends RuntimeException{

  private String resourceId;
  private String msg;
  private Object[] args;
  public NotFoundException(String msg, String resourceId, Object ... args) {
    super(msg);
    this.msg = msg;
    this.resourceId = resourceId;
    this.args = args;
  }
  public NotFoundException(String msg) {
    super(msg);
  }

  public String getResourceId() {
    return resourceId;
  }

  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Object[] getArgs() {
    return args;
  }

  public void setArgs(Object[] args) {
    this.args = args;
  }
}
