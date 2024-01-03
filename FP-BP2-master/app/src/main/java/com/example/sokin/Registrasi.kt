package com.example.sokin

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class Registrasi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrasi)

        val edt_email:EditText = findViewById(R.id.edt_email)
        val edt_nama:EditText = findViewById(R.id.edt_nama)
        val edt_pass:EditText = findViewById(R.id.edt_pass)
        val btn_simpan:Button = findViewById(R.id.btn_login)

        btn_simpan.setOnClickListener {
            val isi_email:String = edt_email.text.toString()
            val isi_nama:String = edt_nama.text.toString()
            val isi_pass:String = edt_pass.text.toString()

            val db: SQLiteDatabase = openOrCreateDatabase("db_sokin", MODE_PRIVATE, null)
            val eksekutor = db.rawQuery("INSERT INTO user (email_user,password_user,nama_user) VALUES ('$isi_email','$isi_pass','$isi_nama')", null)
            eksekutor.moveToNext()
            val simpan:Intent = Intent(this, Login::class.java)
            startActivity(simpan)
        }

        val SignIn:TextView = findViewById(R.id.txt_signin)
        SignIn.setOnClickListener {
            val pergi:Intent = Intent(this, Login::class.java)
            startActivity(pergi)
        }

        val kembali: LinearLayout = findViewById(R.id.btn_kembali)
        kembali.setOnClickListener {
            val pindah: Intent = Intent(this, Halaman2::class.java)
            startActivity(pindah)
        }
    }
}