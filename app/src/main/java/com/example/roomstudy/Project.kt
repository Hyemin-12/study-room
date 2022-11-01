package com.example.roomstudy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "project")
class Project(@PrimaryKey(autoGenerate = true) var id: Long?,
              @ColumnInfo(name = "project-title") var title: String?,
              @ColumnInfo(name = "current-number") var currentNum: Int,
              @ColumnInfo(name = "total-number") var totalNum: Int,
              @ColumnInfo(name = "current-amount") var currentAmount: Int,
              @ColumnInfo(name = "total-amount") var totalAmount: Int,
              @ColumnInfo(name = "deadline") var deadline: Int
){
    constructor(): this(null,"", 0,0, 5, 10, 20221101)
}