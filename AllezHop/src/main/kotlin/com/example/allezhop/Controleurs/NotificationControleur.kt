package com.example.allezhop.Controleurs

import com.example.allezhop.DAO.IntrouvableException
import com.example.allezhop.Modèles.Notification
import com.example.allezhop.Services.NotificationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*


@RestController
class NotificationControleur(val service: NotificationService) {

    @GetMapping("/notifications")
    fun obtenirNotifications() = service.chercherTous()


    @GetMapping("/notifications/{code}")
    fun obtenirNotificationsParCode(@PathVariable code: String) {
        service.chercherParCode(code)
            ?: throw IntrouvableException("La notification  est INTROUVABLE. Écran Bleu si je pouvais.")

        /*
        @PostMapping(value = ["/notifications"])
        fun ajouterNotification(@RequestBody notification: Notification): ResponseEntity<Notification> {
            val productAdded: Notification? = service.ajouter(notification)
            if (Objects.isNull(productAdded)) {
                return ResponseEntity.noContent().build<Notification?>()
            }
            val location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{code}")
                .buildAndExpand(productAdded?.id)
                .toUri()
            return ResponseEntity.created(location).build<Notification?>()

        }

        @DeleteMapping("/notifications")
        fun supprimerNotification(@RequestBody notification: Notification) {
            service.supprimer(notification)

        }

         */
    }
}