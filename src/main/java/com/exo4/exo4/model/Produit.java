package com.exo4.exo4.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
/**
* Entité JPA représentant un produit.
* Chaque instance correspond à une ligne dans la table "produits".
*/
@Entity
@Table(name = "produits")
public class Produit {
 /**
 * Identifiant unique, généré automatiquement par la base de données.
 */
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 /**
 * Nom du produit. Ne peut pas être vide.
 */
 @NotBlank(message = "Le nom du produit est obligatoire")
 @Column(nullable = false)
 private String nom;
 /**
 * Description du produit.
 */
 private String description;
 /**
 * Prix du produit. Doit être positif.
 */
 @Positive(message = "Le prix doit être positif")
 @Column(nullable = false)
 private Double prix;
 /**
 * Quantité en stock.
 */
 private Integer stock;
 // --- Constructeurs ---
 /**
 * Constructeur sans arguments (requis par JPA). */
 public Produit() {
 }
 /**
 * Constructeur avec tous les champs (sauf l'id, généré automatiquement).
 */
 public Produit(String nom, String description, Double prix, Integer stock) {
 this.nom = nom;
 this.description = description;
 this.prix = prix;
 this.stock = stock;
 }
 // --- Getters et Setters ---
 public Long getId() {
    return id;
 }
 public void setId(Long id) {
    this.id = id;
 }
 public String getNom() {
     return nom;
 }
 public void setNom(String nom) {
    this.nom = nom;
 }
 public String getDescription() {
    return description;
 }
 public void setDescription(String description) {
    this.description = description;
 }
 public Double getPrix() {
    return prix;
 }
 public void setPrix(Double prix) {
    this.prix = prix;
 }
 public Integer getStock() {
    return stock;
 }
 public void setStock(Integer stock) {
    this.stock = stock;
 }
 @Override
 public String toString() {
    return "Produit{id=" + id + ", nom='" + nom + "', prix=" + prix + "}";
 }
}
