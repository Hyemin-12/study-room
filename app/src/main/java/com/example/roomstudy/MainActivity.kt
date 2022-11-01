package com.example.roomstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var projectDB : ProjectDB? = null
    private var projectList = listOf<Project>()
    lateinit var mAdapter : ProjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        projectDB = ProjectDB.getInstance(this)
        mAdapter = ProjectAdapter(this, projectList)

        val r = Runnable {
            try {
                projectList = projectDB?.projectDAO()?.getAll()!!
                mAdapter = ProjectAdapter(this, projectList)
                mAdapter.notifyDataSetChanged()

                recyclerView.adapter = mAdapter
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.setHasFixedSize(true)
            } catch (e: Exception) {
                Log.d("tag", "Error - $e")
            }
        }

        var Adapter = ProjectAdapter(this, projectList)
        Adapter.notifyDataSetChanged()
        recyclerView.adapter = Adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val thread = Thread(r)
        thread.start()

        var mAddBtn = findViewById<Button>(R.id.add_btn)
        mAddBtn.setOnClickListener {
            val intent = Intent(this, AddProjectActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        ProjectDB.destroyInstance()
        projectDB = null
        super.onDestroy()
    }
}