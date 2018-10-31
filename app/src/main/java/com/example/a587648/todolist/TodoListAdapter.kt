package com.example.a587648.todolist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.todo_item_list.view.*
import java.nio.file.Files.delete



class TodoListAdapter(val context: Context, val todos: List<Todo>)
    : RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    //salva a função do clique no item
    var clickListener: ((index: Int) -> Unit)? = null
    var doneListener: ((index: Int) -> Unit)? = null


    //método responsável por inflar as views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item_list, parent, false)
        return ViewHolder(view, todos)
    }

    //retorna a quantidade de itens na lista
    override fun getItemCount(): Int {
        return todos.size
    }

    //popula o ViewHolder com as informações do todo
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(context, todos[position], clickListener, doneListener)
    }

    //configuração a função de clique nos itens
    fun setOnItemClickListener(clique: ((index: Int) -> Unit)){
        this.clickListener = clique
    }

    //configuração a função de clique nos itens
    fun setOnDoneClickListener(clique: ((index: Int) -> Unit)){
        this.doneListener = clique
    }

    //referência para a view de cada item da lista
    class ViewHolder(itemView: View, todos: List<Todo>) : RecyclerView.ViewHolder(itemView) {

        fun bindView(context:Context, todo: Todo, clickListener: ((index: Int) -> Unit)?, doneListener: ((index: Int) -> Unit)?) {
            itemView.textTodo.text = todo.texto

            if(clickListener != null) {
                itemView.setOnClickListener {
                    clickListener.invoke(adapterPosition)
                }
            }

            if(doneListener != null) {
                itemView.btnDone.setOnClickListener {
                    doneListener.invoke(adapterPosition)
                }
            }
        }

    }
}