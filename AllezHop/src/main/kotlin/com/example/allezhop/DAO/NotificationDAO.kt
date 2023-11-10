package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Notification
import com.example.allezhop.Modèles.Reservation

interface NotificationDAO : DAO<Notification> {

    override fun chercherTous(): List<Notification>

    override fun chercherParCode(code: Int): Notification?

    override fun supprimer(notification: Notification): Notification?

    override fun modifier(code: Int, notification: Notification): Notification?

    override fun ajouter(notification: Notification): Notification?
}