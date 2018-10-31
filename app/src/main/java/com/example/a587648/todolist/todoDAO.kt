package com.example.a587648.todolist

import android.arch.persistence.room.*


/**
 * Created by 587648 on 31/10/18.
 */
@Dao
interface todoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: Todo)

    @Query("SELECT * FROM todo")
    fun getAll(): List<Todo>

//    @Update
//    fun update(contatinho: Contatinho)

    @Delete
    fun delete(todo: Todo)


}