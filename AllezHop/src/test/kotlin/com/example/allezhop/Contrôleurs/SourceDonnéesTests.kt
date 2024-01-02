package com.example.allezhop.Contrôleurs

import com.example.allezhop.Modèles.Adresse
import com.example.allezhop.Modèles.Reservation
import com.example.allezhop.Modèles.Trajet
import com.example.allezhop.Modèles.Utilisateur
import org.springframework.stereotype.Component
import java.sql.Timestamp
import java.time.LocalDateTime

@Component
class SourceDonnéesTests {
    companion object {
        // Génération des données de test pour les adresses
        val adresses = listOf(
            Adresse(1, null, "1234", "Rue Principale", "Montréal", "QC", "H1H 1H1", "Canada"),
            Adresse(2, "4B", "5678", "Rue Secondaire", "Québec", "QC", "G1G 2G2", "Canada"),
            Adresse(3, null, "9012", "Boulevard Tertiaire", "Laval", "QC", "J1J 3J3", "Canada")
        )

        // Génération des données de test pour les utilisateurs
        val utilisateurs = listOf(
            Utilisateur(1, "Dupont", "Jean", "jean.dupont@example.com"),
            Utilisateur(2, "Tremblay", "Marie", "marie.tremblay@example.com"),
            Utilisateur(3, "Larouche", "Luc", "luc.larouche@example.com")
        )

        // Génération des données de test pour les trajets
        val trajets = listOf(
            Trajet(1, adresses[1], adresses[0], LocalDateTime.of(2023, 12, 1, 9, 0), LocalDateTime.of(2023, 12, 1, 8, 0), utilisateurs[0]),
            Trajet(2, adresses[2], adresses[1], LocalDateTime.of(2023, 12, 2, 10, 0), LocalDateTime.of(2023, 12, 2, 9, 0), utilisateurs[1])
        )

        // Génération des données de test pour les réservations
        val reservations = listOf(
            Reservation(1, Timestamp.valueOf(LocalDateTime.now()), trajets[0].code, utilisateurs[1].code),
            Reservation(2, Timestamp.valueOf(LocalDateTime.now()), trajets[1].code, utilisateurs[2].code)
        )
    }
}