package com.example.chatapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

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
        val btnname = holder.itemView.findViewById<Button>(R.id.btnname)
        val currentMem = ds[position]
        btnname.setText(currentMem.name)
        btnname.setOnClickListener(){
            val intent = Intent(context, chatActivity::class.java)
            intent.putExtra("name", currentMem.name)
            intent.putExtra("uid", currentMem.uid)
            context.startActivity(intent)
        }
    }
}