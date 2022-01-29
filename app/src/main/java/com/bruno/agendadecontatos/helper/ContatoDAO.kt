package com.bruno.agendadecontatos.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.bruno.agendadecontatos.model.Contato
import java.lang.Exception

class ContatoDAO(context: Context) : IContatoDAO {

    private val escreve: SQLiteDatabase
    private val le: SQLiteDatabase

    override fun salvar(contato: Contato): Boolean {

        val cv = ContentValues()
        cv.put("nome",contato?.nomeContato)
        cv.put("email",contato?.emailContato)
        cv.put("telefone",contato?.telefoneContato)

        try {
            //Salvar os dados

            escreve.insert(DbHelper.TABELA_CONTATOS,null,cv)
            Log.i("INFO","contato salvo com sucesso!")

        }catch (e: Exception){
            Log.i("INFO","erro ao salvar o contato" + e.message)
            return false
        }

        return true
    }

    override fun deletar(contato: Contato): Boolean {

        try{
            var args = arrayOf(contato.id.toString())
            escreve.delete(DbHelper.TABELA_CONTATOS,"id=?",args)
            Log.i("INFO_DELETE","Contato removido com sucesso!")

        }catch (e: Exception){
            Log.i("INFO_DELETE","Erro ao remover um contato" + e.message)
            return false
        }

        return true
    }

    override fun listar(): MutableList<Contato> {

        val contatos: MutableList<Contato> = ArrayList()

        val sql = "SELECT * FROM " + DbHelper.TABELA_CONTATOS + ";"

        val cursor = le.rawQuery(sql,null)

        while (cursor.moveToNext()){
            val contato = Contato()
            val id = cursor.getLong(cursor.getColumnIndex("id"))
            val nomeContato = cursor.getString(cursor.getColumnIndex("nome"))
            val emailContato = cursor.getString(cursor.getColumnIndex("email"))
            val telefoneContato = cursor.getString(cursor.getColumnIndex("telefone"))

            contato.id = id
            contato.nomeContato = nomeContato
            contato.emailContato = emailContato
            contato.telefoneContato = telefoneContato
            contatos.add(contato)

        }

        return contatos

    }

    init {
        val db = DbHelper(context)
        escreve = db.writableDatabase
        le = db.readableDatabase
    }

}