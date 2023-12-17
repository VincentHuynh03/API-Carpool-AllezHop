package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Reservation
import com.example.allezhop.Modèles.Utilisateur
import crosemont.tdi.g66.restaurantapirest.DAO.SourceDonnées
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import org.springframework.jdbc.core.query

@Repository
class UtilisateurDAOImplMémoire(val db: JdbcTemplate):  UtilisateurDAO {

    override fun chercherTous(): List<Utilisateur> = db.query("select * from utilisateur") { response, _ ->
        Utilisateur(
            response.getInt("code"),
            response.getString("nom"),
            response.getString("prénom"),
            response.getString("courriel"),
            )
    }


    override fun chercherParCode(code: Int): List<Utilisateur>? {
        val sql = "SELECT * FROM utilisateur WHERE code = ?"
        val results = db.query(sql, code) { response, _ ->
            Utilisateur(
                response.getInt("code"),
                response.getString("nom"),
                response.getString("prénom"),
                response.getString("courriel")
            )
        }

        return results
    }
    override fun chercherParNom(nom: String): List<Utilisateur>? {
        val sql = "SELECT * FROM utilisateur WHERE nom = ?"
        val results = db.query(sql, nom) { response, _ ->
            Utilisateur(
                response.getInt("code"),
                response.getString("nom"),
                response.getString("prénom"),
                response.getString("courriel")
            )
        }

        return results
    }

    override fun chercherParPrénom(prénom: String): List<Utilisateur>? {
        val sql = "SELECT * FROM utilisateur WHERE prénom = ?"
        val results = db.query(sql, prénom) { response, _ ->
            Utilisateur(
                response.getInt("code"),
                response.getString("nom"),
                response.getString("prénom"),
                response.getString("courriel")
            )
        }
        return results
    }

    override fun chercherParCourriel(courriel: String): List<Utilisateur>? {
        val sql = "SELECT * FROM utilisateur WHERE courriel = ?"
        val results = db.query(sql, courriel) { response, _ ->
            Utilisateur(
                response.getInt("code"),
                response.getString("nom"),
                response.getString("prénom"),
                response.getString("courriel")
            )
        }
        return results
    }

    override fun modifier(code: Int, utilisateur: Utilisateur): Utilisateur? {
        val sql =
            "UPDATE utilisateur SET nom = ?, prénom = ?, courriel = ? WHERE code = ?"

        val modifier = db.update(
            sql,
            utilisateur.nom,
            utilisateur.prénom,
            utilisateur.courriel,
            code
        )
        return if (modifier > 0) utilisateur else null
    }


    override fun ajouter(utilisateur: Utilisateur): Utilisateur? {
        val sql = "INSERT INTO utilisateur (nom, prénom, courriel) values (?, ?, ?)"
        db.update(sql,
            utilisateur.nom,
            utilisateur.prénom,
            utilisateur.courriel)

        return utilisateur
    }





    override fun supprimer(code: String) {
        val sql = "DELETE FROM utilisateur WHERE code = ?"
        db.update(sql, code)
    }

}