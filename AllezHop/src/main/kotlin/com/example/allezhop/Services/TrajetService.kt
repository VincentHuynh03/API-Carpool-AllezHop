package com.example.allezhop.Services

import com.example.allezhop.DAO.TrajetDAO
import com.example.allezhop.Modèles.Trajet
import com.example.allezhop.Modèles.Utilisateur
import org.springframework.stereotype.Service

@Service
class TrajetService(val dao: TrajetDAO) {

    fun chercherTous(): List<Trajet> = dao.chercherTous()

    fun chercherParCode(code: String): List<Trajet>? = dao.chercherParCode(code.toInt())

    fun chercherParConducteur(code: String): List<Trajet>? = dao.chercherParConducteur(code.toInt())


    fun ajouter(trajet: Trajet) = dao.ajouter(trajet)


    fun supprimer(trajet: Trajet) = dao.supprimer(trajet)

}