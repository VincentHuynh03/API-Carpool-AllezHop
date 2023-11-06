package com.example.allezhop.Services

import com.example.allezhop.DAO.NotificationDAO
import com.example.allezhop.DAO.ReservationDAO
import com.example.allezhop.Modèles.Notification
import org.springframework.stereotype.Service

@Service
class NotificationService(val dao: NotificationDAO) {


    fun chercherTous(): List<Notification> = dao.chercherTous()


}