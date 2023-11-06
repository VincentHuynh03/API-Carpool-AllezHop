package com.example.allezhop.Controleurs

import com.example.allezhop.Services.ReservationService
import crosemont.tdi.g66.restaurantapirest.exceptions.IntrouvableException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class RéservationControleur(val service: ReservationService) {


    @GetMapping("/reservations")
    fun obtenirReservations() = service.chercherTous()

    @GetMapping("/reservations/{code}")
    fun obtenirReservationsParCode(@PathVariable code: Int) {
        service.chercherParCode(code)?: throw IntrouvableException("La reservation  est INTROUVABLE. Écran Bleu si je pouvais.")
    }

}