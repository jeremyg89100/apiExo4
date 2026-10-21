package com.exo4.exo4.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component 
public class CleApiInterceptor implements HandlerInterceptor {
    private static final String API_KEY = "X-API-KEY";

    @Value ("${app.api.key}")
    private String expectedApiKey;

    @Override 
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            boolean isDeleteMapping = handlerMethod.hasMethodAnnotation(DeleteMapping.class);

            if (!isDeleteMapping) {
                return true;
            }
        }
        String requestedApiKey = request.getHeader(API_KEY);

        if (requestedApiKey == null || !requestedApiKey.equals(requestedApiKey)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex != null) {
            System.err.println("Erreur clé API manquante ou invalide : " + ex.getMessage());
        }
    }
    
}
