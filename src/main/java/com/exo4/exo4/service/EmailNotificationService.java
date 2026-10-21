package com.exo4.exo4.service;

import org.springframework.stereotype.Service;

@Service("emailNotification")
public class EmailNotificationService implements NotificationService {
 @Override
 public void notifier(String destinataire, String message) {
    // Envoi réel d'un e-mail (non implémenté dans ce cours)
    System.out.println("[EMAIL-NOTIFICATION] " + destinataire + " : " + message);
 }
}
