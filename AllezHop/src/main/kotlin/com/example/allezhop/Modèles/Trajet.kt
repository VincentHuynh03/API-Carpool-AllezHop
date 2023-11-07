package com.example.allezhop.Modèles

import java.sql.Time

data class Trajet(var code : Int,
                  var destination: String,
                  var position_départ: String,
                  var heure_arrivée: Time,
                  var heure_départ_max: Time,
                  var utilisateur_code: Int) {
}