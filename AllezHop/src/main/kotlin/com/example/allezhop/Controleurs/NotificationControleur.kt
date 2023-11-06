package com.example.allezhop.Controleurs

import com.example.allezhop.DAO.IntrouvableException
import com.example.allezhop.Services.NotificationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class NotificationControleur(val service: NotificationService)  {

    @GetMapping("/notifications")
    fun obtenirNotifications() = service.chercherTous()


    @GetMapping("/notifications/{code}")
    fun obtenirNotificationsParCode(@PathVariable code: Int) {
        service.chercherParCode(code)?: throw IntrouvableException("La reservation  est INTROUVABLE. Écran Bleu si je pouvais.")
    }
}