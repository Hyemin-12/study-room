package com.example.roomstudy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProjectAdapter(val context: Context, val cats: List<Project>) :
    RecyclerView.Adapter<ProjectAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return cats.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(cats[position])
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val image = itemView?.findViewById<ImageView>(R.id.item_image)
        val title = itemView?.findViewById<TextView>(R.id.item_title)
        val currentNum = itemView?.findViewById<TextView>(R.id.item_current_num)
        val totalNum = itemView?.findViewById<TextView>(R.id.item_total_num)
        val currentAmount = itemView?.findViewById<TextView>(R.id.item_current_amount)
        val totalAmount = itemView?.findViewById<TextView>(R.id.item_total_amount)

        var images = intArrayOf(R.drawable.cat)

        fun bind(project: Project) {
            image?.setImageResource(images[0])
            title?.text = project.title
            currentNum?.text = project.currentNum.toString()
            totalNum?.text = project.totalNum.toString()
            currentAmount?.text = project.currentAmount.toString()
            totalAmount?.text = project.totalAmount.toString()
        }
    }
}