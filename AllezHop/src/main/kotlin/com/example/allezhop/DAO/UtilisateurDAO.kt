package com.example.allezhop.DAO

import com.example.allezhop.Modèles.Utilisateur

interface UtilisateurDAO : DAO<Utilisateur> {

    override fun chercherTous(): List<Utilisateur>

    override fun chercherParCode(code: Int): Utilisateur?

    override fun supprimer(code: Int): Boolean

    override fun modifier(code: Int, utilisateur: Utilisateur): Utilisateur?

    override fun ajouter(utilisateur: Utilisateur): Utilisateur?
}