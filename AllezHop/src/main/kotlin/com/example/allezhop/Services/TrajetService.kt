package com.example.allezhop.Services

import com.example.allezhop.DAO.TrajetDAO
import com.example.allezhop.Modèles.Trajet
import com.example.allezhop.Modèles.Utilisateur
import org.springframework.stereotype.Service

@Service
class TrajetService(val dao: TrajetDAO) {

    fun chercherTous(): List<Trajet> = dao.chercherTous()

    fun chercherParCode(code: String): List<Trajet>? = dao.chercherParCode(code.toInt())

    //fun chercherParConducteurCode(code: String): List<Trajet>? = dao.chercherParConducteurCode(code.toInt())

    fun chercherParConducteurNom(nom: String): List<Trajet>? = dao.chercherParConducteurNom(nom)

    fun chercherParDate(date: String): List<Trajet>? = dao.chercherParDate(date)

    fun chercherParVille(ville: String): List<Trajet>? = dao.chercherParVille(ville)


    fun ajouter(trajet: Trajet) = dao.ajouter(trajet)

    fun modifier(code: String, trajet: Trajet) = dao.modifier(code, trajet)


    fun supprimer(code: String) = dao.supprimer(code)

}