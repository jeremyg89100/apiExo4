package com.exo4.exo4.service;

import org.springframework.stereotype.Service;

@Service
public class ConsoleNotificationService implements NotificationService {
 @Override
 public void notifier(String destinataire, String message) {
    // Remplacé par un logger dans la Partie 4b
    System.out.println("[NOTIFICATION] " + destinataire + " : " + message);
 }
}
