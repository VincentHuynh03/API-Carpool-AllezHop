package crosemont.tdi.g66.restaurantapirest.DAO

import com.example.allezhop.Modèles.Trajet
import com.example.allezhop.Modèles.Utilisateur

import org.springframework.stereotype.Component

@Component
class SourceDonnées {
    companion object {
        val utilisateurs = mutableListOf(
                Utilisateur(1),
                Utilisateur(2) ,
                Utilisateur(3),
        )
        val trajets = mutableListOf(
            Trajet(1),
            Trajet(2) ,
            Trajet(3),
        )
    }
}