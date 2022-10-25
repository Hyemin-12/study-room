package com.example.roomstudy

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "project")
class Project(@PrimaryKey(autoGenerate = true) var id: Long?,
              @ColumnInfo(name = "project-title") var title: String?,
              @ColumnInfo(name = "current-number") var currentNum: Int,
              @ColumnInfo(name = "total-number") var totalNum: Int,
              @ColumnInfo(name = "current-amount") var currentAmount: Int,
              @ColumnInfo(name = "total-amount") var totalAmount: Int,
              @ColumnInfo(name = "deadline") var deadline: Int
){
    constructor(): this(null,"", 0,0, 0, 0, 0)
}