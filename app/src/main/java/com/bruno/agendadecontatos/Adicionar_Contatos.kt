package com.bruno.agendadecontatos

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.bruno.agendadecontatos.helper.ContatoDAO
import com.bruno.agendadecontatos.model.Contato
import kotlinx.android.synthetic.main.activity_adicionar__contatos.*

class Adicionar_Contatos : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar__contatos)

        supportActionBar!!.hide() //Escondendo a nossa barra de ação
        configuracoesToolbarContato() //Configurações da nossa toolbar

        //Evento de click no botão de salvar
        bt_salvar.setOnClickListener {

            val contatoDAO = ContatoDAO(applicationContext)

            //Vai criar um novo contato
            var nomeContato = txtNome.text.toString()
            var emailContato = txtEmail.text.toString()
            var telefoneContato = txtTelefone.text.toString()

            val contato = Contato()
            contato.nomeContato = nomeContato
            contato.emailContato = emailContato
            contato.telefoneContato = telefoneContato
            contatoDAO.salvar(contato)

            VoltarMainActivity()


        }

    }

    fun VoltarMainActivity(){
        val voltarMainActivity = Intent(this,MainActivity::class.java)
        startActivity(voltarMainActivity)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun configuracoesToolbarContato(){
        toolbar_contato.setTitle("Novo Contato")
        toolbar_contato.setNavigationIcon(getDrawable(R.drawable.ic_voltar))
        toolbar_contato.setTitleMargin(200,0,200,0)
        toolbar_contato.setNavigationOnClickListener {
            caixaDeAlerta()
        }
    }

    fun caixaDeAlerta(){
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Voltar para a Tela Inicial?")
        dialog.setMessage("Se você sair o contato não será salvo.")

        dialog.setPositiveButton("Sim"){dialoginterface, i ->
            VoltarMainActivity()

        }
        dialog.setNegativeButton("Cancelar"){dialoginteraface, i ->

        }
        dialog.create()
        dialog.show()
    }
}