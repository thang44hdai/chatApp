package com.example.chatapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.`object`.message
import com.google.firebase.auth.FirebaseAuth

class messageAdapter(val context: Context, val ds: ArrayList<message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class sendViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvsend = itemView.findViewById<TextView>(R.id.tvsend)
    }
    class receiveViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvreceive = itemView.findViewById<TextView>(R.id.tvreceive)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == 1){
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.receive, parent, false)
            return receiveViewHolder(view)
        } else {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.send, parent, false)
            return sendViewHolder(view)
        }
    }
    override fun getItemViewType(position: Int): Int {
        val currentMessage = ds[position]
        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderID)){
            return 1 // receive
        } else {
            return 2 // send
        }
    }
    override fun getItemCount(): Int {
        return ds.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = ds[position]
        if(holder.javaClass == sendViewHolder::class.java){
            val viewHolder = holder as sendViewHolder
            holder.tvsend.text = currentMessage.message
        }
        else{
            val viewHolder = holder as receiveViewHolder
            holder.tvreceive.text = currentMessage.message
        }
    }
}