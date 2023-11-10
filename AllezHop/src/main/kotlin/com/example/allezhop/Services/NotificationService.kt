package com.example.allezhop.Services

import com.example.allezhop.DAO.NotificationDAO
import com.example.allezhop.DAO.ReservationDAO
import com.example.allezhop.Modèles.Notification
import com.example.allezhop.Modèles.Reservation
import org.springframework.stereotype.Service

@Service
class NotificationService(val dao: NotificationDAO) {


    fun chercherTous(): List<Notification> = dao.chercherTous()

    fun ajouter(notification: Notification) = dao.ajouter(notification)
    fun chercherParCode(code: String): List<Notification>? = dao.chercherParCode(code)

    fun supprimer(notification: Notification) = dao.supprimer(notification)

}