package com.example.roomstudy

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Project::class], version = 1)
abstract class ProjectDB: RoomDatabase() {
    abstract fun projectDAO(): ProjectDAO

    companion object {
        private var INSTANCE: ProjectDB? = null

        fun getInstance(context: Context): ProjectDB? {
            if (INSTANCE == null) {
                synchronized(ProjectDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ProjectDB::class.java, "project.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}