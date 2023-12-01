package com.example.allezhop.Services

import com.example.allezhop.DAO.UtilisateurDAO
import com.example.allezhop.Modèles.Reservation
import com.example.allezhop.Modèles.Utilisateur
import org.springframework.stereotype.Service

@Service
class UtilisateurService(val dao: UtilisateurDAO) {

    fun chercherTous(): List<Utilisateur> = dao.chercherTous()

    fun chercherParCode(code: String): List<Utilisateur>? = dao.chercherParCode(code.toInt())

    fun ajouter(utilisateur: Utilisateur) = dao.ajouter(utilisateur)


    fun supprimer(code: String) = dao.supprimer(code)

}