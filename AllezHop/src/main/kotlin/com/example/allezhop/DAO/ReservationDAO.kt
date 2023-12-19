package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Reservation
import com.example.allezhop.Modèles.Trajet
import java.time.LocalDateTime

interface ReservationDAO : DAO<Reservation> {

    override fun chercherTous(): List<Reservation>

    override fun chercherParCode(code: String): Reservation?

    fun chercherParPassagerNom(nom : String): List<Reservation>?

    fun chercherParHorodatage(date: LocalDateTime): List<Reservation>?

    override fun supprimer(code: String)

    override fun modifier(code: Int, reservation: Reservation): Reservation?

    override fun ajouter(reservation: Reservation): Reservation?
}