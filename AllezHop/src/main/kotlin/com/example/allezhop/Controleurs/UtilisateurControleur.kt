package com.example.allezhop.Controleurs

import com.example.allezhop.Services.UtilisateurService
import com.example.allezhop.DAO.IntrouvableException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class UtilisateurControleur(val service: UtilisateurService) {


    @GetMapping("/utilisateurs")
    fun obtenirUtilisateurs() = service.chercherTous()


    @GetMapping("/utilisateurs/{code}")
    fun obtenirUtilisateursParCode(@PathVariable code: String) {
        service.chercherParCode(code)?: throw IntrouvableException("L'utilisateur  est INTROUVABLE.")
    }


}