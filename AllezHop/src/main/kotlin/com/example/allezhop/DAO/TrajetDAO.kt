package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Trajet

interface TrajetDAO : DAO<Trajet> {
    override fun chercherTous(): List<Trajet>

    override fun chercherParCode(code: Int): Trajet?

    override fun supprimer(code: Int): Boolean

    override fun modifier(code: Int, trajet: Trajet): Trajet?

    override fun ajouter(trajet: Trajet): Trajet?
}