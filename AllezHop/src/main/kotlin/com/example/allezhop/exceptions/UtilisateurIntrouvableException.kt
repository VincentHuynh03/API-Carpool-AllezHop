package crosemont.tdi.g66.restaurantapirest.exceptions

import org.springframework.web.bind.annotation.ResponseStatus

import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)

class UtilisateurIntrouvableException(s: String) : RuntimeException(s) {


    fun UtilisateurIntrouvableException(s: String?) {
        (s)
    }


}

