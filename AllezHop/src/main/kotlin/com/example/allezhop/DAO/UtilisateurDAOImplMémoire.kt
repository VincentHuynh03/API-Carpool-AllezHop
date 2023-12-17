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


    override fun ajouter(utilisateur: Utilisateur): Utilisateur? {
        val insertQuery = "insert into utilisateur (nom, prénom, courriel) values (?, ?, ?)"
        db.update(insertQuery,
            utilisateur.nom,
            utilisateur.prénom,
            utilisateur.courriel)

        return utilisateur
    }


    override fun modifier(code: Int, utilisateur: Utilisateur): Utilisateur? {
        val updateQuery = "update utilisateur set nom=?, prénom=?, courriel=? where code=?"
        db.update(updateQuery,
            utilisateur.nom,
            utilisateur.prénom,
            utilisateur.courriel,
            code)
        return utilisateur
    }


    override fun supprimer(utilisateur: String) {
        val deleteQuery = "delete from utilisateur where code = ?"
        db.update(deleteQuery, utilisateur)
    }

}