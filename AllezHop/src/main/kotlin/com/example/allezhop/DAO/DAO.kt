package com.example.allezhop.DAO

interface DAO<T> {
    fun chercherTous(): List<T>
    fun chercherParCode(code: Int): List<T>?
    fun ajouter(unT: T): T?

    fun modifier(code: Int ,unT: T): T?

    fun supprimer(code: String)


}