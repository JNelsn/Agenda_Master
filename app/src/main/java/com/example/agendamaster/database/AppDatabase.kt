package com.example.agendamaster.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.agendamaster.database.dao.ContatoDao
import com.example.agendamaster.model.Contato

@Database(entities = [Contato::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contatoDao(): ContatoDao

    companion object {
        fun instancia(context: Context) : AppDatabase{
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "agendamaster.db"
            ).allowMainThreadQueries()
                .build()


        }
    }

}