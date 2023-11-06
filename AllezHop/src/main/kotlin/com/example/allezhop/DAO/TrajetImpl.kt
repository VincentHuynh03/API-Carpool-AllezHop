package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Trajet
import crosemont.tdi.g66.restaurantapirest.DAO.SourceDonnées
import org.springframework.stereotype.Repository


@Repository
class TrajetImpl:  TrajetDAO {

    override fun chercherTous(): List<Trajet> = SourceDonnées.trajets

    override fun chercherParCode(code: Int): Trajet? = SourceDonnées.trajets.find{it.code == code}


    override fun supprimer(code: Int): Boolean {
        TODO("Not yet implemented")
    }
    override fun modifier(code: Int, trajet: Trajet): Trajet? {
        TODO("Not yet implemented")
    }

    override fun ajouter(trajet: Trajet): Trajet? {
        TODO("Not yet implemented")
    }



}