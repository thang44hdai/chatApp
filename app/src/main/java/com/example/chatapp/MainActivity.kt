package com.example.chatapp

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.chatapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnsignup.setOnClickListener{
            open_dialog()
        }

        binding.btnsignin.setOnClickListener(){
            sign_in()

        }
    }
    fun open_dialog(){
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
            val email = btnacc.text.toString()
            val pass = btnpass.text.toString()
            val x = member(email, pass)

            database = FirebaseDatabase.getInstance().getReference(name)
            database.setValue(x)

            firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(){
                if(it.isSuccessful){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
    fun sign_in(){
        val email = binding.edtaccount.text.toString()
        val pass = binding.edtpass.text.toString()
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(){
            if(it.isSuccessful){
                val intent = Intent(this, screen2::class.java)
                startActivity(intent)
            }
        }
    }
}