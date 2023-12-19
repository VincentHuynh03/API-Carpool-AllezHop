package com.example.allezhop.Controleurs

import com.example.allezhop.Services.TrajetService
import com.example.allezhop.DAO.IntrouvableException
import com.example.allezhop.Modèles.Adresse
import com.example.allezhop.Modèles.Trajet
import com.example.allezhop.Modèles.Utilisateur
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.security.Principal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@RestController
class TrajetControleur(val service: TrajetService) {
    
    @GetMapping("/trajets")
    fun obtenirTrajets() = service.chercherTous()

    @GetMapping("/trajets/{code}")
    fun obtenirTrajetsParCode(@PathVariable code: String) = service.chercherParCode(code) ?: throw IntrouvableException("Le trajet avec le $code n'est pas inscrit au service.")

    @GetMapping("/trajets?conducteurs={nom}")
    fun obtenirTrajetsParConducteurNom(@RequestParam nom: String) = service.chercherParConducteurNom(nom) ?: throw IntrouvableException("Le trajet avec le conducteur qui a le nom $nom n'est pas inscrit au service ou n'as pas de trajets")

    @GetMapping("/trajets?date={date}")
    fun obtenirTrajetsParDate(@RequestParam date: String): List<Trajet>?{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateEnDate = LocalDateTime.parse(date, formatter)
        return service.chercherParDate(dateEnDate) ?: throw IntrouvableException("Le trajet avec la date $date n'existe pas")
    }

    @GetMapping("/trajets?ville={ville}")
    fun obtenirTrajetsParVille(@RequestParam ville: String) = service.chercherParVille(ville) ?: throw IntrouvableException("Le trajet avec la ville $ville n'existe pas")
    @GetMapping("/trajets?état={état}")
    fun obtenirTrajetsParÉtat(@RequestParam état: String) = service.chercherParÉtat(état) ?: throw IntrouvableException("Le trajet avec l'état $état n'existe pas")
    @GetMapping("/trajets?pays={pays}")
    fun obtenirTrajetsParPays(@RequestParam pays: String) = service.chercherParPays(pays) ?: throw IntrouvableException("Le trajet avec le pays $pays n'existe pas")


    @Operation(
        summary = "Ajouter un trajet.",
        description = "Ajouter un trajet au service.",
        operationId = "AjouterTrajet",
        responses = [
            ApiResponse(responseCode = "201", description = "Le trajet a été ajouté."),
            ApiResponse(responseCode = "400", description = "La requête est mal formulée."),
            ApiResponse(responseCode = "401", description = "L'utilisateur voulant effectuer l'opération n'est pas correctement authentifié."),
            ApiResponse(responseCode = "403", description = "L'utilisateur voulant effectuer l'opération n'a pas les droits nécessaires."),
            ApiResponse(responseCode = "409", description = "Le trajet est déjà inscrit au service.")
        ])
    @PostMapping(
        value = ["/trajets"],
        consumes = ["application/json"],
        produces = ["application/json"])
    fun ajouterTrajet(@RequestBody trajet: Trajet, utilsateur: Principal): ResponseEntity<Trajet> {
        val nouveauTrajet: Trajet? = service.ajouter(trajet, utilsateur.name)
        if (nouveauTrajet != null) {
            val uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{code}")
                .buildAndExpand(nouveauTrajet.code)
                .toUri()

            return ResponseEntity.created(uri).body(nouveauTrajet)
        }
        return ResponseEntity.badRequest().build()
    }


    @Operation(
        summary = "Modifier un trajet.",
        description = "Modifie les informations sur le trajet.",
        operationId = "majTrajet",
        responses = [
            ApiResponse(responseCode = "200", description = "Les informations sur le trajet ont été mises à jour."),
            ApiResponse(responseCode = "201", description = "Le trajet n'existait pas, il a été ajouté."),
            ApiResponse(responseCode = "400", description = "La requête est mal formulée."),
            ApiResponse(responseCode = "401", description = "L'utilisateur voulant effectuer l'opération n'est pas correctement authentifié."),
            ApiResponse(responseCode = "403", description = "L'utilisateur voulant effectuer l'opération n'a pas les droits nécessaires."),
        ])
    @PutMapping(
        value = ["/trajets/{code}"],
        consumes = ["application/json"],
        produces = ["application/json"])
    fun modifierTrajet(@PathVariable code: Int, @RequestBody trajet: Trajet, utilsateur: Principal): ResponseEntity<Trajet>  {
        val nouveauTrajet: Trajet? = service.modifier(code, trajet, utilsateur.name)
        if (nouveauTrajet != null) {
            val uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{code}")
                .buildAndExpand(nouveauTrajet.code)
                .toUri()

            return ResponseEntity.created(uri).body(nouveauTrajet)
        }
        return ResponseEntity.badRequest().build()
    }

    @DeleteMapping("/trajets/{code}")
    fun supprimerTrajet(@PathVariable code: String, utilsateur: Principal): ResponseEntity<Unit> {
        val trajet = service.chercherParCode(code)
            ?: throw IntrouvableException("Le trajet est INTROUVABLE. Écran Bleu si je pouvais.")
        service.supprimer(code, utilsateur.name)
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
