package com.example.roomstudy

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface ProjectDAO {
    @Query("SELECT * FROM project")
    fun getAll(): List<Project>

    /* import android.arch.persistence.room.OnConflictStrategy.REPLACE */
    @Insert(onConflict = REPLACE)
    fun insert(cat: Project)

    @Query("DELETE from project")
    fun deleteAll()
}