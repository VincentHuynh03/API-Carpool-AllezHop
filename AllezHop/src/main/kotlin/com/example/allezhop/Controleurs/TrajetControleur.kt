package com.example.allezhop.Controleurs

import com.example.allezhop.Services.TrajetService
import com.example.allezhop.DAO.IntrouvableException
import com.example.allezhop.Modèles.Trajet
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.security.Principal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RestController
@RequestMapping("/trajets")
class TrajetControleur(val service: TrajetService) {

    @GetMapping
    fun obtenirTrajets() = service.chercherTous()

    @GetMapping("/{code}")
    fun obtenirTrajetsParCode(@PathVariable code: String) =
        service.chercherParCode(code) ?: throw IntrouvableException("Le trajet avec le code $code n'est pas inscrit au service.")

    @GetMapping(params = ["conducteur"])
    fun obtenirTrajetsParConducteurNom(@RequestParam nom: String) =
        service.chercherParConducteurNom(nom)
            ?: throw IntrouvableException("Aucun trajet trouvé avec $nom comme conducteur")

    @GetMapping(params = ["date"])
    fun obtenirTrajetsParDate(@RequestParam date: String): List<Trajet>? {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateEnDate = LocalDateTime.parse(date, formatter)
        return service.chercherParDate(dateEnDate) ?: throw IntrouvableException("Aucun trajet trouvé avec la date $date")
    }

    @GetMapping(params = ["ville"])
    fun obtenirTrajetsParVille(@RequestParam ville: String) =
        service.chercherParVille(ville) ?: throw IntrouvableException("Aucun trajet trouvé avec la ville $ville")

    @GetMapping(params = ["état"])
    fun obtenirTrajetsParÉtat(@RequestParam état: String) =
        service.chercherParÉtat(état) ?: throw IntrouvableException("Aucun trajet trouvé avec l'état/province $état")

    @GetMapping(params = ["pays"])
    fun obtenirTrajetsParPays(@RequestParam pays: String) =
        service.chercherParPays(pays) ?: throw IntrouvableException("Aucun trajet trouvé avec le pays $pays")

    @Operation(
        summary = "Ajouter un trajet.",
        description = "Ajouter un trajet au service.",
        operationId = "ajouter",
        responses = [
            ApiResponse(responseCode = "201", description = "Le trajet a été ajouté."),
            ApiResponse(responseCode = "400", description = "La requête est mal formulée."),
            ApiResponse(responseCode = "401", description = "L'utilisateur voulant effectuer l'opération n'est pas correctement authentifié."),
            ApiResponse(responseCode = "403", description = "Seuls les conducteurs peuvent ajouter un trajet."),
            ApiResponse(responseCode = "409", description = "Le trajet est déjà inscrit au service.")
        ])
    @PostMapping(
        value = ["/trajets"],
        consumes = ["application/json"],
        produces = ["application/json"])
    fun ajouterTrajet(@RequestBody trajet: Trajet, utilsateur: Principal): ResponseEntity<Trajet> {
        val nouveauTrajet: Trajet? = service.ajouter(trajet, utilsateur.name)
        if (nouveauTrajet != null) {
            val uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{code}")
                .buildAndExpand(nouveauTrajet.code)
                .toUri()

            return ResponseEntity.created(uri).body(nouveauTrajet)
        }
        return ResponseEntity.internalServerError().build()
    }


    @Operation(
        summary = "Modifier un trajet.",
        description = "Modifie les informations sur le trajet.",
        operationId = "modifier",
        responses = [
            ApiResponse(responseCode = "200", description = "Les informations sur le trajet ont été mises à jour."),
            ApiResponse(responseCode = "201", description = "Le trajet n'existait pas, il a été ajouté."),
            ApiResponse(responseCode = "400", description = "La requête est mal formulée."),
            ApiResponse(responseCode = "401", description = "L'utilisateur voulant effectuer l'opération n'est pas correctement authentifié."),
            ApiResponse(responseCode = "403", description = "Les conducteurs ne peuvent modifier que leurs propres trajets."),
        ])
    @PutMapping(
        value = ["/trajets/{code}"],
        consumes = ["application/json"],
        produces = ["application/json"])
    fun modifierTrajet(@PathVariable code: String, @RequestBody trajet: Trajet, utilisateur: Principal): ResponseEntity<Trajet>  {
        if(service.chercherParCode(code) != null){
            val trajetMAJ = service.modifier(code, trajet, utilisateur.name)

            return ResponseEntity.ok(trajetMAJ)
        } else {
            return ajouterTrajet(trajet, utilisateur)
        }
    }

    @Operation(
        summary = "Supprimer un trajet.",
        description = "Supprimer le trajet du service.",
        operationId = "supprimer",
        responses = [
            ApiResponse(responseCode = "204", description = "Le restaurant a été désinscrit."),
            ApiResponse(responseCode = "400", description = "La requête est mal formulée."),
            ApiResponse(responseCode = "401", description = "L'utilisateur voulant effectuer l'opération n'est pas correctement authentifié."),
            ApiResponse(responseCode = "403", description = "L'utilisateur voulant effectuer l'opération n'a pas les droits nécessaires."),
            ApiResponse(responseCode = "404", description = "Le trajet n'est pas inscrit au service."),
        ])
    @DeleteMapping(
        value = ["/trajets/{code}"])
    fun supprimerTrajet(@PathVariable code: String, utilisateur: Principal): ResponseEntity<Trajet> {
        service.supprimer(code, utilisateur.name)
        return ResponseEntity.noContent().build()
    }
}
