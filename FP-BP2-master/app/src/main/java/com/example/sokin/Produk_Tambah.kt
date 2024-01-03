package com.example.sokin

import android.app.Activity
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import java.io.ByteArrayOutputStream

class Produk_Tambah : AppCompatActivity() {
    var iv_upload:ImageView? = null
    var urlfoto:Uri? = null
    var bitmapfoto:Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.produk_tambah)

        val edt_kategori:EditText = findViewById(R.id.edt_kategori)
        val edt_produk:EditText = findViewById(R.id.edt_produk)
        val edt_harga:EditText = findViewById(R.id.edt_harga)
        val btn_menambah:Button = findViewById(R.id.btn_menambah)

        iv_upload = findViewById(R.id.iv_upload)

        iv_upload?.setOnClickListener{
            val bukagaleri:Intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            pilih_foto.launch(bukagaleri)
        }

        btn_menambah.setOnClickListener {
            val isi_kategori:String = edt_kategori.text.toString()
            val isi_produk:String = edt_produk.text.toString()
            val isi_harga:String = edt_harga.text.toString()

            val bos = ByteArrayOutputStream()
            bitmapfoto?.compress(Bitmap.CompressFormat.JPEG,100,bos)
            val bytearrayfoto = bos.toByteArray()

            val db_sokin:SQLiteDatabase = openOrCreateDatabase("db_sokin", MODE_PRIVATE, null)
            val sql = "INSERT INTO produk (kategori, nama_produk, harga, foto) VALUES (?,?,?,?)"
            val statement = db_sokin.compileStatement(sql)
            statement.clearBindings()
            statement.bindString(1,isi_kategori)
            statement.bindString(2,isi_produk)
            statement.bindString(3,isi_harga)
            statement.bindBlob(4,bytearrayfoto)
            statement.executeInsert()

            val pindah:Intent = Intent(this, Produk::class.java)
            startActivity(pindah)
        }
    }
    val pilih_foto = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            val fotodiperoleh = it.data
            if (fotodiperoleh != null){
                urlfoto = fotodiperoleh.data

                bitmapfoto = MediaStore.Images.Media.getBitmap(contentResolver, urlfoto)
                iv_upload?.setImageBitmap(bitmapfoto)
            }
        }
    }
}