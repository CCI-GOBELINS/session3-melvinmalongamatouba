package com.gmail.melvinmalongamatouba.todido.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gmail.melvinmalongamatouba.todido.MainActivity
import com.gmail.melvinmalongamatouba.todido.R
import com.gmail.melvinmalongamatouba.todido.model.Statut
import com.gmail.melvinmalongamatouba.todido.viewmodel.TacheViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


private fun LocalDateTime.show(): String {
    return this.format(formatter)
}

private fun String.toDate() : LocalDateTime{

    return LocalDateTime.parse(this, formatter)

}

val formatter: DateTimeFormatter? = DateTimeFormatter.ofPattern("dd/MM/YYYY : HH:mm")
val FIELD_SET_CARD_COLOR = Color.LightGray
val FONT_SIZE_FIELD = 16.sp
val PADDING_CLEF = 16.dp
val FIELD_CARD_PADDING = 4.dp
val COLOR_A_FAIRE = Color.Red
val COLOR_EN_COURS = Color.Yellow
val COLOR_TERMINEE = Color.Green





@Composable
fun Tache(tacheVM: TacheViewModel){
    //charger chaque caractéristique séparemment pour diminuer la recomposition
    val statut = tacheVM.getStatut()
    val stringMap = tacheVM.getStringFields()
    val dateDeCreation = tacheVM.getDateDeCreationStr()
    val dateDeRendu = tacheVM.getDateDeRendu()
    val dateDeMiseAJour = tacheVM.getDateDeMiseAJour()
    fun enregistrer(){
        tacheVM.enregistrer()
    }

    Scaffold { paddingValues ->
        Column(modifier = tacheVM.modifier
            .padding(paddingValues)
            .fillMaxSize()) {
            Text(
                text = "Derniere mise à jour : ${dateDeMiseAJour.value.show()}",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()

            )


            Row (modifier = tacheVM.modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){
                Statut(statut, tacheVM.modifier)
                BoutonRetour(modifier = tacheVM.modifier, navController = tacheVM.navController)
                BoutonEnregistrer(modifier =tacheVM.modifier, enregistrer = {enregistrer()})

            }

            ARendrePour(dateDeRendu, tacheVM.modifier)


            FieldSet(clefs = stringMap.keys.toList(), valeurs = stringMap.values.toList(), modifier= tacheVM.modifier.weight(
                1f
            ))
            Text(
                text = "Tache créée le : $dateDeCreation",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

        }
    }

}

@Composable
fun BoutonRetour(modifier: Modifier, navController: NavController) {
    Icon(
        painter = painterResource(R.drawable.back),
        contentDescription = "Arrow to go back to the lsit view",
        modifier = modifier.clickable(
            onClick = {
                navController.navigate("liste")
            }
        )

    )

}

@Composable
fun BoutonEnregistrer(modifier: Modifier = Modifier, enregistrer : ()-> Unit) {
    val context = LocalContext.current
    Button(
        onClick = {
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
            enregistrer()},
        shape = RoundedCornerShape(0.dp),
        modifier = modifier.padding(10.dp)
    ) {
        Text(text = "sauver",
            autoSize = TextAutoSize.StepBased(minFontSize = 10.sp, maxFontSize = 20.sp, stepSize = 1.sp),
            modifier = Modifier.wrapContentSize(Alignment.Center))



    }
}



@Composable
fun ARendrePour(date: MutableState<LocalDateTime>, modifier: Modifier){
    Row (verticalAlignment = Alignment.CenterVertically) {
        Field(
            clef ="pour le :",
            valeur = remember { mutableStateOf(date.value.show()) },
            modifier = modifier.weight(5f),

        )
        ChoixDateRendu(modifier.weight(1f))
    }
}



@Composable
fun ChoixDateRendu(modifier : Modifier = Modifier){
    var showDatePicker by remember { mutableStateOf(false) }
    Icon(
        painter = painterResource(R.drawable.outline_calendar_month_24),
        contentDescription = "Choississez la date de rendu",
        modifier = modifier
            .clickable(
                onClick = { showDatePicker = true }
            )
            .fillMaxWidth()
    )

    if (showDatePicker){
        //DialogChoixDeDate()
    }
}

@Composable
fun DialogChoixDeDate() {
    TODO("Not yet implemented")
}

@Composable
fun Statut(statut : MutableState<Statut>, modifier: Modifier = Modifier){
    val color = when (statut.value){
        Statut.A_FAIRE -> COLOR_A_FAIRE
        Statut.EN_COURS -> COLOR_EN_COURS
        Statut.TERMINEE -> COLOR_TERMINEE
    }
    val displayChangeStatus = remember { mutableStateOf(false) }

    Box {

        Card(
            modifier = modifier
                .padding(10.dp)
                .clickable(
                    onClick = {
                        displayChangeStatus.value = true
                    }
                ),
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = color
            )

        ) {
            Text(
                text = statut.value.toString(),  //PlaceHolder
                color = Color.DarkGray,
                modifier = modifier
                    .padding(10.dp)
                    .wrapContentSize(Alignment.Center)
            )
        }
        if (displayChangeStatus.value){
            ChangeStatus(statut, displayChangeStatus, modifier)
        }
    }
}

@Composable
fun StatutBubbleOnlyAesthetic(statut: Statut, modifier : Modifier = Modifier ){
    val color = when (statut){
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
            text = statut.toString(),  //PlaceHolder
            color = Color.DarkGray,
            modifier = modifier
                .padding(10.dp)
                .wrapContentSize(Alignment.Center)
        )
    }

}

@Composable
fun ChangeStatus(statut : MutableState<Statut>, displayChangeStatus : MutableState<Boolean>,  modifier: Modifier = Modifier) {
    Card{
        LazyRow {
            items(count = Statut.entries.size) { index ->
                StatutBubbleOnlyAesthetic(
                    Statut.entries[index],
                    modifier.clickable(
                        onClick = {
                            statut.value = Statut.entries[index]
                            displayChangeStatus.value = false
                        }
                    ))

            }

        }
    }
}

@Composable
fun FieldSet(clefs : List<String>, valeurs : List<MutableState<String>>, modifier: Modifier = Modifier){
    val modifier = modifier.fillMaxWidth()
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = FIELD_SET_CARD_COLOR
        )
    ) {
        LazyColumn(modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(4.dp)) {
            items(clefs.size) { index ->
                Field(clefs[index], valeurs[index], modifier)
            }
        }
    }

}
@Composable
fun Field(clef : String , valeur : MutableState<String>, modifier: Modifier = Modifier, onValueChange : (String) -> Unit = { valeur.value = it }){

    Card(modifier = modifier) {
        Row (verticalAlignment = Alignment.CenterVertically ){
            Text(
                text = clef,
                fontSize = FONT_SIZE_FIELD,
                modifier = Modifier
                    .weight(1f)
                    .padding(PADDING_CLEF)
            )

            OutlinedTextField(
                value = valeur.value,
                onValueChange = onValueChange,
                textStyle = LocalTextStyle.current.merge(TextStyle(fontSize = FONT_SIZE_FIELD)),
                modifier = Modifier.weight(2f),
                shape = RoundedCornerShape(0.dp)
            )

        }
    }
}

@Composable
@Preview
fun StatutBu(){
    StatutBubbleOnlyAesthetic(Statut.TERMINEE)
}

@Composable
@Preview
fun ChangeStatusPreview(){
    ChangeStatus(remember { mutableStateOf(Statut.EN_COURS)}, remember { mutableStateOf(false)})
}

@Composable
@Preview
fun BoutonEnregistrerPreview(){
    BoutonEnregistrer(Modifier, enregistrer = {})
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

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun ARendrePourPreview(){
    ARendrePour(remember{mutableStateOf(LocalDateTime.now())}, Modifier)
}

@Composable
@Preview
fun ChoixDateDeRenduPreview(){
    ChoixDateRendu()
}

@Composable
@Preview
fun TachePreview(){
    val tacheVM = TacheViewModel(MainActivity.Companion.tache, navController = rememberNavController())
    Tache(tacheVM)
}