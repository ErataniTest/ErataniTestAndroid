package com.gunawan.eratani.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gunawan.eratani.R
import com.gunawan.eratani.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tbMain.title = getString(R.string.app_name)

        binding.btnPencarianKata.setOnClickListener {
            intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        binding.btnPemrosesanData.setOnClickListener {
            intent = Intent(this, StokBarangActivity::class.java)
            startActivity(intent)
        }

        binding.btnAnimasi.setOnClickListener {
            intent = Intent(this, AnimationActivity::class.java)
            startActivity(intent)
        }

        binding.btnAPICalling.setOnClickListener {
            intent = Intent(this, UsersActivity::class.java)
            startActivity(intent)
        }
    }
}