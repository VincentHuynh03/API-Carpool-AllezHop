package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Adresse
import com.example.allezhop.Modèles.Trajet
import com.example.allezhop.Modèles.Utilisateur
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.jdbc.core.query
import java.time.LocalDateTime
import javax.print.attribute.standard.Destination


@Repository
class TrajetDAOImplMémoire(val db: JdbcTemplate):  TrajetDAO {

    override fun chercherTous(): List<Trajet> = db.query("select * from trajet join adresse as adresse_dest on trajet.destination = adresse_dest.id join adresse as adresse_depart on trajet.position_départ = adresse_depart.id join utilisateur on trajet.conducteur = utilisateur.code") { response, _ ->
        Trajet(response.getInt("code"),
            Adresse(response.getInt("destination"), response.getString("adresse_dest.appartement"),response.getString("adresse_dest.numéro_municipal"), response.getString("adresse_dest.rue"), response.getString("adresse_dest.ville"), response.getString("adresse_dest.état"), response.getString("adresse_dest.code_postal"), response.getString("adresse_dest.pays")),
            Adresse(response.getInt("position_départ"), response.getString("adresse_depart.appartement"),response.getString("adresse_depart.numéro_municipal"), response.getString("adresse_depart.rue"), response.getString("adresse_depart.ville"), response.getString("adresse_depart.état"), response.getString("adresse_depart.code_postal"), response.getString("adresse_depart.pays")),
            response.getTimestamp("heure_arrivée").toLocalDateTime(),
            response.getTimestamp("heure_départ_max").toLocalDateTime(),
            Utilisateur(response.getInt("conducteur"), response.getString("nom"), response.getString("prénom"), response.getString("courriel"))
        )
    }



    override fun chercherParCode(code: Int): List<Trajet>?  {
        val sql = "SELECT * FROM trajet JOIN adresse AS adresse_dest ON trajet.destination = adresse_dest.id JOIN adresse AS adresse_depart ON trajet.position_départ = adresse_depart.id JOIN utilisateur ON trajet.conducteur = utilisateur.code WHERE trajet.code = ?"
        val results = db.query(sql, code) { response, _ ->
            Trajet(response.getInt("code"),
                Adresse(response.getInt("destination"), response.getString("adresse_dest.appartement"),response.getString("adresse_dest.numéro_municipal"), response.getString("adresse_dest.rue"), response.getString("adresse_dest.ville"), response.getString("adresse_dest.état"), response.getString("adresse_dest.code_postal"), response.getString("adresse_dest.pays")),
                Adresse(response.getInt("position_départ"), response.getString("adresse_depart.appartement"),response.getString("adresse_depart.numéro_municipal"), response.getString("adresse_depart.rue"), response.getString("adresse_depart.ville"), response.getString("adresse_depart.état"), response.getString("adresse_depart.code_postal"), response.getString("adresse_depart.pays")),
                response.getTimestamp("heure_arrivée").toLocalDateTime(),
                response.getTimestamp("heure_départ_max").toLocalDateTime(),
                Utilisateur(response.getInt("code"), response.getString("nom"), response.getString("prénom"), response.getString("courriel")))
        }

        return if (results.isEmpty()) null else results
    }

    override fun chercherParConducteurNom(nom: String): List<Trajet>? {
        val sql = "SELECT * FROM trajet JOIN adresse as adresse_dest ON trajet.destination = adresse_dest.id JOIN adresse as adresse_depart ON trajet.position_départ = adresse_depart.id JOIN utilisateur ON trajet.conducteur = utilisateur.code WHERE utilisateur.nom LIKE ?"
        val results = db.query(sql, nom) { response, _ ->
            Trajet(response.getInt("code"),
                Adresse(response.getInt("destination"), response.getString("adresse_dest.appartement"),response.getString("adresse_dest.numéro_municipal"), response.getString("adresse_dest.rue"), response.getString("adresse_dest.ville"), response.getString("adresse_dest.état"), response.getString("adresse_dest.code_postal"), response.getString("adresse_dest.pays")),
                Adresse(response.getInt("position_départ"), response.getString("adresse_depart.appartement"),response.getString("adresse_depart.numéro_municipal"), response.getString("adresse_depart.rue"), response.getString("adresse_depart.ville"), response.getString("adresse_depart.état"), response.getString("adresse_depart.code_postal"), response.getString("adresse_depart.pays")),
                response.getTimestamp("heure_arrivée").toLocalDateTime(),
                response.getTimestamp("heure_départ_max").toLocalDateTime(),
                Utilisateur(response.getInt("code"), response.getString("nom"), response.getString("prénom"), response.getString("courriel")))
        }
        return if (results.isEmpty()) null else results
    }

    override fun chercherParDate(date: LocalDateTime): List<Trajet>? {
        val sql = "SELECT * FROM trajet JOIN adresse as adresse_dest ON trajet.destination = adresse_dest.id JOIN adresse as adresse_depart ON trajet.position_départ = adresse_depart.id JOIN utilisateur ON trajet.conducteur = utilisateur.code WHERE heure_arrivée LIKE ? OR heure_départ_max LIKE ?"
        val results = db.query(sql, date, date) { response, _ ->
            Trajet(response.getInt("code"),
                Adresse(response.getInt("destination"), response.getString("adresse_dest.appartement"),response.getString("adresse_dest.numéro_municipal"), response.getString("adresse_dest.rue"), response.getString("adresse_dest.ville"), response.getString("adresse_dest.état"), response.getString("adresse_dest.code_postal"), response.getString("adresse_dest.pays")),
                Adresse(response.getInt("position_départ"), response.getString("adresse_depart.appartement"),response.getString("adresse_depart.numéro_municipal"), response.getString("adresse_depart.rue"), response.getString("adresse_depart.ville"), response.getString("adresse_depart.état"), response.getString("adresse_depart.code_postal"), response.getString("adresse_depart.pays")),
                response.getTimestamp("heure_arrivée").toLocalDateTime(),
                response.getTimestamp("heure_départ_max").toLocalDateTime(),
                Utilisateur(response.getInt("code"), response.getString("nom"), response.getString("prénom"), response.getString("courriel")))
        }
        return if (results.isEmpty()) null else results
    }

    override fun chercherParHeure(heure: String): List<Trajet>? {
        val sql = "SELECT * FROM trajet JOIN adresse as adresse_dest ON trajet.destination = adresse_dest.id JOIN adresse as adresse_depart ON trajet.position_départ = adresse_depart.id JOIN utilisateur ON trajet.conducteur = utilisateur.code WHERE heure_arrivée LIKE ? OR heure_départ_max LIKE ?"
        val results = db.query(sql, heure, heure) { response, _ ->
            Trajet(response.getInt("code"),
                Adresse(response.getInt("destination"), response.getString("adresse_dest.appartement"),response.getString("adresse_dest.numéro_municipal"), response.getString("adresse_dest.rue"), response.getString("adresse_dest.ville"), response.getString("adresse_dest.état"), response.getString("adresse_dest.code_postal"), response.getString("adresse_dest.pays")),
                Adresse(response.getInt("position_départ"), response.getString("adresse_depart.appartement"),response.getString("adresse_depart.numéro_municipal"), response.getString("adresse_depart.rue"), response.getString("adresse_depart.ville"), response.getString("adresse_depart.état"), response.getString("adresse_depart.code_postal"), response.getString("adresse_depart.pays")),
                response.getTimestamp("heure_arrivée").toLocalDateTime(),
                response.getTimestamp("heure_départ_max").toLocalDateTime(),
                Utilisateur(response.getInt("code"), response.getString("nom"), response.getString("prénom"), response.getString("courriel")))
        }
        return if (results.isEmpty()) null else results
    }

    override fun chercherParVille(ville: String): List<Trajet>?{
        val sql = "SELECT * FROM trajet JOIN adresse as adresse_dest ON trajet.destination = adresse_dest.id JOIN adresse as adresse_depart ON trajet.position_départ = adresse_depart.id JOIN utilisateur ON trajet.conducteur = utilisateur.code WHERE adresse_depart.ville LIKE ? OR adresse_dest.ville LIKE ?"
        val results = db.query(sql, ville, ville) { response, _ ->
            Trajet(response.getInt("code"),
                Adresse(response.getInt("destination"), response.getString("adresse_dest.appartement"),response.getString("adresse_dest.numéro_municipal"), response.getString("adresse_dest.rue"), response.getString("adresse_dest.ville"), response.getString("adresse_dest.état"), response.getString("adresse_dest.code_postal"), response.getString("adresse_dest.pays")),
                Adresse(response.getInt("position_départ"), response.getString("adresse_depart.appartement"),response.getString("adresse_depart.numéro_municipal"), response.getString("adresse_depart.rue"), response.getString("adresse_depart.ville"), response.getString("adresse_depart.état"), response.getString("adresse_depart.code_postal"), response.getString("adresse_depart.pays")),
                response.getTimestamp("heure_arrivée").toLocalDateTime(),
                response.getTimestamp("heure_départ_max").toLocalDateTime(),
                Utilisateur(response.getInt("code"), response.getString("nom"), response.getString("prénom"), response.getString("courriel")))
        }
        return if (results.isEmpty()) null else results
    }

    override fun chercherParÉtat(état: String): List<Trajet>? {
        val sql = "SELECT * FROM trajet JOIN adresse as adresse_dest ON trajet.destination = adresse_dest.id JOIN adresse as adresse_depart ON trajet.position_départ = adresse_depart.id JOIN utilisateur ON trajet.conducteur = utilisateur.code WHERE adresse_depart.état LIKE ? OR adresse_dest.état LIKE ?"
        val results = db.query(sql, état, état) { response, _ ->
            Trajet(response.getInt("code"),
                Adresse(response.getInt("destination"), response.getString("adresse_dest.appartement"),response.getString("adresse_dest.numéro_municipal"), response.getString("adresse_dest.rue"), response.getString("adresse_dest.ville"), response.getString("adresse_dest.état"), response.getString("adresse_dest.code_postal"), response.getString("adresse_dest.pays")),
                Adresse(response.getInt("position_départ"), response.getString("adresse_depart.appartement"),response.getString("adresse_depart.numéro_municipal"), response.getString("adresse_depart.rue"), response.getString("adresse_depart.ville"), response.getString("adresse_depart.état"), response.getString("adresse_depart.code_postal"), response.getString("adresse_depart.pays")),
                response.getTimestamp("heure_arrivée").toLocalDateTime(),
                response.getTimestamp("heure_départ_max").toLocalDateTime(),
                Utilisateur(response.getInt("code"), response.getString("nom"), response.getString("prénom"), response.getString("courriel")))
        }
        return if (results.isEmpty()) null else results
    }

    override fun chercherParPays(pays: String): List<Trajet>? {
        val sql = "SELECT * FROM trajet JOIN adresse as adresse_dest ON trajet.destination = adresse_dest.id JOIN adresse as adresse_depart ON trajet.position_départ = adresse_depart.id JOIN utilisateur ON trajet.conducteur = utilisateur.code WHERE adresse_depart.pays LIKE ? OR adresse_dest.pays LIKE ?"
        val results = db.query(sql, pays, pays) { response, _ ->
            Trajet(response.getInt("code"),
                Adresse(response.getInt("destination"), response.getString("adresse_dest.appartement"),response.getString("adresse_dest.numéro_municipal"), response.getString("adresse_dest.rue"), response.getString("adresse_dest.ville"), response.getString("adresse_dest.état"), response.getString("adresse_dest.code_postal"), response.getString("adresse_dest.pays")),
                Adresse(response.getInt("position_départ"), response.getString("adresse_depart.appartement"),response.getString("adresse_depart.numéro_municipal"), response.getString("adresse_depart.rue"), response.getString("adresse_depart.ville"), response.getString("adresse_depart.état"), response.getString("adresse_depart.code_postal"), response.getString("adresse_depart.pays")),
                response.getTimestamp("heure_arrivée").toLocalDateTime(),
                response.getTimestamp("heure_départ_max").toLocalDateTime(),
                Utilisateur(response.getInt("code"), response.getString("nom"), response.getString("prénom"), response.getString("courriel")))
        }
        return if (results.isEmpty()) null else results
    }


    override fun ajouter(trajet: Trajet): Trajet? {
        val insertQuery = "insert into trajet (destination, position_départ, heure_arrivée, heure_départ_max, conducteur) values (?, ?, ?, ?, ?)"
        db.update(insertQuery,
            trajet.destination.code,
            trajet.position_départ.code,
            trajet.heure_arrivée,
            trajet.heure_départ_max,
            trajet.conducteur.code)
        return trajet
    }

    override fun modifier(code: Int, trajet: Trajet): Trajet? {
        val updateQuery = "update trajet set destination=?, position_départ=?, heure_arrivée=?, heure_départ_max=?, conducteur=? where code=?"
        db.update(updateQuery,
            trajet.destination.code,
            trajet.position_départ.code,
            trajet.heure_arrivée,
            trajet.heure_départ_max,
            trajet.conducteur.code,
            code)
        return trajet
    }
    override fun supprimer(code: String) {
        val sql = "DELETE FROM trajet WHERE code = ?"
        db.update(sql, code)
    }

}