package com.deepblueai.gateway.filters;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class AccessLogFilter extends ZuulFilter {

  @Override
  public String filterType() {
    return "post";
  }


  @Override
  public int filterOrder() {
    return 0;
  }


  @Override
  public boolean shouldFilter() {
    return true;
  }


  @Override
  public Object run() {
    HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
    HttpServletResponse response = RequestContext.getCurrentContext().getResponse();

    log.info("request  authorization--> {} :",request.getHeader("authorization"));

    log.info("REQUEST:: > client ip {} access by {} 2 {} with parameters:::::{} header {}",
        getIpAddr(request),
        request.getMethod(),
        request.getRequestURL().toString(),
            request.getHeaderNames(),
        JSON.toJSONString(request.getParameterMap()));
    log.info("RESPONSE:: < http status::::{}", response.getStatus());
    return null;
  }


  public static String getIpAddr(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    if (ip != null && ip.length() > 0) {
      String[] ips = ip.split(",");
      for (int i = 0; i < ips.length; i++) {
        if (ips[i].trim().length() > 0 && !"unknown".equalsIgnoreCase(ips[i].trim())) {
          ip = ips[i].trim();
          break;
        }
      }
    }
    return ip;
  }
}
