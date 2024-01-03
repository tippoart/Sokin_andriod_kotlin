package com.example.sokin

import android.app.Activity
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class Produk_Ubah : AppCompatActivity() {
    var iv_upload: ImageView? = null
    var urlfoto: Uri? = null
    var bitmapfoto:Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.produk_ubah)

        val id_produk:String = intent.getStringExtra("id_produk").toString()

        val db_sokin: SQLiteDatabase = openOrCreateDatabase("db_sokin", MODE_PRIVATE,null)
        val ambil = db_sokin.rawQuery("SELECT * FROM produk WHERE id_produk='$id_produk'",null)
        ambil.moveToNext()

        val isi_kategori:String = ambil.getString(1)
        val isi_produk:String = ambil.getString(2)
        val isi_harga:String = ambil.getString(3)
        val isi_foto:ByteArray = ambil.getBlob(4)

        val edt_kategori:EditText = findViewById(R.id.edt_kategori)
        val edt_produk:EditText = findViewById(R.id.edt_produk)
        val edt_harga:EditText = findViewById(R.id.edt_harga)
        val btn_simpan:Button = findViewById(R.id.btn_simpan)

        iv_upload = findViewById(R.id.iv_upload)

        edt_kategori.setText(isi_kategori)
        edt_produk.setText(isi_produk)
        edt_harga.setText(isi_harga)

        try {
            val bos = ByteArrayInputStream(isi_foto)
            val fotobitmap: Bitmap = BitmapFactory.decodeStream(bos)
            iv_upload?.setImageBitmap(fotobitmap)
        }catch (e:Exception){
            val fotobitmap: Bitmap = BitmapFactory.decodeResource(this.resources,R.drawable.no_image)
            iv_upload?.setImageBitmap(fotobitmap)
        }

        iv_upload?.setOnClickListener{
            val bukagaleri:Intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            pilih_foto.launch(bukagaleri)
        }

        btn_simpan.setOnClickListener {
            val kategori_baru: String = edt_kategori.text.toString()
            val produk_baru: String = edt_produk.text.toString()
            val harga_baru: String = edt_harga.text.toString()

            val bos = ByteArrayOutputStream()
            bitmapfoto?.compress(Bitmap.CompressFormat.JPEG, 100, bos)
            val bytearrayfoto = bos.toByteArray()

            val db_sokin: SQLiteDatabase = openOrCreateDatabase("db_sokin", MODE_PRIVATE, null)
            val sql =
                "UPDATE produk SET kategori=?, nama_produk=?, harga=?, foto=? WHERE id_produk='$id_produk'"
            val statement = db_sokin.compileStatement(sql)
            statement.clearBindings()
            statement.bindString(1, kategori_baru) // Mengikat nilai yang baru
            statement.bindString(2, produk_baru)   // Mengikat nilai yang baru
            statement.bindString(3, harga_baru)    // Mengikat nilai yang baru
            statement.bindBlob(4, bytearrayfoto)
            statement.executeUpdateDelete()

            val pindah: Intent = Intent(this, Produk::class.java)
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