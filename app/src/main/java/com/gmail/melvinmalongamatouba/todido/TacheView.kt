package com.gmail.melvinmalongamatouba.todido

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate

val FIELD_SET_CARD_COLOR = Color.LightGray
val FONT_SIZE_FIELD = 16.sp
val PADDING_CLEF = 16.dp
val FIELD_CARD_PADDING = 10.dp
val COLOR_A_FAIRE = Color.Red
val COLOR_EN_COURS = Color.Yellow
val COLOR_TERMINEE = Color.Green





@Composable
fun Tache(tacheVM: TacheViewModel){
    //charger chaque caractéristique séparemment pour diminuer la recomposition
    val statut = tacheVM.getStatut()
    val stringMap = tacheVM.getStringFields()
    val dateDeCreation = tacheVM.getDateDeCreationStr()
    val dateDeRendu = remember { mutableStateOf(tacheVM.getDateDeRendu())}
    val dateDeMiseAJour = tacheVM.getDateDeMiseAJourStr()

    Scaffold { paddingValues ->
        Column(modifier = tacheVM.modifier.padding(paddingValues)) {
            Text(
                text = "Derniere mise à jour : $dateDeMiseAJour",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()

            )
            Row (modifier = tacheVM.modifier.padding(paddingValues)){
                Statut(statut)
                ARendrePour(dateDeRendu, tacheVM.modifier)
            }
            FieldSet(clefs = stringMap.keys.toList(), valeurs = stringMap.values.toList(), modifier= tacheVM.modifier)
            Text(
                text = "Tache créée le : $dateDeCreation",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

        }
    }

}

@Composable
fun ARendrePour(date : MutableState<LocalDate>, modifier: Modifier){
    Row {
        Field("a rendre le :", valeur = remember { mutableStateOf(date.value.toString()) })
        ChoixDateRendu()
    }
}

@Composable
fun ChoixDateRendu(){
    var showDatePicker by remember { mutableStateOf(false) }
    androidx.compose.material.IconButton(
        onClick = {
            // Open DatePickerDialog
            showDatePicker = true
        },

    ) {
        androidx.compose.material.Icon(
            modifier = Modifier.size(30.dp),
            painter = rememberVectorPainter(image = Icons.Filled.DateRange),
            contentDescription = "Date picker icon")
    }
}

@Composable
fun Statut(statut : MutableState<Statut>, modifier: Modifier = Modifier){
    val color = when (statut.value){
        Statut.A_FAIRE -> COLOR_A_FAIRE
        Statut.EN_COURS -> COLOR_EN_COURS
        Statut.TERMINEE -> COLOR_TERMINEE
    }

    Card(
        modifier = modifier.padding(10.dp),
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = color
        )

    ) {
        Text(
            text = statut.value.toString(),  //PlaceHolder
            color = Color.DarkGray,
            modifier = modifier.padding(10.dp)
        )
    }
}

@Composable
fun FieldSet(clefs : List<String>, valeurs : List<MutableState<String>>, modifier: Modifier = Modifier){
    val modifier = modifier.fillMaxWidth()
    Card(
        colors = CardDefaults.cardColors(
            containerColor = FIELD_SET_CARD_COLOR
        )
    ) {
        LazyColumn(modifier = modifier) {
            items(clefs.size) { index ->
                Field(clefs[index], valeurs[index], modifier)
            }
        }
    }

}
@Composable
fun Field(clef : String , valeur : MutableState<String>, modifier: Modifier = Modifier){

    Card(modifier = modifier.padding(FIELD_CARD_PADDING)) {
        Row (verticalAlignment = Alignment.CenterVertically ){
            Text(
                text = clef,
                fontSize = FONT_SIZE_FIELD,
                modifier = Modifier.weight(1f).padding(PADDING_CLEF)
            )

            OutlinedTextField(
                value = valeur.value,
                onValueChange = { valeur.value = it },
                textStyle = LocalTextStyle.current.merge(TextStyle(fontSize = FONT_SIZE_FIELD)),
                modifier = Modifier.weight(2f),
                shape = RoundedCornerShape(0.dp)
            )

        }
    }
}

@Composable
@Preview
fun FieldPreview(){
    Field(clef = "clef", valeur = remember {mutableStateOf("valeur")})
}

@Composable
@Preview
fun FieldSetPreview(){
    val clefs = mutableListOf<String>()
    val valeurs = mutableListOf<MutableState<String>>()
    for (i in 1..10){
        clefs.add("clef $i")
        valeurs.add(remember {mutableStateOf("valeur $i")})
    }
    FieldSet(clefs, valeurs)
}


@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun StatutPreview(){
    Column {
        Statut(mutableStateOf(Statut.A_FAIRE))
        Statut(mutableStateOf(Statut.EN_COURS))
        Statut(mutableStateOf(Statut.TERMINEE))
    }
}

@Composable
@Preview
fun TachePreview(){
    val tacheVM = TacheViewModel(MainActivity.tache)
    Tache(tacheVM)
}