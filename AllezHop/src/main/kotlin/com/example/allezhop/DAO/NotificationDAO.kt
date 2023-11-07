package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Notification

interface NotificationDAO : DAO<Notification> {

    override fun chercherTous(): List<Notification>

    override fun chercherParCode(code: String): List<Notification>?

    override fun supprimer(code: String): Boolean

    override fun modifier(code: String, notification: Notification): Notification?

    override fun ajouter(notification: Notification): Notification?
}