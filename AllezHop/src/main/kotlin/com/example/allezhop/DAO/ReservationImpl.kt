package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Reservation
import org.springframework.jdbc.core.query
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository




@Repository
class ReservationImpl(val db: JdbcTemplate):  ReservationDAO {


    override fun chercherTous(): List<Reservation> = db.query("SELECT * FROM réservation") { response, _ ->
        Reservation(
            code = response.getInt("code"),
            horodatage = response.getTimestamp("horodatage"),
            trajet_code = response.getInt("trajet_code"),
            passager = response.getInt("utilisateur_code")
        )
    }




    override fun chercherParCode(code: Int): List<Reservation>? = db.query("select * from réservation where code = ?", code) { response, _ ->
        Reservation(
            code = response.getInt("code"),
            horodatage = response.getTimestamp("horodatage"),
            trajet_code = response.getInt("trajet_code"),
            passager = response.getInt("utilisateur_code")
        )
    }










    override fun ajouter(reservation: Reservation): Reservation? {
        val sql = "INSERT INTO réservation (code, horodatage, trajet_code, utilisateur_code) VALUES (?, ?, ?, ?)"
        db.update(
            sql,
            reservation.code,
            reservation.horodatage,
            reservation.trajet_code,
            reservation.passager
        )
        return reservation
    }


    override fun supprimer(reservation: Reservation): Reservation? {
        val sql = "DELETE FROM réservation WHERE code = ?"
        db.update(sql, reservation.code)
        return reservation
    }

    override fun modifier(code: String, reservation: Reservation): Reservation? {
        val sql =
            "UPDATE réservation SET horodatage = ?, trajet_code = ?, utilisateur_code = ? WHERE code = ?"

        val modifier = db.update(
            sql,
            reservation.horodatage,
            reservation.trajet_code,
            reservation.passager,
            code
        )
        return if (modifier > 0) reservation else null

    }
}


