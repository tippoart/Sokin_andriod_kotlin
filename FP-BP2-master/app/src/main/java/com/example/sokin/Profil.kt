package com.example.sokin

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Profil : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var txt_namaUser: TextView
    private lateinit var txt_emailUser: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.profile, container, false)

        val iv_back: ImageView = view.findViewById(R.id.iv_back)
        iv_back.setOnClickListener {
            val pindah: Intent = Intent(activity, Dashboard::class.java)
            startActivity(pindah)
        }

        txt_namaUser = view.findViewById(R.id.txt_namaUser)
        txt_emailUser = view.findViewById(R.id.txt_emailUser)

        val btn_logout: Button = view.findViewById(R.id.btn_logout)
        btn_logout.setOnClickListener {
            val edittiket = requireActivity().getSharedPreferences("user", MODE_PRIVATE).edit()
            edittiket.clear()
            edittiket.apply()

            val keluar = Intent(activity, Login::class.java)
            startActivity(keluar)
        }

        val btn_edit: Button = view.findViewById(R.id.btn_edit)
        btn_edit.setOnClickListener {
            val id_user = requireActivity().getSharedPreferences("user", MODE_PRIVATE)
                .getString("id_user", null).toString()
            val intent = Intent(activity, Profile_Edit::class.java)
            intent.putExtra("id_user", id_user)
            startActivity(intent)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateProfileData()
    }

    private fun updateProfileData() {
        val tiket: SharedPreferences = requireActivity().getSharedPreferences("user", MODE_PRIVATE)
        val nama_user = tiket.getString("nama_user", null).toString()
        txt_namaUser.text = nama_user

        val email_user = tiket.getString("email_user", null).toString()
        txt_emailUser.text = email_user
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            Profil().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}