//package com.deepblueai.gateway.config;
//
//import org.springframework.context.MessageSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.support.ReloadableResourceBundleMessageSource;
//import org.springframework.web.servlet.config.annotation.*;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Locale;
//
//
///**
// * WebConfig
// *
// * @author
// */
//@Configuration
//@EnableWebMvc
//public class WebConfig extends WebMvcConfigurerAdapter {
//
//  private static final String[] RESOURCE_LOCATIONS = {"classpath:/META-INF/resources/",
//          "classpath:/resources/", "classpath:/static/", "classpath:/public/"};
//
//  @Override
//  public void addCorsMappings(CorsRegistry registry) {
//    registry.addMapping("/**")
//            .allowedOrigins("*")
//            .allowedMethods("GET", "POST", "HEAD", "PUT", "DELETE", "OPTIONS")
//            .allowedHeaders("Accept", "Origin", "X-Requested-With", "Content-Type", "Last-Modified")
//            .exposedHeaders("Set-Cookie")
//            .allowCredentials(true);
//
//  }
//
//  @Override
//  public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    registry.addResourceHandler("swagger-ui.html")
//            .addResourceLocations("classpath:/META-INF/resources/");
//
//    if (!registry.hasMappingForPattern("/webjars/**")) {
//      registry.addResourceHandler("/webjars/**").addResourceLocations(
//              "classpath:/META-INF/resources/webjars/");
//    }
//    if (!registry.hasMappingForPattern("/**")) {
//      registry.addResourceHandler("/**").addResourceLocations(
//              RESOURCE_LOCATIONS);
//    }
//
//  }
//
//  @Override
//  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//    configurer.enable();
//  }
//
//
//  @Bean
//  @Primary
//  public MessageSource messageSource() {
//    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//    messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
//    messageSource.setUseCodeAsDefaultMessage(true);
//    messageSource.setCacheSeconds(3000);
//    messageSource.setBasenames("classpath:messages/messages-gateway");
//    return messageSource;
//  }
//
//
//
//}
