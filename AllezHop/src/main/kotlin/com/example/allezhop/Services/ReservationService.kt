package com.example.allezhop.Services

import com.example.allezhop.DAO.ReservationDAO
import com.example.allezhop.Modèles.Reservation
import com.example.allezhop.Modèles.Trajet
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ReservationService(val dao: ReservationDAO) {

    fun chercherTous(): List<Reservation> = dao.chercherTous()
    fun chercherParCode(code: Int): List<Reservation>? = dao.chercherParCode(code.toInt())

    fun ajouter(reservation: Reservation) = dao.ajouter(reservation)

    fun chercherParPassagerNom(nom: String): List<Reservation>? = dao.chercherParPassagerNom(nom)

    fun chercherParHorodatage(date: LocalDateTime): List<Reservation>? = dao.chercherParHorodatage(date)
    fun modifier(code: Int, reservation: Reservation) = dao.modifier(code, reservation)

    fun supprimer(code: String) {
        dao.supprimer(code)
    }


}