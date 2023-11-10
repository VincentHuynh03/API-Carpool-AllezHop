package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Reservation
import crosemont.tdi.g66.restaurantapirest.DAO.SourceDonnées
import org.springframework.stereotype.Repository




@Repository
class ReservationImpl():  ReservationDAO {


    override fun chercherTous(): List<Reservation> = SourceDonnées.reservations


    override fun supprimer(reservation: Reservation): Reservation? {
        SourceDonnées.reservations.remove(reservation);
        return reservation;
    }

    override fun chercherParCode(code: String): List<Reservation>? {
        TODO()
    }



    override fun modifier(code: String, reservation: Reservation): Reservation? {
        TODO("Not yet implemented")
    }

    override fun ajouter(reservation: Reservation): Reservation? {
        SourceDonnées.reservations.add(reservation);
        return reservation;
    }


}


