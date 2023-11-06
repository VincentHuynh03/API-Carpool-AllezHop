package com.example.allezhop.Controleurs

import com.example.allezhop.Services.ReservationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class RéservationControleur(val service: ReservationService) {


    @GetMapping("/reservations")
    fun obtenirReservations() = service.chercherTous()
}