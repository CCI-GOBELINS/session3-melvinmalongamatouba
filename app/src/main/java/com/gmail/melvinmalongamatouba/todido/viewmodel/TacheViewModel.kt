package com.gmail.melvinmalongamatouba.todido.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.gmail.melvinmalongamatouba.todido.model.Statut
import com.gmail.melvinmalongamatouba.todido.model.Tache
import com.gmail.melvinmalongamatouba.todido.model.Taches
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class TacheViewModel(private var tache: Tache, public val modifier: Modifier = Modifier, val navController: NavController){
    private val libelle = mutableStateOf<String>(tache.getLibelle())
    private val type = mutableStateOf(tache.getType())
    private val description = mutableStateOf(tache.getDescription())
    private val statut = mutableStateOf(tache.getStatut())
    private val dateDeRendu = mutableStateOf(tache.getDateDeRendu())


    //Get "modifiable"
    fun getStringFields() : Map<String, MutableState<String>>{
        val res = mutableMapOf<String, MutableState<String>>()
        res["Libelle"] = libelle
        res["Type"] = type
        res["Description"] = description
        return res
    }

    fun getStatut() : MutableState<Statut> {
        return statut
    }
    fun getDateDeRendu() : MutableState<LocalDateTime> {
        return dateDeRendu
    }


    ///get en lecture seule

    private fun LocalDateTime.show(): String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY : HH:mm")
        return this.format(formatter)
    }
    fun getDateDeMiseAJourStr() : String {
        return tache.getDateDeMiseAJour().show()
    }
    fun getDateDeCreationStr() : String {
        return tache.getDateDeCreation().show()
    }



    fun enregistrer(){
        tache.setLibelle(libelle.value).setDescription(description.value).setType(type.value).setStatut(statut.value).setDateDeRendu(dateDeRendu.value)
        this.tache = Taches.get(tache.getId())
    }

    fun enregistrer (libelle : String, description: String, type : String, statut : Statut, dateDeRendu : LocalDateTime){
        tache.setLibelle(libelle).setDescription(description).setType(type).setStatut(statut).setDateDeRendu(dateDeRendu)
        this.tache = Taches.get(tache.getId())
    }
}