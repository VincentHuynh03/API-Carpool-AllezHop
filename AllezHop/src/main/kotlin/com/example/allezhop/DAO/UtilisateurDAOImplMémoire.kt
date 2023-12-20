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
            response.getString("utilisateur_code"),
            response.getString("nom"),
            response.getString("prénom"),
            response.getString("courriel"),
            response.getBoolean("est_conducteur"),
            response.getBoolean("est_passager")
            )
    }


    override fun chercherParCode(code: String): Utilisateur? {
        val sql = "SELECT * FROM utilisateur WHERE utilisateur_code = ?"
        val results = db.query(sql, code) { response, _ ->
            Utilisateur(
                response.getString("utilisateur_code"),
                response.getString("nom"),
                response.getString("prénom"),
                response.getString("courriel"),
                response.getBoolean("est_conducteur"),
                response.getBoolean("est_passager")
            )
        }

        return if (results.isEmpty()) null else results.first()
    }

    override fun chercherParNom(nom: String): List<Utilisateur>? {
        val sql = "SELECT * FROM utilisateur WHERE nom = ?"
        val results = db.query(sql, nom) { response, _ ->
            Utilisateur(
                response.getString("utilisateur_code"),
                response.getString("nom"),
                response.getString("prénom"),
                response.getString("courriel"),
                response.getBoolean("est_conducteur"),
                response.getBoolean("est_passager")
            )
        }

        return if (results.isEmpty()) null else results
    }

    override fun chercherParPrénom(prénom: String): List<Utilisateur>? {
        val sql = "SELECT * FROM utilisateur WHERE prénom = ?"
        val results = db.query(sql, prénom) { response, _ ->
            Utilisateur(
                response.getString("utilisateur_code"),
                response.getString("nom"),
                response.getString("prénom"),
                response.getString("courriel"),
                response.getBoolean("est_conducteur"),
                response.getBoolean("est_passager")
            )
        }
        return if (results.isEmpty()) null else results
    }

    override fun chercherParCourriel(courriel: String): Utilisateur? {
        val sql = "SELECT * FROM utilisateur WHERE courriel = ?"
        val results = db.query(sql, courriel) { response, _ ->
            Utilisateur(
                response.getString("utilisateur_code"),
                response.getString("nom"),
                response.getString("prénom"),
                response.getString("courriel"),
                response.getBoolean("est_conducteur"),
                response.getBoolean("est_passager")
            )
        }
        return if (results.isEmpty()) null else results.first()
    }

    override fun modifier(code: Int, utilisateur: Utilisateur): Utilisateur? {
        val sql =
            "UPDATE utilisateur SET nom = ?, prénom = ?, courriel = ? , est_conducteur = ?, est_passager = ? WHERE utilisateur_code = ?"

        val modifier = db.update(
            sql,
            utilisateur.nom,
            utilisateur.prénom,
            utilisateur.courriel,
            utilisateur.est_conducteur,
            utilisateur.est_passager,
            code
        )
        return if (modifier > 0) utilisateur else null
    }


    override fun ajouter(utilisateur: Utilisateur): Utilisateur? {
        val sql = "INSERT INTO utilisateur (nom, prénom, courriel, est_conducteur, est_passager) values (?, ?, ?, ?, ?)"
        db.update(sql,
            utilisateur.nom,
            utilisateur.prénom,
            utilisateur.courriel,
            utilisateur.est_conducteur,
            utilisateur.est_passager)

        return utilisateur
    }





    override fun supprimer(code: String) {
        val sql = "DELETE FROM utilisateur WHERE utilisateur_code = ?"
        db.update(sql, code)
    }

}