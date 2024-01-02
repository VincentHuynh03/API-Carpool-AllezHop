package com.example.allezhop.Controleurs

import com.example.allezhop.DAO.IntrouvableException
import com.example.allezhop.Modèles.Reservation
import com.example.allezhop.Services.ReservationService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue


import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.web.bind.annotation.PostMapping
import java.sql.Timestamp

@SpringBootTest
@AutoConfigureMockMvc
class RéservationControleurTest {

    @MockBean
    lateinit var service: ReservationService

    @Autowired
    private lateinit var mockMvc: MockMvc

    //il faut modifier la bd reservation pour qu'elle les meme tables que data class reservation
    /*CREATE TABLE reservation (
    code INT PRIMARY KEY,
    horodatage TIMESTAMP,
    trajet_code INT,
    passager INT
);
*/
    @Test
    //GetMapping("/reservations/{code}")
    fun `Étant donné la réservation dont le code est 1 lorsqu'on effectue une requête GET de recherche par code alors on obtient un JSON qui contient une réservation dont le code est 1 et un code de retour 200`() {
        val reservation = Reservation(1, Timestamp(System.currentTimeMillis()), 1, 2)

        Mockito.`when`(service.chercherParCode(1)).thenReturn(listOf(reservation))

        mockMvc.perform(MockMvcRequestBuilders.get("/reservations/1"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value(1))

    }

    @Test
    //GetMapping("/reservations/{code}")
    fun `Étant donné la réservation dont le code est 0 et qui n'est pas inscrit au service, lorsqu'on effectue une requête GET de recherche par code, alors on obtient un code de retour 404 et le message "la réservation 0 n'est pas inscrit au service"`() {
        val exceptionParam = "not_found"

        Mockito.`when`(service.chercherParCode(0)).thenReturn(null)

        mockMvc.perform(get("/reservations/0", exceptionParam)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is IntrouvableException)
                assertEquals("La reservation 0 n'est pas inscrit au service.", résultat.resolvedException?.message)
            }
    }
/*
    @Test
    //PostMapping("/réservations")
    fun `Étant donnée la réservation dont le code est 1 qui existe déjà lorsqu'on effectue une requête POST pour l'ajouter alors on obtient un code de retour 409`() {

    }

    @Test
    //PostMapping("/réservations")
    fun `Étant donnée la réservation dont le code est 78 et qui n'est pas inscrit au service lorsqu'on effectue une requête POST pour l'ajouter, alors on obtient un code de retour 200` () {

    }

    @Test
    //@PutMapping("/réservations/edit/{code}")
    fun `Étant donnée la réservation dont le code est 2 et qui est inscrit au service et dont le code d'utilisateur est 1 lorsqu'on effectue une requête PUT pour modifier le code d'utilisateur pour « 2 », alors on obtient un JSON qui contient une réservation dont le code est 2 et le code d'utilisateur est « 2 » ainsi qu'un code de retour 200` () {

    }

    @Test
    //@PutMapping("/réservations/{code}")
    fun `Étant donnée la réservation dont le code est 78 et qui n'est pas inscrit au service, lorsqu'on effectue une requête PUT, alors on obtient un JSON qui contient une réservation dont le code est 78 ainsi qu'un code de retour 201` () {

    }

    @Test
    //@DeleteMapping("/réservations/{code}")
    fun `Étant donnée la réservation dont le code est 2 et qui est inscrit au service, lorsqu'on effectue une requête DELETE, alors on obtient un code de retour 204` () {

    }

    @Test
    //@DeleteMapping("/réservations/{code}")
    fun `Étant donnée la réservation dont le code est 78 et qui n'est pas inscrit au service, lorsqu'on effectue une requête DELETE, alors on obtient un code de retour 404` () {

    }

 */
}