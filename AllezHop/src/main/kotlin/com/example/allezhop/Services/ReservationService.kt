package com.example.allezhop.Services

import com.example.allezhop.DAO.ReservationDAO
import com.example.allezhop.DAO.UtilisateurDAO
import com.example.allezhop.Modèles.Reservation
import com.example.allezhop.Modèles.Trajet
import com.example.allezhop.Modèles.Utilisateur
import com.example.allezhop.exceptions.RessourceInexistanteException
import crosemont.tdi.g66.restaurantapirest.Exceptions.DroitAccèsInsuffisantException
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ReservationService(val dao: ReservationDAO, val utilisateur_dao : UtilisateurDAO) {

    fun chercherTous(): List<Reservation> = dao.chercherTous()
    fun chercherParCode(code: String): Reservation? = dao.chercherParCode(code)


    fun chercherParPassagerNom(nom: String): List<Reservation>? = dao.chercherParPassagerNom(nom)


    fun ajouter(reservation: Reservation, code_utilisateur: String) : Reservation? {
        val utilisateur = utilisateur_dao.chercherParCode(code_utilisateur)
        if (utilisateur == null){
            throw RessourceInexistanteException("L'utilisateur n'est pas inscrit au service.")
        }
        if (validePassager(utilisateur)){
            return dao.ajouter(reservation)
        } else {
            throw DroitAccèsInsuffisantException("Seuls les passagers peuvent ajouter un réservation.")
        }
        return reservation
    }

    fun modifier(code: String, reservation: Reservation,code_utilisateur: String) : Reservation? {
        val utilisateur = utilisateur_dao.chercherParCode(code_utilisateur)
        if (utilisateur == null){
            throw RessourceInexistanteException("L'utilisateur n'est pas inscrit au service.")
        }
        if (dao.validerPassagerAvecSesReservations(code,code_utilisateur)){
            return dao.modifier(code,reservation)
        } else {
            throw DroitAccèsInsuffisantException("Seuls les passagers peuvent modifier leur propre réservations.")
        }
        return reservation
    }

    fun supprimer(code: String, code_utilisateur: String) {
        val utilisateur = utilisateur_dao.chercherParCode(code_utilisateur)
        if (utilisateur == null){
            throw RessourceInexistanteException("L'utilisateur n'est pas inscrit au service.")
        }
        if (dao.validerPassagerAvecSesReservations(code,code_utilisateur)){
            return dao.supprimer(code)
        } else {
            throw DroitAccèsInsuffisantException("Seuls les passagers peuvent effacer leur propre  trajet.")
        }
    }

    fun validePassager(utilisateur: Utilisateur): Boolean {
        return (utilisateur.est_passager)
    }
}