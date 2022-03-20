package com.example.agendamaster.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.agendamaster.database.AppDatabase
import com.example.agendamaster.databinding.ActivityListaContatosBinding
import com.example.agendamaster.model.Contato
import com.example.agendamaster.ui.recyclerview.adapter.ListaContatosAdapter

class ListaContatosActivity : AppCompatActivity() {

    private val adapter = ListaContatosAdapter(this)
    private val binding by lazy {
        ActivityListaContatosBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()

    }

    override fun onResume() {
        super.onResume()
        val db = AppDatabase.instancia(this)
        val contatoDao = db.contatoDao()
        adapter.atualiza(contatoDao.buscaTodos())
    }

    private fun configuraFab() {
        val fab = binding.activityListaContatosFab
        fab.setOnClickListener {
            vaiParaFormularioContato()
        }
    }

    private fun vaiParaFormularioContato() {
        val intent = Intent(this, FormularioContatoActivity::class.java)
        startActivity(intent)
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.activityListaContatosRecyclerview
        recyclerView.adapter = adapter
        adapter.quandoClicaNoItemListener ={
            val detalhesContato = Intent (this, DetalhesContatoActivity::class.java
            ).apply {
                putExtra(CHAVE_CONTATO, it)
            }
            startActivity(detalhesContato)

            Log.i("ListaContatosActivity", "quandoClica: ${it.nome} ")
        }
    }
}