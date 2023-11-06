package com.example.allezhop.Services

import com.example.allezhop.DAO.ReservationDAO
import com.example.allezhop.Modèles.Reservation
import com.example.allezhop.Modèles.Trajet
import org.springframework.stereotype.Service

@Service
class ReservationService(val dao: ReservationDAO) {

    fun chercherTous(): List<Reservation> = dao.chercherTous()
    fun chercherParCode(code: Int): Reservation? = dao.chercherParCode(code)

}