package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Reservation
import crosemont.tdi.g66.restaurantapirest.DAO.SourceDonnées
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository




@Repository
class ReservationImpl(val db: JdbcTemplate):  ReservationDAO {


    override fun chercherTous(): List<Reservation> = SourceDonnées.reservations




    override fun chercherParCode(code: String): List<Reservation>? {
        TODO()
    }






    override fun ajouter(reservation: Reservation): Reservation? {
        val sql = "INSERT INTO your_reservation_table (code, horodatage, trajet_code, utilisateur_code) VALUES (?, ?, ?, ?)"
        db.update(
            sql,
            reservation.code,
            reservation.horodatage,
            reservation.trajet_code,
            reservation.utilisateur_code
        )
        return reservation
    }


    override fun supprimer(reservation: Reservation): Reservation? {
        val sql = "DELETE FROM your_reservation_table WHERE code = ?"
        db.update(sql, reservation.code)
        return reservation
    }

    override fun modifier(code: String, reservation: Reservation): Reservation? {
        val sql =
            "UPDATE your_reservation_table SET horodatage = ?, trajet_code = ?, utilisateur_code = ? WHERE code = ?"

        val modifier = db.update(
            sql,
            reservation.horodatage,
            reservation.trajet_code,
            reservation.utilisateur_code,
            code
        )
        return if (modifier > 0) reservation else null

    }
}


