package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Notification
import com.example.allezhop.Modèles.Reservation
import crosemont.tdi.g66.restaurantapirest.DAO.SourceDonnées
import org.springframework.stereotype.Repository




@Repository
class NotificationImpl():  NotificationDAO {


    override fun chercherTous(): List<Notification> = SourceDonnées.notifications


    override fun chercherParCode(code: Int): Notification? = SourceDonnées.notifications.find{it.id == code}

    override fun supprimer(code: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun modifier(code: Int, notification: Notification): Notification? {
        TODO("Not yet implemented")
    }

    override fun ajouter(notification: Notification): Notification? {
        TODO("Not yet implemented")
    }


}


