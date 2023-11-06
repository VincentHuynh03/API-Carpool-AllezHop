package com.example.allezhop.Services

import com.example.allezhop.DAO.UtilisateurDAO
import com.example.allezhop.Modèles.Utilisateur
import org.springframework.stereotype.Service

@Service
class UtilisateurService(val dao: UtilisateurDAO) {

    fun chercherTous(): List<Utilisateur> = dao.chercherTous()

    fun chercherParCode(code: Int): Utilisateur? = dao.chercherParCode(code)



}