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


@RestController
class RéservationControleur(val service: ReservationService) {


    @GetMapping("/reservations")
    fun obtenirReservations() = service.chercherTous()

    @GetMapping("/reservations/{code}")
    fun obtenirReservationParCode(@PathVariable code: Int) = service.chercherParCode(code) ?: throw IntrouvableException("La reservation $code n'est pas inscrit au service.")

    @GetMapping("/reservations/passagers/{nom}")
    fun obtenirReservationsParPassagerNom(@PathVariable nom: String) = service.chercherParPassagerNom(nom)  ?: throw IntrouvableException("Le passager avec le nom $nom n'est pas inscrit au service ou n'as pas de réservations")

    @GetMapping("/reservations?horodatage={date}")
    fun obtenirReservationsParHorodatage(@RequestParam date: String): List<Reservation>?{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateEnDate = LocalDateTime.parse(date, formatter)
        return service.chercherParHorodatage(dateEnDate) ?: throw IntrouvableException("La réservation avec l'horodatage $date n'existe pas")
    }

    @PostMapping("/reservations")
    fun ajouterReservation(@RequestBody reservation: Reservation): ResponseEntity<Reservation> {
        val reservationAdded = service.ajouter(reservation)
        return if (reservationAdded != null) {
            ResponseEntity.created(location(reservationAdded.code)).body(reservationAdded)
        } else {
            ResponseEntity.noContent().build()
        }
    }


    @PutMapping("/reservations/{code}")
    fun modifierTrajet(@PathVariable code: Int, @RequestBody reservation: Reservation) = service.modifier(code, reservation)  ?: throw IntrouvableException("La réservation avec le code $code n'existe pas")

    @DeleteMapping("/reservations/{code}")
    fun supprimerReservation(@PathVariable code: String): ResponseEntity<Unit> {
        service.supprimer(code)
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