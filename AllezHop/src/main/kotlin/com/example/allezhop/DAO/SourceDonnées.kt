package crosemont.tdi.g66.restaurantapirest.DAO

import com.example.allezhop.Modèles.Notification
import com.example.allezhop.Modèles.Reservation
import com.example.allezhop.Modèles.Trajet
import com.example.allezhop.Modèles.Utilisateur

import org.springframework.stereotype.Component

@Component
class SourceDonnées {
    companion object {
        val utilisateurs = mutableListOf(
                Utilisateur(1 , "A1"),
                Utilisateur(2 , "A2") ,
                Utilisateur(3, "A3"),
        )
        val trajets = mutableListOf(
            Trajet(1),
            Trajet(2) ,
            Trajet(3),
        )
        val reservations = mutableListOf(
            Reservation(1),
            Reservation(2) ,
            Reservation(3),
        )
        val notifications = mutableListOf(
            Notification(1),
            Notification(2) ,
            Notification(3),
        )
    }
}