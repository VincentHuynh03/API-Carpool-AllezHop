package com.example.allezhop.Controleurs

import com.example.allezhop.Services.ReservationService
import com.example.allezhop.DAO.IntrouvableException
import com.example.allezhop.Modèles.Reservation
import com.example.allezhop.Modèles.Trajet
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.util.*


@RestController
class RéservationControleur(val service: ReservationService) {


    @GetMapping("/reservations")
    fun obtenirReservations() = service.chercherTous()

    @GetMapping("/reservations/{code}")
    fun obtenirReservationParCode(@PathVariable code: String) = service.chercherParCode(code)
    @PostMapping("/reservations")
    fun ajouterReservation(@RequestBody reservation: Reservation): ResponseEntity<Reservation> {
        val reservationAdded = service.ajouter(reservation)
        return if (reservationAdded != null) {
            ResponseEntity.created(location(reservationAdded.code)).body(reservationAdded)
        } else {
            ResponseEntity.noContent().build()
        }
    }
    @DeleteMapping("/reservations/{code}")
    fun supprimerReservation(@PathVariable code: String): ResponseEntity<Unit> {
        val reservation = service.chercherParCode(code)?.first()
            ?: throw IntrouvableException("La reservation est INTROUVABLE. Écran Bleu si je pouvais.")

        service.supprimer(reservation)
        return ResponseEntity.noContent().build()
    }




    //POUR URI
    private fun location(code: Int): URI {
        return ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{code}")
            .buildAndExpand(code)
            .toUri()
    }
}