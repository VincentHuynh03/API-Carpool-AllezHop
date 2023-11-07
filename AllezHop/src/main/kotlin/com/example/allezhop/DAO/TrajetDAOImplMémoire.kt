package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Trajet
import com.example.allezhop.Modèles.Utilisateur
import crosemont.tdi.g66.restaurantapirest.DAO.SourceDonnées
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository


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

    override fun chercherParCode(code: String): List<Trajet>? {
        TODO()
    }


    override fun supprimer(code: String): Boolean {
        TODO("Not yet implemented")
    }
    override fun modifier(code: String, trajet: Trajet): Trajet? {
        TODO("Not yet implemented")
    }

    override fun ajouter(trajet: Trajet): Trajet? {
        TODO("Not yet implemented")
    }



}