package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Utilisateur
import crosemont.tdi.g66.restaurantapirest.DAO.SourceDonnées
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import org.springframework.jdbc.core.query

@Repository
class UtilisateurDAOImplMémoire(val db: JdbcTemplate):  UtilisateurDAO {

    override fun chercherTous(): List<Utilisateur> = db.query("select * from utilisateur") { response, _ ->
        Utilisateur(response.getInt("code"),
            response.getString("nom"),
            response.getString("prénom"),
            response.getString("courriel"),
            response.getString("mot_de_passe"))
    }


    override fun chercherParCode(code: Int): List<Utilisateur>? {
        val sql = "SELECT * FROM utilisateur WHERE code = ?"
        val results = db.query(
            sql,
            arrayOf(code)
        ) { response, _ ->
            Utilisateur(
                code = response.getInt("code"),
                nom = response.getString("nom"),
                prénom = response.getString("prénom"),
                courriel = response.getString("courriel"),
                mot_de_passe = response.getString("mot_de_passe")
            )
        }

        return results
    }

    override fun ajouter(utilisateur: Utilisateur): Utilisateur? {
        val insertQuery = "insert into utilisateur (code, nom, prénom, courriel, mot_de_passe) values (?, ?, ?, ?, ?)"
        db.update(insertQuery,
            utilisateur.code,
            utilisateur.nom,
            utilisateur.prénom,
            utilisateur.courriel,
            utilisateur.mot_de_passe)
        return utilisateur
    }

    override fun modifier(code: String, utilisateur: Utilisateur): Utilisateur? {
        val updateQuery = "update utilisateur set nom=?, prénom=?, courriel=?, mot_de_passe=? where code=?"
        db.update(updateQuery,
            utilisateur.nom,
            utilisateur.prénom,
            utilisateur.courriel,
            utilisateur.mot_de_passe,
            code)
        return utilisateur
    }


    override fun supprimer(utilisateur: Utilisateur): Utilisateur? {
        val deleteQuery = "delete from utilisateur where code = ?"
        db.update(deleteQuery, utilisateur.code)
        return utilisateur
    }

}