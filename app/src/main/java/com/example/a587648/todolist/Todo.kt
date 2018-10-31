package com.example.a587648.todolist

import android.arch.persistence.room.Entity
import java.sql.Date
import java.util.*
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity
data class Todo (
                var texto: String,
//                var dataSalva: java.util.Date,
                @PrimaryKey(autoGenerate = true)
                var id: Int = 0): Serializable