package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Reservation
import com.example.allezhop.Modèles.Trajet
import org.springframework.jdbc.core.query
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.time.LocalDateTime


@Repository
class ReservationImpl(val db: JdbcTemplate):  ReservationDAO {


    override fun chercherTous(): List<Reservation> = db.query("SELECT * FROM réservation") { response, _ ->
        Reservation(
            code = response.getInt("code"),
            horodatage = response.getTimestamp("horodatage"),
            trajet_code = response.getInt("trajet_code"),
            passager = response.getString("passager")
        )
    }




    override fun chercherParCode(code: String): Reservation? {
        val result = db.query("select * from réservation where code = ?", code) { response, _ ->
            Reservation(
                code = response.getInt("code"),
                horodatage = response.getTimestamp("horodatage"),
                trajet_code = response.getInt("trajet_code"),
                passager = response.getString("passager")
            )
        }

        return if (result.isEmpty()) null else result.first()
    }

    override fun chercherParPassagerNom(nom: String): List<Reservation>? {
        val result = db.query("SELECT réservation.*, utilisateur.nom, utilisateur.prénom " + "FROM réservation " + "JOIN utilisateur ON réservation.passager = utilisateur.code " + "WHERE utilisateur.nom = ?", arrayOf(nom)) { response, _ ->
            Reservation(
                code = response.getInt("code"),
                horodatage = response.getTimestamp("horodatage"),
                trajet_code = response.getInt("trajet_code"),
                passager = response.getString("passager")
            )
        }

        return if (result.isEmpty()) null else result
    }

    override fun chercherParHorodatage(date: LocalDateTime): List<Reservation>? {
        val result = db.query("SELECT réservation.*, utilisateur.nom, utilisateur.prénom FROM réservation JOIN utilisateur ON réservation.passager = utilisateur.code WHERE réservation.horodatage = ?", arrayOf(
            Timestamp.valueOf(date))) { response, _ ->
            Reservation(
                code = response.getInt("code"),
                horodatage = response.getTimestamp("horodatage"),
                trajet_code = response.getInt("trajet_code"),
                passager = response.getString("passager")
            )
        }

        return if (result.isEmpty()) null else result
    }

    override fun ajouter(reservation: Reservation): Reservation? {
        val sql = "INSERT INTO réservation (horodatage, trajet_code, passager) VALUES (?, ?, ?)"
        db.update(
            sql,
            reservation.horodatage,
            reservation.trajet_code,
            reservation.passager
        )
        return reservation
    }


    override fun supprimer(code: String) {
        val sql = "DELETE FROM réservation WHERE code = ?"
        db.update(sql, code)
    }

    override fun modifier(code: Int, reservation: Reservation): Reservation? {
        val sql =
            "UPDATE réservation SET horodatage = ?, trajet_code = ?, passager = ? WHERE code = ?"

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


