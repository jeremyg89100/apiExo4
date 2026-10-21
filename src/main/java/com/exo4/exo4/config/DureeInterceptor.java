package com.exo4.exo4.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class DureeInterceptor implements HandlerInterceptor {
 private static final String DEBUT = "debutTraitement";
 @Override
 public boolean preHandle(HttpServletRequest request, HttpServletResponse
response,
 Object handler) {
 // On stocke l'heure de début dans la requête, PAS dans un champ :
 // l'intercepteur est un singleton partagé par toutes les requêtes.
 request.setAttribute(DEBUT, System.currentTimeMillis());
 return true; // false = la requête s'arrête ici, le contrôleur n'est pas appelé
 }
 @Override
 public void afterCompletion(HttpServletRequest request, HttpServletResponse
response,
 Object handler, Exception ex) {
 long duree = System.currentTimeMillis() - (long) request.getAttribute(DEBUT);
 // Remplacé par un logger dans la Partie 4b
 System.out.println(request.getMethod() + " " + request.getRequestURI()
 + " → " + response.getStatus() + " (" + duree + " ms)");
 }
}
