package com.example.allezhop.Controleurs

import com.example.allezhop.Services.UtilisateurService
import crosemont.tdi.g66.restaurantapirest.exceptions.IntrouvableException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class UtilisateurControleur(val service: UtilisateurService) {


    @GetMapping("/utilisateurs")
    fun obtenirUtilisateurs() = service.chercherTous()


    @GetMapping("/utilisateurs/{code}")
    fun obtenirUtilisateursParCode(@PathVariable code: Int) {
        service.chercherParCode(code)?: throw IntrouvableException("L'utilisateur  est INTROUVABLE. Écran Bleu si je pouvais.")
    }


}