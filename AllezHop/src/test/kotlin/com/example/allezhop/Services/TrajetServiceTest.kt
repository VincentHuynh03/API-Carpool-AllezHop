package com.example.allezhop.Services

import com.example.allezhop.Contrôleurs.SourceDonnéesTests
import com.example.allezhop.DAO.TrajetDAO
import com.example.allezhop.Modèles.Trajet
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.time.LocalDateTime

class TrajetServiceTest {
    private val mockDAO = Mockito.mock(TrajetDAO::class.java)
    private val service = TrajetService(mockDAO)

    @Test
    fun `ajouter un trajet devrait appeler la méthode ajouter du DAO`() {
        val trajet = Trajet(1, SourceDonnéesTests.adresses[1], SourceDonnéesTests.adresses[0], LocalDateTime.of(2023, 12, 1, 9, 0), LocalDateTime.of(2023, 12, 1, 8, 0), SourceDonnéesTests.utilisateurs[0])
        service.ajouter(trajet)
        Mockito.verify(mockDAO).ajouter(trajet)
    }

    @Test
    fun `supprimer un trajet devrait appeler la méthode supprimer du DAO`() {
        val code = "456"
        service.supprimer(code)
        Mockito.verify(mockDAO).supprimer(code)
    }
}