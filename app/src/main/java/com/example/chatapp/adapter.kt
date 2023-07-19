package com.example.chatapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class adapter(val ds: ArrayList<member>): RecyclerView.Adapter<adapter.viewholder>() {
    inner class viewholder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return viewholder(view)
    }

    override fun getItemCount(): Int {
        return ds.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val btnname = holder.itemView.findViewById<Button>(R.id.btnname)
        btnname.setText(ds[position].name)
        btnname.setOnClickListener(){

        }
    }
}