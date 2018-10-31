package com.example.a587648.todolist

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable


/**
 * Created by 587648 on 31/10/18.
 */
@Entity
data class Todo (
        var texto: String,
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0)
    : Serializable
