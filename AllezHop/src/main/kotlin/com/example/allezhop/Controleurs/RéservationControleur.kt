package com.example.allezhop.Controleurs

import com.example.allezhop.Services.ReservationService
import com.example.allezhop.DAO.IntrouvableException
import com.example.allezhop.Modèles.Reservation
import com.example.allezhop.Modèles.Trajet
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.web.bind.annotation.*
import java.security.Principal

import java.util.*
@RestController
class RéservationControleur(val service: ReservationService) {


    @GetMapping("/reservations")
    fun obtenirReservations() = service.chercherTous()

    @GetMapping("/reservations/{code}")
    fun obtenirReservationParCode(@PathVariable code: String) = service.chercherParCode(code) ?: throw IntrouvableException("La reservation $code n'est pas inscrit au service.")

    @GetMapping("/reservations/passagers/{nom}")
    fun obtenirReservationsParPassagerNom(@PathVariable nom: String) = service.chercherParPassagerNom(nom)  ?: throw IntrouvableException("Le passager avec le nom $nom n'est pas inscrit au service ou n'as pas de réservations")


    @Operation(
        summary = "Ajouter un réservation.",
        description = "Ajouter un réservation au service.",
        operationId = "AjouterRéservation",
        responses = [
            ApiResponse(responseCode = "201", description = "La réservation a été ajouté."),
            ApiResponse(responseCode = "400", description = "La requête est mal formulée."),
            ApiResponse(responseCode = "401", description = "L'utilisateur voulant effectuer l'opération n'est pas correctement authentifié."),
            ApiResponse(responseCode = "403", description = "L'utilisateur voulant effectuer l'opération n'a pas les droits nécessaires."),
            ApiResponse(responseCode = "409", description = "La réservation est déjà inscrit au service.")
        ])
    @PostMapping(
        value = ["/reservations"],
        consumes = ["application/json"],
        produces = ["application/json"])
    fun ajouterReservation(@RequestBody reservation: Reservation, utilisateur: Principal): ResponseEntity<Reservation> {
        val nouveauRéservation: Reservation? = service.ajouter(reservation, utilisateur.name)
        if (nouveauRéservation != null) {
            val uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{code}")
                .buildAndExpand(nouveauRéservation.code)
                .toUri()

            return ResponseEntity.created(uri).body(nouveauRéservation)
        }
        return ResponseEntity.internalServerError().build()
    }


    @Operation(
        summary = "Modifier un réservation.",
        description = "Modifie les informations sur la réservation.",
        operationId = "majRéservation",
        responses = [
            ApiResponse(responseCode = "200", description = "Les informations sur la réservation ont été mises à jour."),
            ApiResponse(responseCode = "201", description = "La réservation n'existait pas, il a été ajouté."),
            ApiResponse(responseCode = "400", description = "La requête est mal formulée."),
            ApiResponse(responseCode = "401", description = "L'utilisateur voulant effectuer l'opération n'est pas correctement authentifié."),
            ApiResponse(responseCode = "403", description = "L'utilisateur voulant effectuer l'opération n'a pas les droits nécessaires."),
        ])
    @PutMapping(
        value = ["/reservations/{code}"],
        consumes = ["application/json"],
        produces = ["application/json"])
    fun modifierTrajet(@PathVariable code: String, @RequestBody reservation: Reservation , utilisateur: Principal) :ResponseEntity<Reservation> {
        val nouveauRéservation: Reservation? = service.modifier(code, reservation, utilisateur.name)
        if (nouveauRéservation != null) {
            val uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{code}")
                .buildAndExpand(nouveauRéservation.code)
                .toUri()

            return ResponseEntity.created(uri).body(nouveauRéservation)
        }
        return ResponseEntity.ok(nouveauRéservation)
    }
    @DeleteMapping("/reservations/{code}")
    fun supprimerReservation(@PathVariable code: String, utilisateur: Principal): ResponseEntity<Reservation> {
        service.supprimer(code,utilisateur.name)
        return ResponseEntity.noContent().build()
    }

}