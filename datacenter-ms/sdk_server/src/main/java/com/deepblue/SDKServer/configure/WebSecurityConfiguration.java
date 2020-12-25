package com.deepblue.SDKServer.configure;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springboot.KeycloakBaseSpringBootConfiguration;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.Principal;


@KeycloakConfiguration
@EnableConfigurationProperties(KeycloakSpringBootProperties.class)
@Order(99)
public class WebSecurityConfiguration extends KeycloakWebSecurityConfigurerAdapter {


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(keycloakAuthenticationProvider());
    }


    @Bean
    public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }


    /* this defines that we want to use the Spring Boot properties file support instead of the default keycloak.json */
    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/pub/**",
                        "/test/**",
                        "/ic-card/check",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/swagger-ui.html",
                        "/static/**",
                        "/configuration/ui",
                        "/v2/api-docs",
                        "/actuator/**",
//            "/**",
                        "/error",
                        "/mappings",
                        "/env",
                        "/configprops",
                        "/logfile",
                        "/",
                        "/visitors/schedule/exist",
                        "/pass-policy/security-property/**",
                        "/user/selectUser/publicKey/*",
                        "/user/selectUser/privateKey/*",
                        "/api/*")
                .permitAll()
                .anyRequest().authenticated();
    }


    /**
     * Allows to inject requests scoped wrapper for {@link KeycloakSecurityContext}.
     * <p>
     * Returns the {@link KeycloakSecurityContext} from the Spring {@link ServletRequestAttributes}'s
     * {@link Principal}.
     * <p>
     * The principal must support retrieval of the KeycloakSecurityContext, so at this point, only
     * {@link KeycloakPrincipal} values and {@link KeycloakAuthenticationToken} are supported.
     *
     * @return the current <code>KeycloakSecurityContext</code>
     */
    @Bean
    @Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public KeycloakSecurityContext provideKeycloakSecurityContext() {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        Principal principal = attributes.getRequest().getUserPrincipal();
        if (principal == null) {
            return null;
        }

        if (principal instanceof KeycloakAuthenticationToken) {
            principal = Principal.class
                    .cast(KeycloakAuthenticationToken.class.cast(principal).getPrincipal());
        }

        if (principal instanceof KeycloakPrincipal) {
            return KeycloakPrincipal.class.cast(principal).getKeycloakSecurityContext();
        }

        return null;
    }

    /**
     * Ensures the correct registration of KeycloakSpringBootConfigResolver when Keycloaks
     * AutoConfiguration is explicitly turned off in application.yml {@code keycloak.enabled: false}.
     */
    @Configuration
    static class CustomKeycloakBaseSpringBootConfiguration extends
            KeycloakBaseSpringBootConfiguration {

    }
}
