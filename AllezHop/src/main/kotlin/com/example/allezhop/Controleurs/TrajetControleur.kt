package com.example.allezhop.Controleurs

import com.example.allezhop.Services.TrajetService
import com.example.allezhop.DAO.IntrouvableException
import com.example.allezhop.Modèles.Trajet
import com.example.allezhop.Modèles.Utilisateur
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*


@RestController
class TrajetControleur(val service: TrajetService) {


    @GetMapping("/trajets")
    fun obtenirTrajets() = service.chercherTous()

    @GetMapping("/trajets/{code}")
    fun obtenirTrajetsParCode(@PathVariable code: String) {
        service.chercherParCode(code)
            ?: throw IntrouvableException("Le trajet est INTROUVABLE. Écran Bleu si je pouvais.")


/*
        @PostMapping(value = ["/trajets"])
        fun ajouterTrajet(@RequestBody trajet: Trajet): ResponseEntity<Trajet> {
            val productAdded: Trajet? = service.ajouter(trajet)
            if (Objects.isNull(productAdded)) {
                return ResponseEntity.noContent().build<Trajet?>()
            }
            val location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{code}")
                .buildAndExpand(productAdded?.code)
                .toUri()
            return ResponseEntity.created(location).build<Trajet?>()

        }

        @DeleteMapping("/trajets")
        fun supprimerTrajet(@RequestBody trajet: Trajet) {
            service.supprimer(trajet)

        }
        */

    }
}