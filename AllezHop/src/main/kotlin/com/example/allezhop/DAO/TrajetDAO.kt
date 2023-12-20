package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Adresse
import com.example.allezhop.Modèles.Trajet
import java.time.LocalDateTime
import javax.print.attribute.standard.Destination

interface TrajetDAO : DAO<Trajet> {
    override fun chercherTous(): List<Trajet>

    override fun chercherParCode(code: String): Trajet?

    fun chercherParConducteurNom(nom : String): List<Trajet>?

    fun chercherParDate(date: LocalDateTime): List<Trajet>?

    fun chercherParHeure(heure: String): List<Trajet>?

    fun chercherParVille(ville: String): List<Trajet>?

    fun chercherParÉtat(état: String): List<Trajet>?

    fun chercherParPays(pays: String): List<Trajet>?

    override fun supprimer(code: String)


    override fun modifier(code: Int, trajet: Trajet): Trajet?

    override fun ajouter(trajet: Trajet): Trajet?

    fun validerConducteur(code_Trajet: String, code_util: String?): Boolean
}