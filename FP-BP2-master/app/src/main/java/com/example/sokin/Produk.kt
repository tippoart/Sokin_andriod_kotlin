package com.example.sokin

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.ByteArrayInputStream

class Produk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.produk)

        val iv_back: ImageView = findViewById(R.id.iv_back)
        iv_back.setOnClickListener {
            val pindah: Intent = Intent(this, Dashboard::class.java)
            startActivity(pindah)
        }

        val id:MutableList<String> = mutableListOf()
        val kategori:MutableList<String> = mutableListOf()
        val produk:MutableList<String> = mutableListOf()
        val harga:MutableList<String> = mutableListOf()
        val foto:MutableList<Bitmap?> = mutableListOf()
        val rv_produk:RecyclerView = findViewById(R.id.rv_produk)

        val db_sokin:SQLiteDatabase = openOrCreateDatabase("db_sokin", MODE_PRIVATE, null)
        val ambil_produk = db_sokin.rawQuery("SELECT * FROM produk", null)
        while (ambil_produk.moveToNext()){
            try {
                val bos = ByteArrayInputStream(ambil_produk.getBlob(4))
                val fotobitmap:Bitmap = BitmapFactory.decodeStream(bos)
                foto.add(fotobitmap)
            }catch (e:Exception){
                val fotobitmap:Bitmap = BitmapFactory.decodeResource(this.resources,R.drawable.no_image)
                foto.add(fotobitmap)
            }

            id.add(ambil_produk.getString(0))
            kategori.add(ambil_produk.getString(1))
            produk.add(ambil_produk.getString(2))
            harga.add(ambil_produk.getString(3))
        }

        val pk = Produk_Item(this, id, kategori, produk, harga, foto)
        rv_produk.adapter = pk
        rv_produk.layoutManager = LinearLayoutManager(this)

        val btn_tambah:Button = findViewById(R.id.btn_tambah)
        btn_tambah.setOnClickListener {
            val pindah:Intent = Intent(this, Produk_Tambah::class.java)
            startActivity(pindah)
        }
    }
}