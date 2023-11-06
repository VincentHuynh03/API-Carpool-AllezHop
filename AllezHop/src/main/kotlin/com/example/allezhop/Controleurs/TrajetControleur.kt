package com.example.allezhop.Controleurs

import com.example.allezhop.Services.TrajetService
import crosemont.tdi.g66.restaurantapirest.exceptions.IntrouvableException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class TrajetControleur(val service: TrajetService) {


    @GetMapping("/trajets")
    fun obtenirTrajets() = service.chercherTous()

    @GetMapping("/trajets/{code}")
    fun obtenirTrajetsParCode(@PathVariable code: Int) {
        service.chercherParCode(code)?: throw IntrouvableException("Le trajets  est INTROUVABLE. Écran Bleu si je pouvais.")
    }
}