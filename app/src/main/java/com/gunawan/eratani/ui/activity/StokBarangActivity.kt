package com.gunawan.eratani.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gunawan.eratani.R
import com.gunawan.eratani.databinding.ActivityMainBinding
import com.gunawan.eratani.databinding.ActivityStokBarangBinding

class StokBarangActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStokBarangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStokBarangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tbStokBarang.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.tbStokBarang.title = getString(R.string.stok_barang)

        binding.tbStokBarang.setNavigationOnClickListener {
            finish()
        }

        binding.btnMasterBarang.setOnClickListener {
            intent = Intent(this, MasterBarangActivity::class.java)
            startActivity(intent)
        }

        binding.btnOrder.setOnClickListener {
            intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }
    }

}