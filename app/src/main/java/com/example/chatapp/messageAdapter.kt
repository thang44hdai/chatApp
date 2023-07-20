package com.example.chatapp

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class messageAdapter : RecyclerView.Adapter<messageAdapter.viewHolder>() {
    inner class viewHolder(view: View) : RecyclerView.ViewHolder(view)

    class sendViewHolder() : RecyclerView.ViewHolder(){

    }
    class receiveViewHolder() : RecyclerView.ViewHolder(){
        
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

    }

    override fun getItemCount(): Int {

    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

    }
}