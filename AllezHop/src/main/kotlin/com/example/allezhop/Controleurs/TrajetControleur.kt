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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@RestController
class TrajetControleur(val service: TrajetService) {
    
    @GetMapping("/trajets")
    fun obtenirTrajets() = service.chercherTous()

    @GetMapping("/trajets/{code}")
    fun obtenirTrajetsParCode(@PathVariable code: String) = service.chercherParCode(code) ?: throw IntrouvableException("Le trajet avec le $code n'est pas inscrit au service.")


    @GetMapping("/trajets/conducteurs/{nom}")
    fun obtenirTrajetsParConducteurNom(@PathVariable nom: String) = service.chercherParConducteurNom(nom) ?: throw IntrouvableException("Le trajet avec le conducteur qui a le nom $nom n'est pas inscrit au service ou n'as pas de trajets")

    @GetMapping("/trajets/date/{date}")
    fun obtenirTrajetsParDate(@PathVariable date: String): List<Trajet>?{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateEnDate = LocalDateTime.parse(date, formatter)
        return service.chercherParDate(dateEnDate) ?: throw IntrouvableException("Le trajet avec la date $date n'existe pas")
    }

    @GetMapping("/trajets/ville/{ville}")
    fun obtenirTrajetsParVille(@PathVariable ville: String) = service.chercherParVille(ville) ?: throw IntrouvableException("Le trajet avec la ville $ville n'existe pas")
    @GetMapping("/trajets/état/{état}")
    fun obtenirTrajetsParÉtat(@PathVariable état: String) = service.chercherParÉtat(état) ?: throw IntrouvableException("Le trajet avec l'état $état n'existe pas")
    @GetMapping("/trajets/pays/{pays}")
    fun obtenirTrajetsParPays(@PathVariable pays: String) = service.chercherParPays(pays) ?: throw IntrouvableException("Le trajet avec le pays $pays n'existe pas")
    @PostMapping("/trajets")
    fun ajouterTrajet(@RequestBody trajet: Trajet): ResponseEntity<Trajet> {
        val trajetAdded: Trajet? = service.ajouter(trajet)
        return if (trajetAdded != null) {
            ResponseEntity.created(location(trajetAdded.code)).body(trajetAdded)
        } else {
            ResponseEntity.noContent().build()
        }
    }

    @PutMapping("/trajets/{code}")
    fun modifierTrajet(@PathVariable code: Int, @RequestBody trajet: Trajet) = service.modifier(code, trajet)

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
