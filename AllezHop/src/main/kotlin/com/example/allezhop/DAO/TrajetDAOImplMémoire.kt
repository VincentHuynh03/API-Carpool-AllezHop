package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Trajet
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.jdbc.core.query



@Repository
class TrajetDAOImplMémoire(val db: JdbcTemplate):  TrajetDAO {

    override fun chercherTous(): List<Trajet> = db.query("select * from trajet") { response, _ ->
        Trajet(response.getInt("code"),
            response.getString("destination"),
            response.getString("position_départ"),
            response.getTime("heure_arrivée"),
            response.getTime("heure_départ_max"),
            response.getInt("utilisateur_code"))
    }



    override fun chercherParCode(code: Int): List<Trajet>?  = db.query("select * from Trajet where code = ?", code) { response, _ ->
        Trajet(response.getInt("code"),
            response.getString("destination"),
            response.getString("position_départ"),
            response.getTime("heure_arrivée"),
            response.getTime("heure_départ_max"),
            response.getInt("utilisateur_code"))
    }


    override fun ajouter(trajet: Trajet): Trajet? {
        val insertQuery = "insert into trajet (code, destination, position_départ, heure_arrivée, heure_départ_max, utilisateur_code) values (?, ?, ?, ?, ?, ?)"
        db.update(insertQuery,
            trajet.code,
            trajet.destination,
            trajet.position_départ,
            trajet.heure_arrivée,
            trajet.heure_départ_max,
            trajet.conducteur)
        return trajet
    }

    override fun modifier(code: String, trajet: Trajet): Trajet? {
        val updateQuery = "update trajet set destination=?, position_départ=?, heure_arrivée=?, heure_départ_max=?, utilisateur_code=? where code=?"
        db.update(updateQuery,
            trajet.destination,
            trajet.position_départ,
            trajet.heure_arrivée,
            trajet.heure_départ_max,
            trajet.conducteur,
            code)
        return trajet
    }
    override fun supprimer(trajet: Trajet): Trajet? {
        val deleteQuery = "delete from trajet where code = ?"
        db.update(deleteQuery, trajet.code)
        return trajet
    }

}