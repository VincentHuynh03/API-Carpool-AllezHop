package com.example.allezhop.Controleurs

import com.example.allezhop.Services.ReservationService
import com.example.allezhop.DAO.IntrouvableException
import com.example.allezhop.Modèles.Reservation
import com.example.allezhop.Modèles.Trajet
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*


@RestController
class RéservationControleur(val service: ReservationService) {


    @GetMapping("/reservations")
    fun obtenirReservations() = service.chercherTous()

    @GetMapping("/reservations/{code}")
    fun obtenirReservationsParCode(@PathVariable code: Int) {
        service.chercherParCode(code)?: throw IntrouvableException("La reservation  est INTROUVABLE. Écran Bleu si je pouvais.")
    }


    @PostMapping(value = ["/reservations"])
    fun ajouterReservation(@RequestBody reservation: Reservation): ResponseEntity<Reservation> {
        val productAdded: Reservation? = service.ajouter(reservation)
        if (Objects.isNull(productAdded)) {
            return ResponseEntity.noContent().build<Reservation?>()
        }
        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{code}")
            .buildAndExpand(productAdded?.code)
            .toUri()
        return ResponseEntity.created(location).build<Reservation?>()

    }
    @DeleteMapping("/reservations")
    fun supprimerReservation(@RequestBody reservation: Reservation) {
        service.supprimer(reservation)
    }
}