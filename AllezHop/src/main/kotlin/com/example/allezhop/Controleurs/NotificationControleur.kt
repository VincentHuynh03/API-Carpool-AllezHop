package com.example.allezhop.Controleurs

import com.example.allezhop.DAO.ReservationDAO
import com.example.allezhop.Services.NotificationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class NotificationControleur(val service: NotificationService)  {

    @GetMapping("/notifications")
    fun obtenirNotifications() = service.chercherTous()
}