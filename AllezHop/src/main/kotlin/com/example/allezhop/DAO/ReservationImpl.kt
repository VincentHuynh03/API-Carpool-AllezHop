package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Reservation
import crosemont.tdi.g66.restaurantapirest.DAO.SourceDonnées
import org.springframework.stereotype.Repository




@Repository
class ReservationImpl():  ReservationDAO {


    override fun chercherTous(): List<Reservation> = SourceDonnées.reservations


    override fun chercherParCode(code: String): List<Reservation>? {
        TODO()
    }

    override fun supprimer(code: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun modifier(code: String, reservation: Reservation): Reservation? {
        TODO("Not yet implemented")
    }

    override fun ajouter(reservation: Reservation): Reservation? {
        TODO("Not yet implemented")
    }


}


