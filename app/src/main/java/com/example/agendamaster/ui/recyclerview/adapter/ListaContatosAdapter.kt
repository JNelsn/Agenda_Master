package com.example.agendamaster.ui.recyclerview.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agendamaster.R
import com.example.agendamaster.databinding.ContatoItemBinding
import com.example.agendamaster.model.Contato
import com.example.agendamaster.ui.activity.DetalhesContatoActivity

class ListaContatosAdapter(

    private val context: Context,
    contatos:List<Contato> = emptyList(),
    var quandoClicaNoItemListener : (contato: Contato) -> Unit = {}


) : RecyclerView.Adapter<ListaContatosAdapter.ViewHolder>() {

    private val contatos = contatos.toMutableList()


    inner class ViewHolder(private val binding: ContatoItemBinding) :
        RecyclerView.ViewHolder(binding.root){

        private lateinit var contato: Contato

        init {
            itemView.setOnClickListener {
                Log.i("ListaContatosAdapter", "clicando no item")
                if(::contato.isInitialized){
                    quandoClicaNoItemListener(contato)

                }

            }
        }
        fun vincula(contato: Contato) {

            this.contato = contato
            val nome  = binding.contatoItemNome
            nome.text = contato.nome
            val email = binding.contatoItemEmail
            email.text = contato.email
            val telefone = binding.contatoItemTelefone
            telefone.text = contato.telefone

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ContatoItemBinding.inflate(inflater, parent, false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contato = contatos[position]
        holder.vincula(contato)
    }

    override fun getItemCount(): Int = contatos.size


    fun atualiza(contatos: List<Contato>) {
        this.contatos.clear()
        this.contatos.addAll(contatos)
        notifyDataSetChanged()

    }

}




