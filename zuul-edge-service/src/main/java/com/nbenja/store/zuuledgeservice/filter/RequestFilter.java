package com.nbenja.store.zuuledgeservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import java.util.UUID;

public class RequestFilter extends ZuulFilter {

  private static final String PRE = "pre";

  @Override
  public String filterType() {
    return PRE;
  }

  @Override
  public int filterOrder() {
    return 1;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() throws ZuulException {
    RequestContext requestContext = RequestContext.getCurrentContext();
    // print Zuul Request Headers
    requestContext.getZuulRequestHeaders().entrySet().stream().forEach(System.out::println);
    // print remote address
    System.out.println(requestContext.getRequest().getRemoteAddr());
    requestContext.addZuulRequestHeader("RequestID", UUID.randomUUID().toString());
    return null;
  }
}
