package com.example.allezhop.Controleurs

import com.example.allezhop.Services.UtilisateurService
import com.example.allezhop.DAO.IntrouvableException
import com.example.allezhop.Modèles.Trajet
import com.example.allezhop.Modèles.Utilisateur
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.util.*


@RestController
class UtilisateurControleur(val service: UtilisateurService) {


    @GetMapping("/utilisateurs")
    fun obtenirUtilisateurs() = service.chercherTous()
    @GetMapping("/utilisateurs/nom/{nom}")
    fun obtenirUtilisateursParNom(@PathVariable nom: String) = service.chercherParNom(nom) ?: throw IntrouvableException("L'utilisateur avec le nom $nom est INTROUVABLE.")
    @GetMapping("/utilisateurs/prénom/{prénom}")
    fun obtenirUtilisateursParPrénom(@PathVariable prénom: String) = service.chercherParPrénom(prénom) ?: throw IntrouvableException("L'utilisateur avec le prénom $prénom est INTROUVABLE.")
    @GetMapping("/utilisateurs/courriel/{courriel}")
    fun obtenirUtilisateursParCourriel(@PathVariable courriel: String) = service.chercherParCourriel(courriel) ?: throw IntrouvableException("L'utilisateur avec le courriel $courriel est INTROUVABLE.")



    @GetMapping("/utilisateurs/{code}")
    fun obtenirUtilisateursParCode(@PathVariable code: String) = service.chercherParCode(code) ?: throw IntrouvableException("utilisateur $code n'est pas inscrit au service.")

    @PostMapping("/utilisateurs")
    fun ajouterTrajet(@RequestBody utilisateurs: Utilisateur): ResponseEntity<Utilisateur> {
        val trajetAdded: Utilisateur? = service.ajouter(utilisateurs)
        return if (trajetAdded != null) {
            ResponseEntity.created(location(trajetAdded.code)).body(trajetAdded)
        } else {
            ResponseEntity.noContent().build()
        }
    }
    /*
    @DeleteMapping("/utilisateurs")
    fun supprimerUtilisateur(@RequestBody utilisateur: Utilisateur) {
        service.supprimer(utilisateur)
    }
    */

    private fun location(code: Int): URI {
        return ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{code}")
            .buildAndExpand(code)
            .toUri()
    }

}