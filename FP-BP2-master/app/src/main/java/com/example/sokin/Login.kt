package com.example.sokin

import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlin.math.log

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val edt_email:EditText = findViewById(R.id.edt_email)
        val edt_password:EditText = findViewById(R.id.edt_pass)
        val btn_login:Button = findViewById(R.id.btn_login)

        btn_login.setOnClickListener {
            val isi_email:String = edt_email.text.toString()
            val isi_password:String = edt_password.text.toString()

            val db: SQLiteDatabase = openOrCreateDatabase("db_sokin", MODE_PRIVATE, null)
            val query = db.rawQuery("SELECT * FROM user WHERE email_user='$isi_email' AND password_user='$isi_password'", null)
            val cek = query.moveToNext()

            Log.d("eek", cek.toString())

            if (cek) {
                val id = query.getString(0)
                val email = query.getString(1)
                val password = query.getString(2)
                val nama = query.getString(3)

                val session:SharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
                val buattiket = session.edit()
                buattiket.putString("id_user", id)
                buattiket.putString("email_user", email)
                buattiket.putString("password_user", password)
                buattiket.putString("nama_user", nama)
                buattiket.commit()

                val benar:Intent = Intent(this, Dashboard::class.java)
                startActivity(benar)

            }else {
                Toast.makeText(this, "Email atau password salah!", Toast.LENGTH_LONG).show()
            }
        }

        val register:TextView = findViewById(R.id.txt_register)
        register.setOnClickListener {
            val pindah: Intent = Intent(this, Registrasi::class.java)
            startActivity(pindah)
        }

        val kembali: LinearLayout = findViewById(R.id.btn_kembali)
        kembali.setOnClickListener {
            val pindah: Intent = Intent(this, Halaman2::class.java)
            startActivity(pindah)
        }
    }
}