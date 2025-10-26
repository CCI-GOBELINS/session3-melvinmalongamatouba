package com.gmail.melvinmalongamatouba.todido.model

import com.gmail.melvinmalongamatouba.todido.MainActivity
import com.gmail.melvinmalongamatouba.todido.model.Taches.generateId
import java.time.LocalDateTime


object Taches {
    private var currentId = 0
    private val liste = mutableMapOf<Int, Tache>()

    fun generateId(): Int {
        currentId += 1
        return currentId
    }

    fun ajouterTache(tache: Tache){
        liste[tache.getId()] = tache
    }

    fun liste() : Map<Int, Tache>{
        return liste
    }

    fun get(id: Int): Tache {
        return liste[id]?: MainActivity.tache
    }
}
enum class Statut {
    A_FAIRE,
    EN_COURS,
    TERMINEE
}

data class Tache(private var libelle: String, private var type : String, private var description : String, private var dateDeRendu: LocalDateTime) {
    private var statut : Statut = Statut.A_FAIRE

    private var id : Int =-1
    private var dateDeMiseAJour : LocalDateTime = LocalDateTime.now()
    private val dateDeCreation : LocalDateTime = LocalDateTime.now()



    init{
        this.statut = Statut.A_FAIRE
        this.dateDeMiseAJour = LocalDateTime.now()
        this.id = Taches.generateId()
        Taches.ajouterTache(this)
    }

    //GETTERS

    //"privés" utilisés pour des fonctionnalités internes spécifique pas pour un display
    fun getStatut() : Statut {
        return this.statut
    }

    fun getDescription() : String{
        return this.description
    }

    fun getDateDeRendu() : LocalDateTime {
        return this.dateDeRendu
    }

    fun getDateDeCreation() : LocalDateTime {
        return this.dateDeCreation
    }

    fun getDateDeMiseAJour() : LocalDateTime {
        return this.dateDeMiseAJour
    }

    fun getType() : String{
        return this.type
    }

    fun getLibelle() : String {
        return this.libelle
    }

    fun getId() : Int {
        return id;
    }


    fun get() : Map<String, Any> {
        return mapOf("id" to this.id,
            "libelle" to this.libelle,
            "type" to this.type,
            "description" to this.description,
            "dateDeRendu" to dateDeRendu, //should maybe be cloned to prevent modification
            "statut" to this.statut.toString(),
            "dateDeCreation" to this.dateDeCreation, ///should maybe be cloned to prevent modification
            "dateDeMiseAJour" to this.dateDeMiseAJour //should maybe be cloned to prevent modification
        )
    }


    //SETTERS

    private fun quandMisAJour()  {
        this.dateDeMiseAJour = LocalDateTime.now()

    }

    fun setStatut(statut: Statut) : Tache {
        this.statut = statut
        quandMisAJour()
        return this
    }

    fun setDateDeRendu(dateDeRendu: LocalDateTime) : Tache {
        this.dateDeRendu = dateDeRendu
        quandMisAJour()
        return this
    }

    fun setLibelle(libelle: String) : Tache {
        this.libelle = libelle
        quandMisAJour()
        return this
    }

    fun setType(type: String) : Tache {
        this.type = type
        quandMisAJour()
        return this
    }

    fun setDescription(description: String) : Tache {
        this.description = description
        quandMisAJour()
        return this
    }




}