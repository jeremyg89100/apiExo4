package com.exo4.exo4.service;

import com.exo4.exo4.model.Produit;
import com.exo4.exo4.repository.ProduitRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
/**
* Service contenant la logique métier pour les produits.
* Le service est le chef d'orchestre : il coordonne les appels
* au repository et applique les règles métier.
*/
@Service
public class ProduitService {
    private final ProduitRepository produitRepository;
    private final NotificationService notificationService;
 /**
 * Injection de dépendance par constructeur.
 * Spring injecte automatiquement le ProduitRepository.
 */
 public ProduitService(ProduitRepository produitRepository, @Qualifier ("emailNotification")NotificationService notificationService) {
 this.produitRepository = produitRepository;
 this.notificationService = notificationService;
 }
 /**
 * Retourne tous les produits.
 */
 public List<Produit> recupererTous() {
 return produitRepository.findAll();
 }
 /**
 * Retourne un produit par son ID.
 * @return Optional vide si le produit n'existe pas.
 */
 public Optional<Produit> recupererParId(Long id) {
 return produitRepository.findById(id);
 }
 /**
 * Crée un nouveau produit.
 * @param produit Le produit à créer (sans ID).
 * @return Le produit créé (avec ID généré).
 */
 public Produit creer(Produit produit) {
 // On s'assure que l'ID est null pour forcer une création (pas une mise à jour)
    produit.setId(null);
    String nom = produit.getNom();
    notificationService.notifier("stock", "Nouveau produit :  " + nom);
    return produitRepository.save(produit);
 }
 /**
 * Met à jour un produit existant.
 * @param id L'ID du produit à modifier.
 * @param produitModifie Les nouvelles valeurs.
 * @return Optional contenant le produit mis à jour, ou vide si l'ID n'existe
pas.
 */
 public Optional<Produit> mettreAJour(Long id, Produit produitModifie) {
 return produitRepository.findById(id)
 .map(produitExistant -> {
 produitExistant.setNom(produitModifie.getNom());
  produitExistant.setDescription(produitModifie.getDescription());
produitExistant.setPrix(produitModifie.getPrix());
produitExistant.setStock(produitModifie.getStock());
return produitRepository.save(produitExistant);
 });
 }
 /**
 * Supprime un produit par son ID.
 * @return true si le produit existait et a été supprimé, false sinon.
 */
 public boolean supprimer(Long id) {
 if (produitRepository.existsById(id)) {
 produitRepository.deleteById(id);
 return true;
 }
 return false;
 }
 /**
 * Recherche des produits par nom (recherche partielle, insensible à la casse).
 */
 public List<Produit> rechercherParNom(String nom) {
 return produitRepository.findByNomContainingIgnoreCase(nom);
 }
}
