package com.gmail.melvinmalongamatouba.todido.viewmodel

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.gmail.melvinmalongamatouba.todido.model.Tache

class TachesViewModel(private var taches : List<Tache>, val navController: NavController, private var modeDeTri : ModeDeTri? = null, val modifier : Modifier = Modifier ) {
    init {
        sort(modeDeTri)
    }
    fun count() : Int {
        return taches.size
    }

    fun item(index : Int): Tache {
        return taches[index]
    }

    fun setModeDetri(mode : ModeDeTri){
        modeDeTri = mode
        sort(modeDeTri)
    }

    private fun sort(modeDeTri: ModeDeTri?){
        when(modeDeTri){
            ModeDeTri.DATE_DE_RENDU -> taches = taches.sortedByDescending { tache -> tache.getDateDeRendu() }
            ModeDeTri.DATE_DE_CREATION -> taches = taches.sortedByDescending { tache -> tache.getDateDeCreation() }
            ModeDeTri.STATUT -> taches = taches.sortedByDescending { tache -> tache.getStatut() }
            ModeDeTri.TYPE -> taches = taches.sortedByDescending { tache -> tache.getType() }
            null -> taches = taches
        }

    }



}

enum class ModeDeTri {
    DATE_DE_RENDU,
    DATE_DE_CREATION,
    STATUT,
    TYPE

}
