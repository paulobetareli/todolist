package com.example.a587648.todolist



import android.arch.persistence.room.*

@Dao
interface todoDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(todo: Todo)

        @Query("SELECT * FROM todo")
        fun getAll(): List<Todo>

    //    @Update
    //    fun update(contatinho: Contatinho)

        @Delete
        fun delete(todo: Todo)

        @Query("SELECT * FROM todo WHERE id = :todoId LIMIT 1")
        fun getTodo(todoId: Int): Todo

        @Query("SELECT * FROM todo WHERE texto like :todoTexto")
        fun findByName(todoTexto: String): List<Todo>
    }
