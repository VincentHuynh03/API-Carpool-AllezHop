package com.example.allezhop.Controleurs

import com.example.allezhop.Modèles.Reservation
import com.example.allezhop.Modèles.Trajet
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.sql.Timestamp
import com.example.allezhop.Services.TrajetService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.springframework.test.web.servlet.MockMvc


import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.web.bind.annotation.PostMapping


@SpringBootTest
@AutoConfigureMockMvc
class TrajetControleurTest {
    @MockBean
    lateinit var service: TrajetService

    @Autowired
    private lateinit var mockMvc: MockMvc
    @Test
    //GetMapping("/trajet/{code}")
    fun `Étant donné le trajet dont le code est 1 lorsqu'on effectue une requête GET de recherche par code alors on obtient un JSON qui contient un trajet dont le code est 1 et un code de retour 200`() {

    }

    @Test
    //GetMapping("/trajet/{code}")
    fun `Étant donné le trajet dont le code est 0 et qui n'est pas inscrit au service, lorsqu'on effectue une requête GET de recherche par code, alors on obtient un code de retour 404 et le message "le trajet 0 n'est pas inscrit au service"`() {

    }

    @Test
    //PostMapping("/trajets")
    fun `Étant donnée le trajet dont le code est 1 qui existe déjà lorsqu'on effectue une requête POST pour l'ajouter alors on obtient un code de retour 409`() {

    }

    @Test
    //PostMapping("/trajets")
    fun `Étant donnée le trajet dont le code est 78 et qui n'est pas inscrit au service lorsqu'on effectue une requête POST pour l'ajouter, alors on obtient un code de retour 200` () {

    }

    @Test
    //@PutMapping("/trajets/{code}")
    fun `Étant donnée le trajet dont le code est 2 et qui est inscrit au service et dont le conducteur est John Doe lorsqu'on effectue une requête PUT pour modifier le conducteur pour « Jane Doe », alors on obtient un JSON qui contient un trajet dont le code est 2 et le conducteur est « Jane Doe » ainsi qu'un code de retour 200` () {

    }

    @Test
    //@PutMapping("/trajets/{code}")
    fun `Étant donnée le trajet dont le code est 78 et qui n'est pas inscrit au service, lorsqu'on effectue une requête PUT, alors on obtient un JSON qui contient un trajet dont le code est 78 ainsi qu'un code de retour 201` () {

    }

    @Test
    //@DeleteMapping("/trajets/{code}")
    fun `Étant donnée le trajet dont le code est 2 et qui est inscrit au service, lorsqu'on effectue une requête DELETE, alors on obtient un code de retour 204` () {

    }

    @Test
    //@DeleteMapping("/trajets/{code}")
    fun `Étant donnée le trajet dont le code est 78 et qui n'est pas inscrit au service, lorsqu'on effectue une requête DELETE, alors on obtient un code de retour 404` () {

    }



}