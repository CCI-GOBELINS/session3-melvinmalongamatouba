package com.gmail.melvinmalongamatouba.todido

import com.gmail.melvinmalongamatouba.todido.model.Tache
import com.gmail.melvinmalongamatouba.todido.viewmodel.TachesViewModel
import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDateTime

class TachesViewModelTest {

    @Test
    fun testCount (){
        val tachesVM = TachesViewModel(
            taches = listOf(
                Tache(
                    libelle = "libelle",
                    type = "type",
                    description = "desc",
                    dateDeRendu = LocalDateTime.now()
                ),
                Tache(
                    libelle = "libelle",
                    type = "type",
                    description = "desc",
                    dateDeRendu = LocalDateTime.now()
                ),
                Tache(
                    libelle = "libelle",
                    type = "type",
                    description = "desc",
                    dateDeRendu = LocalDateTime.now()
                ),
                Tache(
                    libelle = "libelle",
                    type = "type",
                    description = "desc",
                    dateDeRendu = LocalDateTime.now()
                )

            )


        )

        assertEquals(4, tachesVM.count())
        println(tachesVM.count())
    }

    @Test
    fun testContent () {
        val tachesVM = TachesViewModel(
            taches = listOf(
                Tache(
                    libelle = "0",
                    type = "type",
                    description = "desc",
                    dateDeRendu = LocalDateTime.now()
                ),
                Tache(
                    libelle = "1",
                    type = "type",
                    description = "desc",
                    dateDeRendu = LocalDateTime.now()
                ),
                Tache(
                    libelle = "2",
                    type = "type",
                    description = "desc",
                    dateDeRendu = LocalDateTime.now()
                ),
                Tache(
                    libelle = "3",
                    type = "type",
                    description = "desc",
                    dateDeRendu = LocalDateTime.now()
                ),
                Tache(
                    libelle = "4",
                    type = "type",
                    description = "desc",
                    dateDeRendu = LocalDateTime.now()
                )
            )
        )

        assertEquals("0", tachesVM.item(0).getLibelle())
        assertEquals("1", tachesVM.item(1).getLibelle())
        assertEquals("2", tachesVM.item(2).getLibelle())
        assertEquals("3", tachesVM.item(3).getLibelle())
        assertEquals("4", tachesVM.item(4).getLibelle())
    }

    @Test
    fun testContentStatic(){
        val tachesVM = TachesViewModel(
            taches = MainActivity.taches,
            modeDeTri = null
        )

        assertEquals(MainActivity.taches.size ,tachesVM.count())
        assertEquals(MainActivity.taches[0], tachesVM.item(0))
    }

}