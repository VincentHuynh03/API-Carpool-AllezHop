package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Adresse
import com.example.allezhop.Modèles.Trajet
import javax.print.attribute.standard.Destination

interface TrajetDAO : DAO<Trajet> {
    override fun chercherTous(): List<Trajet>

    override fun chercherParCode(code: Int): List<Trajet>?

    fun chercherParConducteurNom(nom : String): List<Trajet>?

    fun chercherParDate(date: String): List<Trajet>?

    fun chercherParHeure(heure: String): List<Trajet>?

    fun chercherParVille(ville: String): List<Trajet>?

    fun chercherParÉtat(état: String): List<Trajet>?

    fun chercherParPays(pays: String): List<Trajet>?

    override fun supprimer(code: String)


    override fun modifier(code: String, trajet: Trajet): Trajet?

    override fun ajouter(trajet: Trajet): Trajet?
}