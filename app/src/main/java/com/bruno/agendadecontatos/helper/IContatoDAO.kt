package com.bruno.agendadecontatos.helper

import com.bruno.agendadecontatos.model.Contato

interface IContatoDAO {

    fun salvar(contato: Contato): Boolean
    fun deletar(contato: Contato): Boolean
    fun listar(): List<Contato>

}