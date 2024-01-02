package com.example.allezhop.Services

import com.example.allezhop.DAO.UtilisateurDAO
import com.example.allezhop.Modèles.Utilisateur
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class UtilisateurServiceTest {
    private val mockDAO = Mockito.mock(UtilisateurDAO::class.java)
    private val service = UtilisateurService(mockDAO)

    @Test
    fun `ajouter un utilisateur devrait appeler la méthode ajouter du DAO`() {
        val utilisateur = Utilisateur(789, "Nom", "Prénom", "email@example.com")
        service.ajouter(utilisateur)
        Mockito.verify(mockDAO).ajouter(utilisateur)
    }

    @Test
    fun `supprimer un utilisateur devrait appeler la méthode supprimer du DAO`() {
        val code = "789"
        service.supprimer(code)
        Mockito.verify(mockDAO).supprimer(code)
    }
}