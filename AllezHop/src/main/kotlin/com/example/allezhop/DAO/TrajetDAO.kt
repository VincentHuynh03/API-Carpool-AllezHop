package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Trajet
import com.example.allezhop.Modèles.Utilisateur

interface TrajetDAO : DAO<Trajet> {
    override fun chercherTous(): List<Trajet>

    override fun chercherParCode(code: Int): Trajet?

    override fun supprimer(trajet: Trajet): Trajet?

    override fun modifier(code: Int, trajet: Trajet): Trajet?

    override fun ajouter(trajet: Trajet): Trajet?
}