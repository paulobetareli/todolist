package com.example.a587648.todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_cadastro.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by 587648 on 24/10/18.
 */
class CadastraToDoActivity : AppCompatActivity() {

    companion object {
        public const val TODO: String = "ToDo" //para putExtra entre activities
    }

    var todo: Todo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        //verifica se foi passado um todo para alteração
//        val todo: String? = intent.getSerializableExtra(TODO) as String?

        todo = intent.getSerializableExtra(TODO) as Todo?
        if(todo != null){
            carregaDados()
        }

        btnSalvar.setOnClickListener(){
            salvaToDo()

        }
        if (todo == null) {
            todo = Todo(edtTodo.text.toString())
        }else{
            todo?.texto = edtTodo.text.toString()
        }
    }

    
    //cria um objeto todo com os dados fornecidos e retorna seu nome
    //para a ListaContatinhoActivity
    private fun salvaToDo() {

        //verifica se os campos obrigatórios estão preenchidos
        if(edtTodo.text.isEmpty()){
            edtTodo.requestFocus()
            edtTodo.setError(getString(R.string.campo_obrigatorio))
            return
        }



//        val abreLista = Intent(this, MainActivity::class.java)
//        abreLista.putExtra(TODO, todo)
//        setResult(Activity.RESULT_OK, abreLista)
//        finish()
        val todoDao: todoDao = AppDatabase.getInstance(this).todoDao()
        doAsync {
            todoDao.insert(todo!!)
            uiThread {
                finish()
            }
        }

    }

    //exibe as informações do todo na Activity
//    private fun carregaDados(todo: String) {
//        edtTodo.setText(todo)
//    }

    private fun carregaDados(){
        edtTodo.setText(todo?.texto)
    }

}