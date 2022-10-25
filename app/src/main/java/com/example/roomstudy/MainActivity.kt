package com.example.roomstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var projectDB : ProjectDB? = null
    private var projectList = listOf<Project>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        projectDB = ProjectDB.getInstance(this)

        val r = Runnable {
            // 데이터에 읽고 쓸 때 스레드 사용
            projectList = projectDB?.projectDAO()?.getAll()!!
        }

        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        var Adapter = ProjectAdapter(this, projectList)
        Adapter.notifyDataSetChanged()
        recyclerView.adapter = Adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val thread = Thread(r)
        thread.start()
    }
}