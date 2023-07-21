package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.adapter.messageAdapter
import com.example.chatapp.databinding.ActivityChatBinding
import com.example.chatapp.`object`.message
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class chatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var messageAdapter: messageAdapter
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var data: DatabaseReference
    lateinit var senderRoom: String
    lateinit var receiverRoom: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rcvchat.layoutManager = LinearLayoutManager(this)

        var intent = Intent()
        var name = intent.getStringExtra("name")
        var receiverUid = intent.getStringExtra("uid")

        var senderUid = FirebaseAuth.getInstance().currentUser?.uid
        senderRoom = receiverUid + senderUid
        receiverRoom = senderUid + receiverUid

        data = FirebaseDatabase.getInstance().getReference()
        val ds : ArrayList<message> = ArrayList()
        // set adapter
        messageAdapter = messageAdapter(this, ds)
        binding.rcvchat.adapter = messageAdapter

        //add message to box
        data.child("chats").child(senderRoom!!).child("messages").addValueEventListener(object: ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                ds.clear()
                for(post in snapshot.children){
                    var mess = post.getValue(message::class.java)
                    ds.add(mess!!)
                }
                messageAdapter.notifyDataSetChanged()

            }
            override fun onCancelled(error: DatabaseError) {
            }

        })
        binding.btnsend.setOnClickListener(){
            val mess = binding.edtmessage.text.toString()
            val messObject = message(mess, senderUid)
            data.child("chats").child(senderRoom!!).child("messages").push().setValue(messObject).addOnSuccessListener {
                data.child("chats").child(receiverRoom!!).child("messages").push().setValue(messObject)
            }
            binding.edtmessage.setText("")
        }
    }
}