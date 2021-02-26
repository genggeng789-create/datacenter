package com.deepblue.report.tools;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public final class SecurityContextUtils {


    private SecurityContextUtils() {
    }

    /**
     * 获取用户角色列表
     */
    public static Set<String> getUserRoles() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Set<String> roles = new HashSet<>();

        if (null != authentication) {
            authentication.getAuthorities()
                    .forEach(e -> roles.add(e.getAuthority()));
        }
        return roles;
    }

    private static KeycloakSecurityContext loadKeycloakSecurityContext() {
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder
                .getContext()
                .getAuthentication();
        KeycloakPrincipal<KeycloakSecurityContext> keycloakPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) authentication
                .getPrincipal();
        return keycloakPrincipal.getKeycloakSecurityContext();
    }

    /**
     * 获取当前用户登录Token
     */
    public static String getJWTToken() {
        return loadKeycloakSecurityContext().getTokenString();
    }

    /**
     * 获取当前用户登录用户名
     */
    public static String getUsername() {
        return loadKeycloakSecurityContext().getToken().getPreferredUsername();
    }

    /**
     * 获取当前用户登录用户ID
     */
    public static String getUserId() {
        return loadKeycloakSecurityContext().getToken().getSubject();
    }
}
