package com.example.chatapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityMainBinding
import com.example.chatapp.`object`.member
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnsignup.setOnClickListener{
            open_dialog_to_register()
        }

        binding.btnsignin.setOnClickListener(){
            sign_in()
        }
    }
    fun open_dialog_to_register(){
        val dialog = AlertDialog.Builder(this)
        val dialog_view = layoutInflater.inflate(R.layout.dialog_sign_up, null)
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
            firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this){
                if(it.isSuccessful){
                    val x = member(firebaseAuth.currentUser?.uid!!, name, email, pass)

                    database = FirebaseDatabase.getInstance().getReference()
                    database.child("user").child(firebaseAuth.currentUser?.uid!!).setValue(x)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
    fun sign_in(){
        val email = binding.edtaccount.text.toString()
        val pass = binding.edtpass.text.toString()
        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(){
                if (it.isSuccessful) {
                    val intent = Intent(this, ListFriendActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this, "Sai tài khoản và mật khẩu", Toast.LENGTH_SHORT).show()
                }
        }
    }
//    override fun onRestart() {
//        super.onRestart()
//        binding.edtaccount.setText("")
//        binding.edtpass.setText("")
//    }
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(item.itemId == R.id.logout){
//            firebaseAuth.signOut()
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }
}