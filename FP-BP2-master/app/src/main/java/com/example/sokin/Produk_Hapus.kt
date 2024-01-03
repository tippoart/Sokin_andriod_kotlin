package com.example.sokin

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Produk_Hapus : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.produk_hapus)

        val id_produk:String = intent.getStringExtra("id_produk").toString()
        val db_sokin: SQLiteDatabase = openOrCreateDatabase("db_sokin", MODE_PRIVATE, null)
        val query = db_sokin.rawQuery("DELETE FROM produk WHERE id_produk='$id_produk'", null)
        query.moveToNext()

        val pindah:Intent = Intent(this, Produk::class.java)
        startActivity(pindah)
    }
}