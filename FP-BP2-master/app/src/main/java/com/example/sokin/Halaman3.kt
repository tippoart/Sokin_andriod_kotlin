package com.example.sokin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class Halaman3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.halaman3)

        val pindah: LinearLayout = findViewById(R.id.btn_lanjut)
        pindah.setOnClickListener {
            val pindah: Intent = Intent(this, Login::class.java)
            startActivity(pindah)
        }
        val kembali:LinearLayout = findViewById(R.id.btn_kembali)
        kembali.setOnClickListener {
            val pindah:Intent = Intent(this, Halaman2::class.java)
            startActivity(pindah)
        }
    }
}