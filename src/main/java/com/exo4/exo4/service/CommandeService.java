package com.exo4.exo4.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service 
public class CommandeService {
 private final NotificationService notificationService;
 // @Qualifier force une implémentation précise, même si une autre est @Primary
 public CommandeService(@Qualifier ("emailNotification") NotificationService
notificationService) {
    this.notificationService = notificationService;
 }
}
