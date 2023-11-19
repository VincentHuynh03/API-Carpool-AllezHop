package com.example.allezhop.Services

import com.example.allezhop.DAO.NotificationDAO
import com.example.allezhop.DAO.ReservationDAO
import com.example.allezhop.Modèles.Notification
import com.example.allezhop.Modèles.Reservation
import org.springframework.stereotype.Service

@Service
class NotificationService(val dao: NotificationDAO) {


    fun chercherTous(): List<Notification> = dao.chercherTous()
    fun chercherParCode(code: String): Notification? = dao.chercherParCode(code)

    fun ajouter(notification: Notification) = dao.ajouter(notification)

    fun supprimer(notification: Notification) = dao.supprimer(notification)

}