package com.example.allezhop.DAO

interface DAO<T> {
    fun chercherTous(): List<T>
    fun chercherParCode(code: Int): T?
    fun ajouter(unT: T): T?

    fun modifier(code: String ,unT: T): T?

    fun supprimer(code: String): Boolean
}