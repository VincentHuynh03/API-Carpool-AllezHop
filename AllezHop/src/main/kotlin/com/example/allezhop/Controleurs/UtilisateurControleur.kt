package com.example.allezhop.Controleurs

import com.example.allezhop.Services.UtilisateurService
import com.example.allezhop.DAO.IntrouvableException
import com.example.allezhop.Modèles.Utilisateur
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*


@RestController
class UtilisateurControleur(val service: UtilisateurService) {


    @GetMapping("/utilisateurs")
    fun obtenirUtilisateurs() = service.chercherTous()


    @GetMapping("/utilisateurs/{code}")
    fun obtenirUtilisateursParCode(@PathVariable code: String) {
        service.chercherParCode(code)?: throw IntrouvableException("L'utilisateur  est INTROUVABLE.")
    }
/*
    @PostMapping(value = ["/utilisateurs"])
    fun ajouterUtilisateur(@RequestBody utilisateur: Utilisateur): ResponseEntity<Utilisateur> {
        val productAdded: Utilisateur? = service.ajouter(utilisateur)
        if (Objects.isNull(productAdded)) {
            return ResponseEntity.noContent().build<Utilisateur?>()
        }
        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{code}")
            .buildAndExpand(productAdded?.code)
            .toUri()
        return ResponseEntity.created(location).build<Utilisateur?>()

    }
    @DeleteMapping("/utilisateurs")
    fun supprimerUtilisateur(@RequestBody utilisateur: Utilisateur) {
        service.supprimer(utilisateur)
    }
    */

}