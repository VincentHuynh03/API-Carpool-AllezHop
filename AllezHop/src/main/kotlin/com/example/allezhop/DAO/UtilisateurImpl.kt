package com.example.allezhop.DAO

import com.example.allezhop.DAO.UtilisateurDAO
import com.example.allezhop.Modèles.Utilisateur
import crosemont.tdi.g66.restaurantapirest.DAO.SourceDonnées
import org.springframework.stereotype.Repository




@Repository
class UtilisateurImpl():  UtilisateurDAO {


    override fun chercherTous(): List<Utilisateur> = SourceDonnées.utilisateurs

    override fun chercherParCode(code: Int): Utilisateur? = SourceDonnées.utilisateurs.find{it.code == code}


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