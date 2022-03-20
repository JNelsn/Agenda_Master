package com.example.agendamaster.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Contato(

    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,
    val nome : String,
    val email: String,
    val telefone: String,

) : Parcelable


