package com.example.sokin

import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        val iv_back: ImageView = findViewById(R.id.iv_back)
        iv_back.setOnClickListener {
            val pindah: Intent = Intent(this, Dashboardd::class.java)
            startActivity(pindah)
        }

        val id:MutableList<String> = mutableListOf()
        val email:MutableList<String> = mutableListOf()
        val password:MutableList<String> = mutableListOf()
        val nama:MutableList<String> = mutableListOf()

        val db_sokin:SQLiteDatabase = openOrCreateDatabase("db_sokin", MODE_PRIVATE,null)

        val ambil_user = db_sokin.rawQuery("SELECT * FROM user",null)

        while (ambil_user.moveToNext())
        {
            id.add(ambil_user.getString(0))
            email.add(ambil_user.getString(1))
            password.add(ambil_user.getString(2))
            nama.add(ambil_user.getString(3))
        }

        val txt_namaUser:TextView = findViewById(R.id.txt_namaUser)
        val txt_emailUser:TextView = findViewById(R.id.txt_emailUser)

        val tiket:SharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        val id_user = tiket.getString("id_user",null).toString()
        val nama_user = tiket.getString("nama_user",null).toString()
        txt_namaUser.text = nama_user

        val email_user = tiket.getString("email_user",null).toString()
        txt_emailUser.text = email_user

        val btn_logout:Button = findViewById(R.id.btn_logout)
        btn_logout.setOnClickListener {
            val edittiket = tiket.edit()
            edittiket.clear()
            edittiket.commit()

            val keluar:Intent = Intent(this, Login::class.java)
            startActivity(keluar)
        }

        val btn_edit:Button = findViewById(R.id.btn_edit)
        btn_edit.setOnClickListener {
            val intent = Intent(this, Profile_Edit::class.java)
            intent.putExtra("id_user", id_user)
            startActivity(intent)
        }
    }
}
