package com.exo4.exo4.repository;

import com.exo4.exo4.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
* Repository pour l'entité Produit.
* En héritant de JpaRepository, Spring génère automatiquement
* les méthodes CRUD : findAll(), findById(), save(), deleteById(), etc.
*
* Pas besoin d'écrire l'implémentation : Spring Data le fait pour nous.
*/
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
 /**
 * Recherche des produits dont le nom contient la chaîne donnée (insensible à la
casse).
 * Spring Data génère automatiquement la requête SQL à partir du nom de la
méthode.
 * Équivalent SQL : SELECT * FROM produits WHERE LOWER(nom) LIKE LOWER('%nom%')
 */
 List<Produit> findByNomContainingIgnoreCase(String nom);
 /**
 * Recherche des produits dont le prix est inférieur ou égal au montant donné.
 * Équivalent SQL : SELECT * FROM produits WHERE prix <= ?
 */
 List<Produit> findByPrixLessThanEqual(Double prix);
}

