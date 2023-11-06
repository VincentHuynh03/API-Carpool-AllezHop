package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Notification

interface NotificationDAO : DAO<Notification> {

    override fun chercherTous(): List<Notification>

    override fun chercherParCode(code: Int): Notification?

    override fun supprimer(code: Int): Boolean

    override fun modifier(code: Int, notification: Notification): Notification?

    override fun ajouter(notification: Notification): Notification?
}