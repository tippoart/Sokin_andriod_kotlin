package com.example.sokin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class Halaman2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.halaman2)

        val lanjut:LinearLayout = findViewById(R.id.btn_lanjut)
        lanjut.setOnClickListener {
            val pindah:Intent = Intent(this, Halaman3::class.java)
            startActivity(pindah)
        }
    }
}