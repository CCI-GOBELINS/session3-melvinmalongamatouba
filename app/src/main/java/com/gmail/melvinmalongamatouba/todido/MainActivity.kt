package com.gmail.melvinmalongamatouba.todido

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gmail.melvinmalongamatouba.todido.model.Tache
import com.gmail.melvinmalongamatouba.todido.model.Taches
import com.gmail.melvinmalongamatouba.todido.ui.theme.ToDiDoTheme
import com.gmail.melvinmalongamatouba.todido.view.Liste
import com.gmail.melvinmalongamatouba.todido.viewmodel.TacheViewModel
import com.gmail.melvinmalongamatouba.todido.viewmodel.TachesViewModel
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //taches[0].libelle
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDiDoTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController,"liste"){
                        composable ("liste"){
                            val tachesVM = TachesViewModel(
                                taches = Taches.liste().values.toList(),
                                modifier = Modifier.padding(innerPadding),
                                navController = navController
                            )
                            Liste(tachesVM)
                        }
                        composable ("tache/{id}",
                        arguments = listOf(
                            navArgument("id"){type = NavType.IntType}
                        )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getInt("id")?:0
                            val tacheVM = TacheViewModel(
                                tache = Taches.get(id),
                                modifier = Modifier.padding(innerPadding),
                                navController = navController
                            )
                            com.gmail.melvinmalongamatouba.todido.view.Tache(tacheVM)
                        }
                    }

                }
            }
        }
    }

    //Utiliser uniquement pour les tests et des valeurs par dfaut dans les previews et à l'initialisation de l'app
    //Les mettre dans le main comme ça est probablement pas la meilleure idée je concède
    companion object {
        val tache = Tache(
            libelle = "libelle",
            type = "sport",
            description = "Une description vreumennnt très très lonngue, interminable vraiment elle n'en finit pas",
            dateDeRendu = LocalDateTime.now()
        )

        val taches = listOf(
            Tache(
                libelle = "libelle",
                type = "sport",
                description = "Une description vreumennnt très très lonngue, interminable vraiment elle n'en finit pas",
                dateDeRendu = LocalDateTime.now()
            ),
            Tache(
                libelle = "libelle2",
                type = "musique",
                description = "lalallalalalalallalalalallaa",
                dateDeRendu = LocalDateTime.now()
            ),
            Tache(
                libelle = "libelle3",
                type = "developpement",
                description = "CODE c1++ c#python .net Java fortran",
                dateDeRendu = LocalDateTime.now()
            ),
            Tache(
                libelle = "libelle4",
                type = "documentation",
                description = "Documenter le code pour qu'il puisse être lu",
                dateDeRendu = LocalDateTime.now()
            ),
            Tache(
                libelle = "libelle",
                type = "sport",
                description = "Une description vreumennnt très très lonngue, interminable vraiment elle n'en finit pas",
                dateDeRendu = LocalDateTime.now()
            )
        )
    }
}

