package com.example.chatapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.chatActivity
import com.example.chatapp.`object`.member

class adapter(val context: Context, val ds: ArrayList<member>): RecyclerView.Adapter<adapter.viewholder>() {
    inner class viewholder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view = LayoutInflater.from(context).inflate(R.layout.listfriend, parent, false)
        return viewholder(view)
    }

    override fun getItemCount(): Int {
        return ds.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val btnname = holder.itemView.findViewById<TextView>(R.id.tvname)
        val currentMem = ds[position]
        btnname.setText(currentMem.name)
        holder.itemView.setOnClickListener(){
            val intent = Intent(context, chatActivity::class.java)
            intent.putExtra("name", currentMem.name)
            intent.putExtra("uid", currentMem.uid)
            context.startActivity(intent)
        }
    }
}