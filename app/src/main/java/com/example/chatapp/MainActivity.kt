package com.example.chatapp

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.chatapp.databinding.ActivityMainBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnsignup.setOnClickListener{
            dialog()
        }

    }
    fun dialog(){
        val dialog = AlertDialog.Builder(this)
        val dialog_view = layoutInflater.inflate(R.layout.dialog, null)
        dialog.setTitle("Register")
        dialog.setView(dialog_view)
        dialog.create().show()
        val btnregister = dialog_view.findViewById<Button>(R.id.btnregister)
        val btnacc = dialog_view.findViewById<EditText>(R.id.edtaccount)
        val btnpass = dialog_view.findViewById<EditText>(R.id.edtpass)
        val btnname = dialog_view.findViewById<EditText>(R.id.edtname)
        btnregister.setOnClickListener() {

            val name = btnname.text.toString()
            val acc = btnacc.text.toString()
            val pass = btnpass.text.toString()
            val x = member(acc, pass)

            database = FirebaseDatabase.getInstance().getReference(name)
            database.setValue(x)

            btnacc.setText("")
            btnname.setText("")
            btnpass.setText("")
        }
    }
}