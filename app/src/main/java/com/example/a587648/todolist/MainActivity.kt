package com.example.a587648.todolist

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


//meme

class MainActivity : AppCompatActivity() {
    companion object {
        private const val REQUEST_CADASTRO: Int = 1 //para executar o cadastro de todo
        private const val LISTA = "ListaToDos" //para salvar e restaurar a lista quando necessário
    }

    //lista para armazenar to-do's adicionados
    var listaTodos: MutableList<String> = mutableListOf()
    //indice para verificar se algum to-do' foi clicado
    var indexToDoClicado: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddTodo.setOnClickListener(){
            val cadastrarToDo = Intent(this,CadastraToDoActivity::class.java)
            startActivityForResult(cadastrarToDo, REQUEST_CADASTRO)
        }
    }

    //carrega a lista sempre que a activity é atualizada
    override fun onResume() {
        super.onResume()
        carregaLista()
    }

    //recebe o to-do' da tela de cadastro e o adiciona na lista
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CADASTRO && resultCode == Activity.RESULT_OK){
            val todo: String? = data?.getSerializableExtra(CadastraToDoActivity.TODO) as String
            //caso algum item tenha sido clicado seus dados são alterados, caso não adiciona um novo
            if (todo != null) {
                if(indexToDoClicado >= 0){
                    listaTodos.set(indexToDoClicado, todo)
                    indexToDoClicado = -1
                }else {
                    listaTodos.add(todo)
                }
            }
        }

    }

    //salva a lista caso o Android venha a destruir a activity
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putSerializable(LISTA, listaTodos as ArrayList<String>)
    }

    //restaura a lista caso o Android venha a destruir a activity
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        if(savedInstanceState != null)
            listaTodos = savedInstanceState.getSerializable(LISTA) as MutableList<String>
    }

    //configura os componentes necesário para utilizar a RecyclerView
    fun carregaLista() {
        val adapter = TodoListAdapter(this, listaTodos)

        //configura o clique em cada item do RecyclerView
        adapter.setOnItemClickListener { todo, indexToDoClicado ->
            this.indexToDoClicado = indexToDoClicado
            val editaContatinho = Intent(this, CadastraToDoActivity::class.java)
            editaContatinho.putExtra(CadastraToDoActivity.TODO, todo)
            this.startActivityForResult(editaContatinho, REQUEST_CADASTRO)
        }
        
        adapter.setOnDoneClickListener { indexToDoClicado ->
            this.indexToDoClicado = indexToDoClicado
            listaTodos.removeAt(indexToDoClicado)
            this.indexToDoClicado = -1
            carregaLista()
        }

        val layoutManager = LinearLayoutManager(this)

        rvToDos.adapter = adapter
        rvToDos.layoutManager = layoutManager
    }
}
