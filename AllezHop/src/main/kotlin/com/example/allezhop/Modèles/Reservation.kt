package com.example.allezhop.Modèles

import java.sql.Time
import java.sql.Timestamp

data class Reservation( var code : Int,
                        var horodatage : Timestamp,
                        var trajet_code: Int,
                        var passager: Utilisateur?) {
    }
