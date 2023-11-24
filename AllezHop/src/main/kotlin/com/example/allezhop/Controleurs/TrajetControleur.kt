package com.example.allezhop.Controleurs

import com.example.allezhop.Services.TrajetService
import com.example.allezhop.DAO.IntrouvableException
import com.example.allezhop.Modèles.Trajet
import com.example.allezhop.Modèles.Utilisateur
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.util.*


@RestController
class TrajetControleur(val service: TrajetService) {


    @GetMapping("/trajets")
    fun obtenirTrajets() = service.chercherTous()

    @GetMapping("/trajets/{code}")
    fun obtenirTrajetsParCode(@PathVariable code: String) = service.chercherParCode(code)

    @PostMapping("/trajets")
    fun ajouterTrajet(@RequestBody trajet: Trajet): ResponseEntity<Trajet> {
        val trajetAdded: Trajet? = service.ajouter(trajet)
        return if (trajetAdded != null) {
            ResponseEntity.created(location(trajetAdded.code)).body(trajetAdded)
        } else {
            ResponseEntity.noContent().build()
        }
    }

    @DeleteMapping("/trajets/{code}")
    fun supprimerTrajet(@PathVariable code: String): ResponseEntity<Unit> {
        val trajet = service.chercherParCode(code)
            ?: throw IntrouvableException("Le trajet est INTROUVABLE. Écran Bleu si je pouvais.")
        service.supprimer(trajet.first())
        return ResponseEntity.noContent().build()
    }



    private fun location(code: Int): URI {
        return ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{code}")
            .buildAndExpand(code)
            .toUri()
    }

    }
