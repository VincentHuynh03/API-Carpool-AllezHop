package com.example.allezhop.Controleurs

import com.example.allezhop.Services.TrajetService
import com.example.allezhop.DAO.IntrouvableException
import com.example.allezhop.Modèles.Adresse
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

    //@GetMapping("/trajets/conducteurs/{code}")
    //fun obtenirTrajetsParConducteur(@PathVariable code: String) = service.chercherParConducteurCode(code)

    @GetMapping("/trajets/conducteurs/{nom}")
    fun obtenirTrajetsParConducteurNom(@PathVariable nom: String) = service.chercherParConducteurNom(nom)

    @GetMapping("/trajets/date/{date}")
    fun obtenirTrajetsParDate(@PathVariable date: String) = service.chercherParDate(date)

    @GetMapping("/trajets/ville/{ville}")
    fun obtenirTrajetsParVille(@PathVariable ville: String) = service.chercherParVille(ville)

    @PostMapping("/trajets")
    fun ajouterTrajet(@RequestBody trajet: Trajet): ResponseEntity<Trajet> {
        val trajetAdded: Trajet? = service.ajouter(trajet)
        return if (trajetAdded != null) {
            ResponseEntity.created(location(trajetAdded.code)).body(trajetAdded)
        } else {
            ResponseEntity.noContent().build()
        }
    }

    @PutMapping("/trajets")
    fun modifierTrajet(@PathVariable code: String, @RequestBody trajet: Trajet) = service.modifier(code, trajet)

    @DeleteMapping("/trajets/{code}")
    fun supprimerTrajet(@PathVariable code: String): ResponseEntity<Unit> {
        val trajet = service.chercherParCode(code)
            ?: throw IntrouvableException("Le trajet est INTROUVABLE. Écran Bleu si je pouvais.")
        service.supprimer(code)
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
