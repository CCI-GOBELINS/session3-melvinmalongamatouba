package com.gmail.melvinmalongamatouba.todido

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import java.time.LocalDate


data class TacheViewModel(private val tache: Tache, public val modifier: Modifier = Modifier){

    fun getStringFields() : Map<String, MutableState<String>>{
        val res = mutableMapOf<String, MutableState<String>>()
        res["Libelle"] = mutableStateOf(tache.getLibelle())
        res["Type"] = mutableStateOf(tache.getType())
        res["Description"] = mutableStateOf(tache.getDescription())
        return res
    }

    fun getStatut() : MutableState<Statut> {
        return mutableStateOf(tache.getStatut())
    }


    //set(int year, int month, int date)

    fun getDateDeMiseAJourStr() : String {
        return tache.getDateDeMiseAJour().toString()
    }
    fun getDateDeCreationStr() : String {
        return tache.getDateDeCreation().toString()
    }

    fun getDateDeRendu() : LocalDate {
        return tache.getDateDeRendu()
    }
}