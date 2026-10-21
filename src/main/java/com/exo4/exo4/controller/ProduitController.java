package com.exo4.exo4.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exo4.exo4.model.Produit;
import com.exo4.exo4.service.ProduitService;

import java.util.List;
/**
* Contrôleur REST pour les produits.
* Chaque méthode correspond à un endpoint de l'API.
*
* @RestController = @Controller + @ResponseBody
* → Toutes les méthodes retournent du JSON (pas des vues HTML).
*/
@RestController
@RequestMapping("/api/v1/produits")
public class ProduitController {
 private final ProduitService produitService;
 public ProduitController(ProduitService produitService) {
    this.produitService = produitService;
 }
 // ============================================================
 // GET /api/v1/produits — Lister tous les produits
 // ============================================================
 @GetMapping
 public ResponseEntity<List<Produit>> listerTous() {
 List<Produit> produits = produitService.recupererTous();
 return ResponseEntity.ok(produits); // 200 OK
 }
 // ============================================================
 // GET /api/v1/produits/{id} — Obtenir un produit par son ID
 // ============================================================
 @GetMapping("/{id}")
 public ResponseEntity<Produit> obtenirParId(@PathVariable Long id) {
 return produitService.recupererParId(id)
 .map(ResponseEntity::ok) // 200 OK si trouvé
 .orElse(ResponseEntity.notFound().build()); // 404 si non trouvé
 }
 // ============================================================
 // POST /api/v1/produits — Créer un nouveau produit
 // ============================================================
 @PostMapping
 public ResponseEntity<Produit> creer(@Valid @RequestBody Produit produit) {
    Produit produitCree = produitService.creer(produit);
    return ResponseEntity
    .status(HttpStatus.CREATED) // 201 Created
    .body(produitCree);
 }
 // ============================================================
 // PUT /api/v1/produits/{id} — Mettre à jour un produit
 // ============================================================
 @PutMapping("/{id}")
 public ResponseEntity<Produit> mettreAJour(@PathVariable Long id,
 @Valid @RequestBody Produit produit)
{
 return produitService.mettreAJour(id, produit)
 .map(ResponseEntity::ok) // 200 OK si mis à jour
 .orElse(ResponseEntity.notFound().build()); // 404 si non trouvé
 }
 // ============================================================
 // DELETE /api/v1/produits/{id} — Supprimer un produit
 // ============================================================
 @DeleteMapping("/{id}")
 public ResponseEntity<Void> supprimer(@PathVariable Long id) {
 if (produitService.supprimer(id)) {
 return ResponseEntity.noContent().build(); // 204 No Content
 }
 return ResponseEntity.notFound().build(); // 404 si non trouvé
 }
 // ============================================================
 // GET /api/v1/produits/recherche?nom=clavier — Rechercher par nom
 // ============================================================
 @GetMapping("/recherche")
 public ResponseEntity<List<Produit>> rechercherParNom(@RequestParam String nom) {
 List<Produit> resultats = produitService.rechercherParNom(nom);
 return ResponseEntity.ok(resultats);
 }
}
