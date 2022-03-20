package com.example.agendamaster.ui.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.room.Room
import coil.imageLoader
import com.example.agendamaster.R
import com.example.agendamaster.database.AppDatabase
import com.example.agendamaster.database.AppDatabase.Companion.instancia
import com.example.agendamaster.databinding.ActivityFormularioContatoBinding
import com.example.agendamaster.model.Contato

class FormularioContatoActivity :
    AppCompatActivity(R.layout.activity_formulario_contato) {


    private val binding by lazy {
        ActivityFormularioContatoBinding.inflate(layoutInflater)

    }

    private var idContato = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()

        intent.getParcelableExtra<Contato>(CHAVE_CONTATO)?.let { contatoCarregado ->
            idContato = contatoCarregado.id
            binding.activityFormularioContatoNome
                .setText(contatoCarregado.nome)
            binding.activityFormularioContatoEmail
                .setText(contatoCarregado.email)
            binding.activityFormularioContatoTelefone
                .setText(contatoCarregado.telefone)
        }
    }

    private fun configuraBotaoSalvar() {

        val botaoSalvar = binding.activityFormularioContatoButtonSalvar
        val db = AppDatabase.instancia(this)
        val contatoDao = db.contatoDao ()
        botaoSalvar.setOnClickListener {
            val contatoNovo = criaContato()
            if (idContato > 0){
                contatoDao.atualiza(contatoNovo)
            }else{
                contatoDao.salva(contatoNovo)
            }
            finish()
        }
    }
            private fun criaContato(): Contato {

                val campoNome = binding.activityFormularioContatoNome
                val nome = campoNome.text.toString()
                val campoEmail = binding.activityFormularioContatoEmail
                val email = campoEmail.text.toString()
                val campoTelefone = binding.activityFormularioContatoTelefone
                val telefone = campoTelefone.text.toString()


                return Contato(
                    id = idContato,
                    nome = nome,
                    email = email,
                    telefone = telefone,
                )
            }

}



