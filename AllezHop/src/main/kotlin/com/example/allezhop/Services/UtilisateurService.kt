package com.example.allezhop.Services

import com.example.allezhop.DAO.UtilisateurDAO
import com.example.allezhop.Modèles.Reservation
import com.example.allezhop.Modèles.Trajet
import com.example.allezhop.Modèles.Utilisateur
import org.springframework.stereotype.Service

@Service
class UtilisateurService(val dao: UtilisateurDAO) {

    fun chercherTous(): List<Utilisateur> = dao.chercherTous()

    fun chercherParCode(code: String): List<Utilisateur>? = dao.chercherParCode(code.toInt())
    fun chercherParNom(nom: String): List<Utilisateur>? = dao.chercherParNom(nom)
    fun chercherParPrénom(prénom: String): List<Utilisateur>? = dao.chercherParPrénom(prénom)
    fun chercherParCourriel(courriel: String): List<Utilisateur>? = dao.chercherParCourriel(courriel)


    fun ajouter(utilisateur: Utilisateur) = dao.ajouter(utilisateur)


    fun supprimer(code: String) = dao.supprimer(code)

}