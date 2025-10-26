package com.gmail.melvinmalongamatouba.todido

import com.gmail.melvinmalongamatouba.todido.model.Statut
import com.gmail.melvinmalongamatouba.todido.model.Tache
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime

class TacheModelTest {
    @Test
    fun testDateCreation(){
        val tache = Tache("libelle", "type", "description", LocalDateTime.now())
        assertEquals( "date de creation fausse" ,LocalDate.now().toString(),  tache.getDateDeCreation().toString() )
    }

    @Test
    fun testConstructeur(){
        val date = LocalDateTime.now()
        val tache = Tache("libelle", "type", "description", date)
        assertEquals("libelle",tache.getLibelle())
        assertEquals("type", tache.getType())
        assertEquals("description", tache.getDescription())
        assertEquals(date, tache.getDateDeRendu())

    }

    // Test requires some waiting but not to bad
    @Test
    fun testSetWorks() {
        runBlocking {

           val dateRendu = LocalDateTime.now()
            val tache = Tache("libelle", "type", "description", dateRendu)
            val timeAtStart = tache.getDateDeCreation()
            val waitingIsDone = async { delay(2000L)} //wait for 1min and 2 seconds
            assertEquals("libelle", tache.getLibelle())
            assertEquals("type", tache.getType())
            assertEquals("description", tache.getDescription())
            assertEquals(dateRendu, tache.getDateDeRendu())
            tache.setLibelle("newLibelle")
            tache.setType("newType")
            tache.setDescription("newDescription")
            val newDateDeRendu = LocalDateTime.now()
            tache.setDateDeRendu(newDateDeRendu)


            waitingIsDone.await()

            tache.setStatut(Statut.EN_COURS) // Ce set après le wait doit changer la date de mise à jour
            assertEquals(Statut.EN_COURS, tache.getStatut())
            assertEquals(timeAtStart, tache.getDateDeCreation())
            assertEquals("newLibelle",tache.getLibelle())
            assertEquals("newType", tache.getType())
            assertEquals("newDescription", tache.getDescription())
            assertEquals(newDateDeRendu, tache.getDateDeRendu())
            assertNotEquals(timeAtStart, tache.getDateDeMiseAJour())
            assertEquals(timeAtStart, tache.getDateDeCreation())
        }
    }
}