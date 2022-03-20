package com.example.agendamaster.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.agendamaster.R
import com.example.agendamaster.database.AppDatabase
import com.example.agendamaster.databinding.ActivityDetalhesContatoBinding
import com.example.agendamaster.model.Contato

class DetalhesContatoActivity : AppCompatActivity() {

    private var contatoId: Long? = null
    private  var contato: Contato? = null
    private val binding by lazy {
        ActivityDetalhesContatoBinding.inflate(layoutInflater)
    }


    val contatoDao by lazy {
        AppDatabase.instancia(this).contatoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarContato()
    }

    override fun onResume() {
        super.onResume()
        contatoId?.let {  id ->
           contato = contatoDao.buscaPorId(id)
        }
        contato?.let {
            preencheCampos(it)
        }?: finish()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_contato, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.contato_remover -> {
                contato?.let {  contatoDao.remove(it) }
                finish()
            }
            R.id.contato_editar -> {
                Intent(this, FormularioContatoActivity::class.java).apply {
                    putExtra(CHAVE_CONTATO, contato)
                    startActivity(this)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tentaCarregarContato() {
        intent.getParcelableExtra<Contato>(CHAVE_CONTATO)?.let { contatoCarregado ->
            contatoId = contatoCarregado.id
        } ?: finish()

    }

    private fun preencheCampos(contatoCarregado: Contato) {
        with(binding) {
            activityDetalhesContatoNome.text = contatoCarregado.nome
            activityDetalhesContatoEmail.text = contatoCarregado.email
            activityDetalhesContatoTelefone.text = contatoCarregado.telefone
        }
    }
}