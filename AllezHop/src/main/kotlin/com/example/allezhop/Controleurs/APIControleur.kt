package com.example.allezhop.Controleurs

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class APIControleur {
    @GetMapping("/")
    fun index()= "Service web du service de AllezHop"
}