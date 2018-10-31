package com.example.a587648.todolist

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(Todo::class), version = 1)
public abstract class AppDatabase : RoomDatabase(){

    companion object {
        private val DB_NAME = "todo.db"
        private var instance: AppDatabase? = null

        private fun create(context: Context): AppDatabase? {
                        return Room.databaseBuilder(
                                        context,
                                        AppDatabase::class.java,
                                        DB_NAME).build()
    }
        public fun getInstance(context: Context): AppDatabase{
                        if (instance == null)
                                instance = create(context)
                        return instance!!
                    }

            }

        public abstract fun todoDao() : todoDao
}
