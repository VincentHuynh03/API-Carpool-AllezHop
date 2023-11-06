package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Reservation
import crosemont.tdi.g66.restaurantapirest.DAO.SourceDonnées
import org.springframework.stereotype.Repository




@Repository
class ReservationImpl():  ReservationDAO {


    override fun chercherTous(): List<Reservation> = SourceDonnées.reservations


    override fun chercherParCode(code: Int): Reservation? = SourceDonnées.reservations.find{it.code == code}

    override fun supprimer(code: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun modifier(code: Int, reservation: Reservation): Reservation? {
        TODO("Not yet implemented")
    }

    override fun ajouter(reservation: Reservation): Reservation? {
        TODO("Not yet implemented")
    }


}


