package com.example.sokin

import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class Profile_Edit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_edit)

        val id_user:String = intent.getStringExtra("id_user").toString()
        val db_sokin:SQLiteDatabase = openOrCreateDatabase("db_sokin", MODE_PRIVATE,null)
        val ambil = db_sokin.rawQuery("SELECT * FROM user WHERE id_user='$id_user'",null)
        ambil.moveToNext()

        val isi_email:String = ambil.getString(1)
        val isi_password:String = ambil.getString(2)
        val isi_nama:String = ambil.getString(3)

        val edt_emailUser:EditText = findViewById(R.id.edt_emailUser)
        val edt_password:EditText = findViewById(R.id.edt_password)
        val edt_namaUser:EditText = findViewById(R.id.edt_namaUser)
        val btn_simpan:Button = findViewById(R.id.btn_simpan)

        edt_emailUser.setText(isi_email)
        edt_password.setText(isi_password)
        edt_namaUser.setText(isi_nama)

        btn_simpan.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Konfirmasi")
            alertDialogBuilder.setMessage("Yakin ingin merubah data?")

            alertDialogBuilder.setPositiveButton("Ya") { dialog, which ->
                // Logika untuk melakukan perubahan data
                val tiket: SharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
                val email_baru: String = edt_emailUser.text.toString()
                val password_baru: String = edt_password.text.toString()
                val nama_baru: String = edt_namaUser.text.toString()

                val query = db_sokin.rawQuery("UPDATE user SET email_user='$email_baru', password_user='$password_baru', nama_user='$nama_baru' WHERE id_user='$id_user'",null)
                query.moveToNext()

                val edittiket = tiket.edit()
                edittiket.clear()
                edittiket.commit()

                val keluar:Intent = Intent(this, Login::class.java)
                startActivity(keluar)
            }

            alertDialogBuilder.setNegativeButton("Tidak") { dialog, which ->
                // Tidak melakukan apa-apa jika pengguna membatalkan perubahan
            }

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }

    }
}