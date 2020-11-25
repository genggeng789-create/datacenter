package com.deepblueai.gateway.config;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.firewall.FirewalledRequest;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableOAuth2Sso
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.httpFirewall(allowFirewall());
  }

  @Bean
  public HttpFirewall allowFirewall() {
    StrictHttpFirewall fireWall = new StrictHttpFirewall() {
      @Override
      public FirewalledRequest getFirewalledRequest(HttpServletRequest request)
          throws RequestRejectedException {
        log.info("requestURI::::{}, contextPath::::{}, servletPath::::{}, pathInfo::::{},header::: {}",
            request.getRequestURI(), request.getContextPath(), request.getServletPath(),request.getHeaderNames(),
            request.getPathInfo());

        log.info("request  authorization {} :",request.getHeader("authorization"));

        return new FirewalledRequest(request) {
          @Override
          public void reset() {
          }
        };
      }
    };
    fireWall.setAllowUrlEncodedSlash(true);
    fireWall.setAllowSemicolon(true);
    fireWall.setAllowUrlEncodedPercent(true);
    fireWall.setAllowUrlEncodedPeriod(true);
    fireWall.setAllowBackSlash(true);
    return fireWall;
  }
}
