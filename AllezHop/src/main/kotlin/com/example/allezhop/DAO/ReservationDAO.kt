package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Reservation

interface ReservationDAO : DAO<Reservation> {

    override fun chercherTous(): List<Reservation>

    override fun chercherParCode(code: Int): Reservation?

    override fun supprimer(code: Int): Boolean

    override fun modifier(code: Int, reservation: Reservation): Reservation?

    override fun ajouter(reservation: Reservation): Reservation?
}