package com.example.allezhop.DAO

import org.springframework.web.bind.annotation.ResponseStatus

import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)

class IntrouvableException(message: String? = null, cause: Throwable? = null) : RuntimeException(message, cause) {
}

