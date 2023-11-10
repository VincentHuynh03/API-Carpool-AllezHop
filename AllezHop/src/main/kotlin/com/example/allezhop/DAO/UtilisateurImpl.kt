package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Utilisateur
import crosemont.tdi.g66.restaurantapirest.DAO.SourceDonnées
import org.springframework.stereotype.Repository




@Repository
class UtilisateurImpl():  UtilisateurDAO {


    override fun chercherTous(): List<Utilisateur> = SourceDonnées.utilisateurs

    override fun chercherParCode(code: Int): Utilisateur? = SourceDonnées.utilisateurs.find{it.code == code}



    override fun ajouter(utilisateur: Utilisateur): Utilisateur? {
        SourceDonnées.utilisateurs.add(utilisateur);
        return utilisateur;
    }
    override fun modifier(code: Int , utilisateur: Utilisateur): Utilisateur? {
        TODO("Not yet implemented")
    }

    override fun supprimer(utilisateur: Utilisateur): Utilisateur?{
        SourceDonnées.utilisateurs.remove(utilisateur);
        return utilisateur
    }

}