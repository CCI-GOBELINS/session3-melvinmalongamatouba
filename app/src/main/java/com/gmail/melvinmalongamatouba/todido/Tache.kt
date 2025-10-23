package com.gmail.melvinmalongamatouba.todido

import android.icu.util.Calendar
import android.text.format.DateFormat

object TacheIdGenerator {
    private var currentId = 0

    fun generateId(): Int {
        currentId += 1
        return currentId
    }
}
enum class Statut {
    A_FAIRE,
    EN_COURS,
    TERMINEE
}

data class Tache(var libelle: String, var type : String, var description : String, var dateDeRendu: Calendar ) {
    private var statut : Statut = Statut.A_FAIRE

    private val id : Int = TacheIdGenerator.generateId()
    private var dateDeMiseAJour : Calendar = Calendar.getInstance()
    private var dateDeCreation : Calendar = Calendar.getInstance()



    init{
        this.statut = Statut.A_FAIRE
        this.dateDeCreation = Calendar.getInstance()
        this.dateDeMiseAJour = Calendar.getInstance()
    }

    //GETTERS

    //"privés" utilisés pour des fonctionnalités internes spécifique pas pour un display
    fun getStatut() : Statut {
        return this.statut
    }

    fun getDateDeRendu() : Calendar {
        return this.dateDeRendu
    }

    fun getDateDeMiseAJour() : Calendar {
        return this.dateDeMiseAJour
    }

    fun getLibelle() : String {
        return this.libelle
    }


    fun get() : Map<String, Any> {
        return mapOf("id" to this.id,
            "libelle" to this.libelle,
            "type" to this.type,
            "description" to this.description,
            "dateDeRendu" to dateDeRendu.clone(),
            "statut" to this.statut.toString(),
            "dateDeCreation" to this.dateDeCreation.clone(),
            "dateDeMiseAJour" to this.dateDeMiseAJour.clone()
        )
    }


    //SETTERS

    private fun quandMisAJour() {
        this.dateDeMiseAJour = Calendar.getInstance()
    }

    fun setStatut(statut: Statut) {
        this.statut = statut
        quandMisAJour()
    }

    fun setDateDeRendu(dateDeRendu: Calendar) {
        this.dateDeRendu = dateDeRendu
        quandMisAJour()
    }

    fun setLibelle(libelle: String) {
        this.libelle = libelle
        quandMisAJour()
    }

    fun setType(type: String) {
        this.type = type
        quandMisAJour()
    }

    fun setDescription(description: String) {
        this.description = description
        quandMisAJour()
    }

    // Suppression

    fun deleteTache() {
        // Code pour supprimer la tâche
    }

    



}