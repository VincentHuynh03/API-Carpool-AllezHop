package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Adresse
import com.example.allezhop.Modèles.Trajet
import com.example.allezhop.Modèles.Utilisateur
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.jdbc.core.query



@Repository
class TrajetDAOImplMémoire(val db: JdbcTemplate):  TrajetDAO {

    override fun chercherTous(): List<Trajet> = db.query("select * from trajet join adresse on trajet.destination = adresse.id join utilisateur on trajet.conducteur = utilisateur.code") { response, _ ->
        Trajet(response.getInt("code"),
            Adresse(response.getString("appartement"),response.getString("numéro_municipal"), response.getString("rue"), response.getString("ville"), response.getString("état"), response.getString("code_postal"), response.getString("pays")),
            Adresse(response.getString("appartement"),response.getString("numéro_municipal"), response.getString("rue"), response.getString("ville"), response.getString("état"), response.getString("code_postal"), response.getString("pays")),
            response.getTime("heure_arrivée"),
            response.getTime("heure_départ_max"),
            Utilisateur(response.getString("nom"), response.getString("prénom"), response.getString("courriel"))
        )
    }



    override fun chercherParCode(code: Int): List<Trajet>?  = db.query("select * from trajet join adresse on trajet.destination = adresse.id join utilisateur on trajet.conducteur = utilisateur.code where trajet.code = ?", code) { response, _ ->
        Trajet(response.getInt("code"),
            Adresse(response.getString("appartement"),response.getString("numéro_municipal"), response.getString("rue"), response.getString("ville"), response.getString("état"), response.getString("code_postal"), response.getString("pays")),
            Adresse(response.getString("appartement"),response.getString("numéro_municipal"), response.getString("rue"), response.getString("ville"), response.getString("état"), response.getString("code_postal"), response.getString("pays")),
            response.getTime("heure_arrivée"),
            response.getTime("heure_départ_max"),
            Utilisateur(response.getString("nom"), response.getString("prénom"), response.getString("courriel")))
    }

    override fun chercherParConducteur(code: Int): List<Trajet>? = db.query("select * from trajet join adresse on trajet.destination = adresse.id join utilisateur on trajet.conducteur = utilisateur.code where conducteur = ?", code) { response, _ ->
        Trajet(response.getInt("code"),
            Adresse(response.getString("appartement"),response.getString("numéro_municipal"), response.getString("rue"), response.getString("ville"), response.getString("état"), response.getString("code_postal"), response.getString("pays")),
            Adresse(response.getString("appartement"),response.getString("numéro_municipal"), response.getString("rue"), response.getString("ville"), response.getString("état"), response.getString("code_postal"), response.getString("pays")),
            response.getTime("heure_arrivée"),
            response.getTime("heure_départ_max"),
            Utilisateur(response.getString("nom"), response.getString("prénom"), response.getString("courriel")))
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