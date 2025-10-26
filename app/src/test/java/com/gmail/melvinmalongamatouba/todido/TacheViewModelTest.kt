package com.gmail.melvinmalongamatouba.todido

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.gmail.melvinmalongamatouba.todido.model.Tache
import com.gmail.melvinmalongamatouba.todido.viewmodel.TacheViewModel
import org.junit.Test
import java.time.LocalDateTime

class TacheViewModelTest {

    @Composable
    @Test
    fun testEnregistrer(){
        val tache = Tache(
            libelle = "libelle",
            type ="type",
            description = "description",
            dateDeRendu = LocalDateTime.now()
        )
        val tacheVM = TacheViewModel(
            tache = tache,
            navController = rememberNavController()
        )

        val stringFields = tacheVM.getStringFields()

        stringFields["libelle"]!!.value = "newLibelle" //si ça crash donne deja des renseignements

        tacheVM.enregistrer()
    }
}