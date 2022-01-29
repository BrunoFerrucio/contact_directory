package com.bruno.agendadecontatos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bruno.agendadecontatos.R
import com.bruno.agendadecontatos.model.Contato
import kotlinx.android.synthetic.main.lista_contatos.view.*

class ContatoAdapter (private val listaContatos: MutableList<Contato>) : RecyclerView.Adapter<ContatoAdapter.ContatosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lista_contatos,parent,false)
        return ContatosViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContatosViewHolder, position: Int) {
      holder.bind(listaContatos[position])
    }

    override fun getItemCount(): Int = listaContatos.size


    inner class ContatosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
      fun bind(listaContatos: Contato){

          itemView.textNome.text = listaContatos.nomeContato
          itemView.textEmail.text = listaContatos.emailContato
          itemView.textTelefone.text = listaContatos.telefoneContato

      }
    }
}