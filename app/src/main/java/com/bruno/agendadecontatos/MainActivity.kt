package com.bruno.agendadecontatos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bruno.agendadecontatos.adapter.ContatoAdapter
import com.bruno.agendadecontatos.helper.ContatoDAO
import com.bruno.agendadecontatos.helper.RecyclerItemClickListener
import com.bruno.agendadecontatos.model.Contato
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var contatoAdapter: ContatoAdapter? = null
    private var listaContatos: MutableList<Contato> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CarregarListaDeContatos()

        recyclerview_contatos.addOnItemTouchListener(RecyclerItemClickListener(applicationContext,recyclerview_contatos,
          object : RecyclerItemClickListener.OnItemClickListener{


              override fun onItemClick(view: View, position: Int) {

                  val contatoSelecionado = listaContatos[position]

                  val dialog = AlertDialog.Builder(this@MainActivity)
                  dialog.setTitle("Confirmar Exclusão?")
                  dialog.setMessage("Deseja excluir o contato: " + contatoSelecionado.nomeContato + " ?")

                  dialog.setPositiveButton("Sim"){dialoginterface, i ->

                      val contatoDAO = ContatoDAO(applicationContext)

                      if (contatoDAO.deletar(contatoSelecionado)){
                          CarregarListaDeContatos()
                          Toast.makeText(applicationContext,"Sucesso ao excluir contato!",Toast.LENGTH_SHORT).show()
                      }else{
                          Toast.makeText(applicationContext,"Erro ao excluir contato!",Toast.LENGTH_SHORT).show()
                      }

                  }
                  dialog.setNegativeButton("Não"){dialoginteraface, i ->

                  }
                  dialog.create()
                  dialog.show()
              }

              override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

              }

              override fun onLongItemClick(view: View, position: Int) {

              }

          }))



       adicionar_contato.setOnClickListener {
          val segundaTela = Intent(this,Adicionar_Contatos::class.java)
           startActivity(segundaTela)
       }

    }

    fun CarregarListaDeContatos(){

        //Listar os contatos
        val contatoDAO = ContatoDAO(applicationContext)
        listaContatos = contatoDAO.listar()

        contatoAdapter = ContatoAdapter(listaContatos)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerview_contatos.layoutManager = layoutManager
        recyclerview_contatos.setHasFixedSize(true)
        recyclerview_contatos.addItemDecoration(DividerItemDecoration(this,LinearLayout.VERTICAL))
        recyclerview_contatos.adapter = contatoAdapter

    }


}