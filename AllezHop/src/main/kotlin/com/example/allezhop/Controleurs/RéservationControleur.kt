package com.example.allezhop.Controleurs

import com.example.allezhop.DAO.ReservationDAO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class RéservationControleur(val service: ReservationDAO) {


    @GetMapping("/reservations")
    fun obtenirReservations() = service.chercherTous()
}