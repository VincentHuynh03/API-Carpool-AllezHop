package com.example.allezhop.Services

import com.example.allezhop.DAO.TrajetDAO
import com.example.allezhop.DAO.UtilisateurDAO
import com.example.allezhop.Modèles.Adresse
import com.example.allezhop.Modèles.Trajet
import com.example.allezhop.Modèles.Utilisateur
import com.example.allezhop.exceptions.RessourceInexistanteException
import crosemont.tdi.g66.restaurantapirest.Exceptions.DroitAccèsInsuffisantException
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TrajetService(val dao: TrajetDAO, val utilisateur_dao : UtilisateurDAO) {

    fun chercherTous(): List<Trajet> = dao.chercherTous()

    fun chercherParCode(code: String): Trajet? = dao.chercherParCode(code)

    fun chercherParPays(pays: String): List<Trajet>? = dao.chercherParPays(pays)

    fun chercherParConducteurNom(nom: String): List<Trajet>? = dao.chercherParConducteurNom(nom)
    fun chercherParÉtat(état: String): List<Trajet>? = dao.chercherParÉtat(état)

    fun chercherParDate(date: LocalDateTime): List<Trajet>? = dao.chercherParDate(date)

    fun chercherParVille(ville: String): List<Trajet>? = dao.chercherParVille(ville)

    fun ajouter(trajet: Trajet, code_utilisateur : String) : Trajet {
        val utilisateur = utilisateur_dao.chercherParCode( code_utilisateur )
        if (utilisateur == null){
            throw RessourceInexistanteException("L'utilisateur $code_utilisateur n'est pas inscrit au service.")
        }
        if (validerConducteur(utilisateur)){
            dao.ajouter(trajet)
        } else {
            throw DroitAccèsInsuffisantException("Seuls les conducteurs peuvent ajouter un trajet.")
        }
        return trajet
    }

    fun modifier(code: String, trajet: Trajet, code_utilisateur: String) : Trajet? {
        val utilisateur = utilisateur_dao.chercherParCode(code_utilisateur)
        if (utilisateur == null){
            throw RessourceInexistanteException("L'utilisateur $code_utilisateur n'est pas inscrit au service.")
        }
        if (dao.validerConducteurAvecSesTrajets(code,code_utilisateur)){
            return dao.modifier(code,trajet)
        } else {
            throw DroitAccèsInsuffisantException("Seuls les conducteurs peuvent effacer leur propre trajet.")
        }
        return trajet
    }


    fun supprimer(code: String, code_utilisateur: String) {
        val utilisateur = utilisateur_dao.chercherParCode(code_utilisateur)
        if (utilisateur == null){
            throw RessourceInexistanteException("L'utilisateur $code_utilisateur n'est pas inscrit au service.")
        }
        if (dao.validerConducteurAvecSesTrajets(code,code_utilisateur)){
            return dao.supprimer(code)
        } else {
            throw DroitAccèsInsuffisantException("Seuls les conducteurs peuvent effacer leur propre  trajet.")
        }
    }

    fun validerConducteur(utilisateur: Utilisateur): Boolean {
        return (utilisateur.est_conducteur)
    }

}