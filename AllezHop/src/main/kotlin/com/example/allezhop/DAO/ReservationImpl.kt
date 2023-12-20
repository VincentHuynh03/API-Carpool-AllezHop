package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Reservation
import com.example.allezhop.Modèles.Trajet
import com.example.allezhop.Modèles.Utilisateur
import com.example.allezhop.exceptions.RessourceInexistanteException
import org.springframework.jdbc.core.query
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.time.LocalDateTime


@Repository
class ReservationImpl(val db: JdbcTemplate):  ReservationDAO {


    override fun chercherTous(): List<Reservation> = db.query("SELECT réservation.code, réservation.horodatage, réservation.trajet_code, utilisateur.utilisateur_code, utilisateur.nom, utilisateur.prénom, utilisateur.courriel, utilisateur.est_conducteur, utilisateur.est_passager FROM réservation JOIN utilisateur ON réservation.passager = utilisateur.utilisateur_code") { response, _ ->
        Reservation(
            code = response.getInt("code"),
            horodatage = response.getTimestamp("horodatage"),
            trajet_code = response.getInt("trajet_code"),
            Utilisateur(
                code = response.getString("utilisateur_code"),
                nom = response.getString("nom"),
                prénom = response.getString("prénom"),
                courriel = response.getString("courriel"),
                est_conducteur = response.getBoolean("est_conducteur"),
                est_passager = response.getBoolean("est_passager")
            )
        )
    }




    override fun chercherParCode(code: String): Reservation? {
        val result = db.query("SELECT réservation.code, réservation.horodatage, réservation.trajet_code, utilisateur.utilisateur_code, utilisateur.nom, utilisateur.prénom, utilisateur.courriel, utilisateur.est_conducteur, utilisateur.est_passager FROM réservation JOIN utilisateur ON réservation.passager = utilisateur.utilisateur_code WHERE réservation.code = ?", code) { response, _ ->
            Reservation(
                code = response.getInt("code"),
                horodatage = response.getTimestamp("horodatage"),
                trajet_code = response.getInt("trajet_code"),
                Utilisateur(
                    code = response.getString("utilisateur_code"),
                    nom = response.getString("nom"),
                    prénom = response.getString("prénom"),
                    courriel = response.getString("courriel"),
                    est_conducteur = response.getBoolean("est_conducteur"),
                    est_passager = response.getBoolean("est_passager")
                )
            )
        }

        return if (result.isEmpty()) null else result.first()
    }


    override fun chercherParPassagerNom(nom: String): List<Reservation>? {
        val result = db.query("SELECT réservation.code, réservation.horodatage, réservation.trajet_code, utilisateur.utilisateur_code, utilisateur.nom, utilisateur.prénom, utilisateur.courriel, utilisateur.est_conducteur, utilisateur.est_passager FROM réservation JOIN utilisateur ON réservation.passager = utilisateur.utilisateur_code WHERE utilisateur.nom = ?", arrayOf(nom)) { response, _ ->
            Reservation(
                code = response.getInt("code"),
                horodatage = response.getTimestamp("horodatage"),
                trajet_code = response.getInt("trajet_code"),
                Utilisateur(
                    code = response.getString("utilisateur_code"),
                    nom = response.getString("nom"),
                    prénom = response.getString("prénom"),
                    courriel = response.getString("courriel"),
                    est_conducteur = response.getBoolean("est_conducteur"),
                    est_passager = response.getBoolean("est_passager")
                )
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
            reservation.passager?.code
        )
        return reservation
    }



    override fun supprimer(code: String) {
        val sql = "DELETE FROM réservation WHERE code = ?"
        db.update(sql, code)
    }

    override fun modifier(code: String, reservation: Reservation): Reservation? {
        val sql =
            "UPDATE réservation SET horodatage = ?, trajet_code = ?, passager = ? WHERE code = ?"
        db.update(
            sql,
            reservation.horodatage,
            reservation.trajet_code,
            reservation.passager?.code,code
        )
        return reservation
    }


    override fun validerPassagerAvecSesReservations(code_Reservation: String, code_util: String?): Boolean {
        val trajet = chercherParCode(code_Reservation)
        if (trajet != null){
            val passagerCode = trajet.passager?.code
            if (passagerCode == code_util) {
                return true
            }
        } else {
            throw RessourceInexistanteException("La réservation n'est pas inscrit au service.")
        }
        return false
    }

}


