package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Notification
import com.example.allezhop.Modèles.Reservation
import crosemont.tdi.g66.restaurantapirest.DAO.SourceDonnées
import org.springframework.stereotype.Repository




@Repository
class NotificationImpl():  NotificationDAO {


    override fun chercherTous(): List<Notification> = SourceDonnées.notifications


    override fun chercherParCode(code: String): List<Notification>? {
        TODO()
    }

    override fun supprimer(code: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun modifier(code: String, notification: Notification): Notification? {
        TODO("Not yet implemented")
    }

    override fun ajouter(notification: Notification): Notification? {
        TODO("Not yet implemented")
    }


}


