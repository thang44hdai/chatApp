package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatapp.databinding.ActivityMainBinding
import com.example.chatapp.databinding.ActivityScreen2Binding

class screen2 : AppCompatActivity() {
    private lateinit var binding: ActivityScreen2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreen2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}