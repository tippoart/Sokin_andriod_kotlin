package com.example.sokin

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class Produk_Item(
    val ini: Context, val id: MutableList<String>, val kategori: MutableList<String>, val produk: MutableList<String>, val harga: MutableList<String>, val foto: MutableList<Bitmap?>
) : RecyclerView.Adapter<Produk_Item.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.produk_item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_kategori: TextView = itemView.findViewById(R.id.txt_kategori)
        val txt_produk: TextView = itemView.findViewById(R.id.txt_produk)
        val txt_harga: TextView = itemView.findViewById(R.id.txt_harga)
        val iv_foto: ImageView = itemView.findViewById(R.id.iv_foto)
        val cardView: CardView = itemView.findViewById(R.id.cardview)
        val iv_hapus: ImageView = itemView.findViewById(R.id.iv_hapus)
        val iv_edit: ImageView = itemView.findViewById(R.id.iv_edit)
    }

    override fun getItemCount(): Int {
        return kategori.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txt_kategori.text = kategori[position]
        holder.txt_produk.text = produk[position]
        holder.txt_harga.text = harga[position]
        holder.iv_foto.setImageBitmap(foto[position])

        holder.iv_hapus.setOnClickListener{
            val id_produk:String = id.get(position)
            val pindah:Intent = Intent(ini, Produk_Hapus::class.java)
            pindah.putExtra("id_produk", id_produk)
            ini.startActivity(pindah)
        }

        holder.iv_edit.setOnClickListener{
            val id_produk:String = id.get(position)
            val pindah:Intent = Intent(ini, Produk_Ubah::class.java)
            pindah.putExtra("id_produk", id_produk)
            ini.startActivity(pindah)
        }
    }
}
