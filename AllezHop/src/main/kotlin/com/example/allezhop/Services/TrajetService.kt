package com.example.allezhop.Services

import com.example.allezhop.DAO.TrajetDAO
import com.example.allezhop.Modèles.Trajet
import org.springframework.stereotype.Service

@Service
class TrajetService(val dao: TrajetDAO) {

    fun chercherTous(): List<Trajet> = dao.chercherTous()

}