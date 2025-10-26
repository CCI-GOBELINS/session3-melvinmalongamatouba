package com.gmail.melvinmalongamatouba.todido.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gmail.melvinmalongamatouba.todido.MainActivity
import com.gmail.melvinmalongamatouba.todido.R
import com.gmail.melvinmalongamatouba.todido.model.Statut
import com.gmail.melvinmalongamatouba.todido.model.Tache
import com.gmail.melvinmalongamatouba.todido.model.Taches
import com.gmail.melvinmalongamatouba.todido.viewmodel.ModeDeTri
import com.gmail.melvinmalongamatouba.todido.viewmodel.TachesViewModel
import java.time.LocalDateTime






@Composable
fun Liste(tachesVM : TachesViewModel){
    var showSortingChoice = remember {mutableStateOf(false)}
    if (showSortingChoice.value){
        SortingChoices(tachesVM, showSortingChoice)
    } else {


        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.add),
                    contentDescription = "Créer une nouvelle tâche",
                    modifier = Modifier
                        .size(48.dp) // Taille de l’icône
                        .clickable {
                            val tache = Tache(
                                libelle = "",
                                type = "",
                                description = "",
                                dateDeRendu = LocalDateTime.now()
                            )
                            tachesVM.navController.navigate("Tache/${tache.getId()}")
                        },
                    tint = MaterialTheme.colorScheme.primary
                )

                Icon(
                    painter = painterResource(R.drawable.sort),
                    contentDescription = "Changer le critère de tri",
                    modifier = Modifier
                        .size(48.dp)
                        .clickable {
                            showSortingChoice.value = true
                        },
                    tint = MaterialTheme.colorScheme.primary
                )
            }


            LazyColumn(modifier = tachesVM.modifier.fillMaxWidth()) {
                items(tachesVM.count()) { index ->
                    ListItem(
                        tachesVM.item(index),
                        navController = tachesVM.navController,
                        modifier = tachesVM.modifier
                    )
                }
            }
        }
    }
}



@Composable
fun ListItem(tache: Tache, navController: NavController, modifier: Modifier = Modifier){

    val color = when(tache.getStatut()){
        Statut.A_FAIRE -> COLOR_A_FAIRE
        Statut.EN_COURS -> COLOR_EN_COURS
        Statut.TERMINEE -> COLOR_TERMINEE
    }
    OutlinedCard(
        modifier = Modifier.fillMaxWidth().padding(16.dp).clickable(
            onClick = {
                navController.navigate("tache/${tache.getId()}")
            }
        )
        ,
        border = BorderStroke(2.dp, color)
    ) {
        Row (modifier= Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = tache.getLibelle(),
                textAlign = TextAlign.Left,
                modifier = modifier.padding(16.dp).weight(1f)
            )
            Text(
                text = tache.getDescription(),
                textAlign = TextAlign.Left,
                modifier = modifier.padding(16.dp).weight(2f)
            )
        }
    }
}

@Composable
fun SortingChoices(tachesVM: TachesViewModel, showSortingChoice : MutableState<Boolean>){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(0.dp) // ou 4.dp, etc.
    ) {
        items(ModeDeTri.entries.size) { index ->
            Text(
                text = ModeDeTri.entries[index].toString(),
                modifier = tachesVM.modifier
                    .clickable {
                        tachesVM.setModeDetri(ModeDeTri.entries[index])
                        showSortingChoice.value = false
                    }
            )
        }
    }

}

@Composable
@Preview
fun SortingChoicesPreview(){
    SortingChoices(
        tachesVM = TachesViewModel(
            taches = Taches.liste().values.toList(),
            navController = rememberNavController(),
            modeDeTri = null,
            modifier = Modifier
        ),
        remember { mutableStateOf(false) }
    )
}

@Composable
@Preview
fun ListePreview(){

    val tachesVM = TachesViewModel(
        taches = MainActivity.taches,
        modeDeTri = null,
        navController = rememberNavController()
    )
    Liste(
        tachesVM = tachesVM,
    )
}



@Composable
@Preview
fun ListItemPreview(){
    val tache = Tache(
        libelle = "libelle",
        type ="type",
        description = "description",
        dateDeRendu = LocalDateTime.now()
    )
    ListItem(
        tache = tache,
        navController = rememberNavController(),
        modifier = Modifier
    )
}