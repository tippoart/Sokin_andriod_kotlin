package com.example.sokin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Halaman1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.halaman1)

        val start:Button = findViewById(R.id.btn_started)
        start.setOnClickListener {
            val pindah:Intent = Intent(this, Halaman2::class.java)
            startActivity(pindah)
        }
    }
}