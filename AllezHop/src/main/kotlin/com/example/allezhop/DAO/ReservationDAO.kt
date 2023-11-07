package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Reservation

interface ReservationDAO : DAO<Reservation> {

    override fun chercherTous(): List<Reservation>

    override fun chercherParCode(code: String): List<Reservation>?

    override fun supprimer(code: String): Boolean

    override fun modifier(code: String, reservation: Reservation): Reservation?

    override fun ajouter(reservation: Reservation): Reservation?
}