package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Utilisateur
import crosemont.tdi.g66.restaurantapirest.DAO.SourceDonnées
import org.springframework.jdbc.core.JdbcTemplate
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


    override fun chercherParCode(code: String): List<Utilisateur>? = db.query("select * from utilisateur where code = ?", code) { response, _ ->
        Utilisateur(response.getInt("code"),
            response.getString("nom"),
            response.getString("prénom"),
            response.getString("courriel"),
            response.getString("mot_de_passe"))
    }



    override fun supprimer(code: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun modifier(code: String, utilisateur: Utilisateur): Utilisateur? {
        TODO("Not yet implemented")
    }

    override fun ajouter(utilisateur: Utilisateur): Utilisateur? {
        TODO("Not yet implemented")
    }


}