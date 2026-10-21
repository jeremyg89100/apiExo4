package com.exo4.exo4.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
 private final DureeInterceptor dureeInterceptor;
 private final CleApiInterceptor cleApiInterceptor;
 public WebConfig(DureeInterceptor dureeInterceptor, CleApiInterceptor cleApiInterceptor) {
 this.dureeInterceptor = dureeInterceptor;
 this.cleApiInterceptor = cleApiInterceptor;
 }
 @Override
 public void addInterceptors(InterceptorRegistry registry) {
 registry.addInterceptor(dureeInterceptor)
 .addPathPatterns("/api/**"); // uniquement les routes de l'API
  registry.addInterceptor(cleApiInterceptor)
 .addPathPatterns("/api/**");
 }
}
