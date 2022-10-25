package com.example.roomstudy

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

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