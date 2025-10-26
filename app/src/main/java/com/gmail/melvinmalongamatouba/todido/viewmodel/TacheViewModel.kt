package com.gmail.melvinmalongamatouba.todido.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.gmail.melvinmalongamatouba.todido.model.Statut
import com.gmail.melvinmalongamatouba.todido.model.Tache
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class TacheViewModel(private var tache: Tache, public val modifier: Modifier = Modifier, val navController: NavController){
    private val libelle = mutableStateOf<String>(tache.getLibelle())
    private val type = mutableStateOf(tache.getType())
    private val description = mutableStateOf(tache.getDescription())
    private val statut = mutableStateOf(tache.getStatut())
    private val dateDeRendu = mutableStateOf(tache.getDateDeRendu())
    private var dateDeMiseAJour = mutableStateOf(tache.getDateDeMiseAJour())


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
    fun getDateDeCreationStr() : String {
        return tache.getDateDeCreation().show()
    }

    //get en lecture "dynamique"

    fun getDateDeMiseAJour() : MutableState<LocalDateTime> {
        dateDeMiseAJour = mutableStateOf(tache.getDateDeMiseAJour())
        return dateDeMiseAJour
    }



    fun enregistrer(){
        tache.setLibelle(libelle.value).setDescription(description.value).setType(type.value).setStatut(statut.value).setDateDeRendu(dateDeRendu.value)
        dateDeMiseAJour = mutableStateOf(tache.getDateDeMiseAJour())
    }

}