package com.example.agendamaster.database.dao

import androidx.room.*
import com.example.agendamaster.model.Contato


@Dao
interface ContatoDao {

    @Query("SELECT * FROM Contato")
    fun buscaTodos(): List<Contato>

    @Insert
    fun salva( vararg contato: Contato)

    @Delete
    fun remove(contato: Contato)

    @Update
    fun atualiza(contato: Contato)

    @Query("SELECT * FROM Contato WHERE id = :id" )
    fun buscaPorId(id: Long) : Contato?

}