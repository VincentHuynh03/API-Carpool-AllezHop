package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Utilisateur

interface UtilisateurDAO : DAO<Utilisateur> {

    override fun chercherTous(): List<Utilisateur>

    override fun chercherParCode(code: String): Utilisateur?

    fun chercherParNom(nom: String): List<Utilisateur>?
    fun chercherParPrénom(prénom: String): List<Utilisateur>?
    fun chercherParCourriel(courriel: String): Utilisateur?


     fun modifier(code: Int, utilisateur: Utilisateur): Utilisateur?

    override fun ajouter(utilisateur: Utilisateur): Utilisateur?

    override fun supprimer(code: String)

}