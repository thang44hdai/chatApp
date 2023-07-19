package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.databinding.ActivityMainBinding
import com.example.chatapp.databinding.ActivityScreen2Binding
import com.google.firebase.database.*

class screen2 : AppCompatActivity() {
    private lateinit var binding: ActivityScreen2Binding
    private lateinit var database: DatabaseReference
    private lateinit var adapter: adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreen2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rcvlist.layoutManager = LinearLayoutManager(this)
        val ds: ArrayList<member> = ArrayList()

        adapter = adapter(ds)
        binding.rcvlist.adapter = adapter

        database = FirebaseDatabase.getInstance().getReference()
        database.child("user").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                ds.clear()
                for(mem in snapshot.children){
                    val id = mem.getValue(member::class.java)
                    if(id!=null)
                        ds.add(id)
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }

        })
//


    }

    override fun onRestart() {
        super.onRestart()
        val ds: ArrayList<member> = ArrayList()
        database = FirebaseDatabase.getInstance().getReference()
        database.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                ds.clear()
                for(mem in snapshot.children){
                    val id = mem.getValue(member::class.java)
                    ds.add(id!!)
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }

        })
        adapter = adapter(ds)
        binding.rcvlist.adapter = adapter
    }
}