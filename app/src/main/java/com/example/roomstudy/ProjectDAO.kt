package com.example.roomstudy

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

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