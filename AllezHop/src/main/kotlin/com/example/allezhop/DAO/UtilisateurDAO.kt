package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Utilisateur

interface UtilisateurDAO : DAO<Utilisateur> {

    override fun chercherTous(): List<Utilisateur>

    override fun chercherParCode(code: Int): Utilisateur?



    override fun ajouter(utilisateur: Utilisateur): Utilisateur?
    override fun modifier(code: Int, utilisateur: Utilisateur): Utilisateur?

    override fun supprimer(utilisateur: Utilisateur): Utilisateur?

}