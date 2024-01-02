package com.example.allezhop.Services

import com.example.allezhop.DAO.ReservationDAO
import com.example.allezhop.Modèles.Reservation
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.sql.Timestamp
import java.time.LocalDateTime

class RéservationServiceTest {
    private val mockDAO = Mockito.mock(ReservationDAO::class.java)
    private val service = ReservationService(mockDAO)

    @Test
    fun `ajouter une réservation devrait appeler la méthode ajouter du DAO`() {
        val reservation = Reservation(123, Timestamp.valueOf(LocalDateTime.now()), 456, 789)
        service.ajouter(reservation)
        Mockito.verify(mockDAO).ajouter(reservation)
    }

    @Test
    fun `supprimer une réservation devrait appeler la méthode supprimer du DAO`() {
        val code = "123"
        service.supprimer(code)
        Mockito.verify(mockDAO).supprimer(code)
    }
}