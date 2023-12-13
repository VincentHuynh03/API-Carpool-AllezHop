package com.example.allezhop.Modèles

import java.sql.Time
import java.time.LocalDateTime

data class Trajet(var code : Int,
                  var destination: Adresse,
                  var position_départ: Adresse,
                    //var arrêts: List<Adresse>,
                  var heure_arrivée: LocalDateTime,
                  var heure_départ_max: LocalDateTime,
                  var conducteur: Utilisateur) {
}