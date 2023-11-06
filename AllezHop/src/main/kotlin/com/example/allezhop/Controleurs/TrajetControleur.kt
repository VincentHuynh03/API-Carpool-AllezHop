package com.example.allezhop.Controleurs

import com.example.allezhop.Services.TrajetService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TrajetControleur(val service: TrajetService) {


    @GetMapping("/trajets")
    fun obtenirTrajets() = service.chercherTous()
}