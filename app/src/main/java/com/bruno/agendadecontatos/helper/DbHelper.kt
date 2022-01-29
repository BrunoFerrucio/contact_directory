package com.bruno.agendadecontatos.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DbHelper(context: Context) : SQLiteOpenHelper(context, NOME_DB,null, VERSION) {

    override fun onCreate(db: SQLiteDatabase) {

        val sql = "CREATE TABLE IF NOT EXISTS " + TABELA_CONTATOS +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT NOT NULL, " +
                " email TEXT NOT NULL, " +
                " telefone TEXT NOT NULL); "

        try {
            db.execSQL(sql)
            Log.i("INFO_DB","Sucesso ao criar a tabela")
        }catch (e: Exception){
            Log.i("INFO_DB","Erro ao criar a tabela" + e.message)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    companion object{

        val VERSION = 1
        val NOME_DB = "DB_CONTATOS"
        val TABELA_CONTATOS = "contatos"

    }
}