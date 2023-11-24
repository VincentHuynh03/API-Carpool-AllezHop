package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Notification
import com.example.allezhop.Modèles.Reservation
import crosemont.tdi.g66.restaurantapirest.DAO.SourceDonnées
import org.springframework.stereotype.Repository




@Repository
class NotificationImpl():  NotificationDAO {


    override fun chercherTous(): List<Notification> = SourceDonnées.notifications
    override fun chercherParCode(code: Int): List<Notification>? {
        TODO("Not yet implemented")
    }


    override fun modifier(code: String, notification: Notification): Notification? {
        TODO("Not yet implemented")
    }

    override fun ajouter(notification: Notification): Notification? {
        SourceDonnées.notifications.add(notification);
        return notification;
    }


    override fun supprimer(notification: Notification): Notification? {
        SourceDonnées.notifications.remove(notification);
        return notification;
    }

}


