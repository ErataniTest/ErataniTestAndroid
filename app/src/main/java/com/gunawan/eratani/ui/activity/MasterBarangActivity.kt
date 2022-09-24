package com.gunawan.eratani.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.gunawan.eratani.R
import com.gunawan.eratani.databinding.ActivityMasterBarangBinding
import com.gunawan.eratani.databinding.BottomSheetFormBarangBinding
import com.gunawan.eratani.repository.local.model.BarangModel
import com.gunawan.eratani.ui.adapter.MasterBarangAdapter
import com.gunawan.eratani.viewmodel.BarangViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MasterBarangActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMasterBarangBinding
    private lateinit var masterBarangAdapter: MasterBarangAdapter
    private lateinit var listBarang: MutableList<BarangModel>
    private val barangViewModel: BarangViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMasterBarangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tbMasterBarang.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.tbMasterBarang.title = getString(R.string.master_barang)

        binding.tbMasterBarang.setNavigationOnClickListener {
            finish()
        }

        binding.fabAdd.setOnClickListener {
            showBottomSheetFormBarang(true, 0)
        }

        getAllBarang()
    }

    private fun getAllBarang() {
        barangViewModel.getAllBarang()?.observe(this, {
            listBarang = it as MutableList<BarangModel>
            masterBarangAdapter = MasterBarangAdapter(it)
            binding.rvMasterBarang.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MasterBarangActivity)
                adapter = masterBarangAdapter
                masterBarangAdapter.setOnCustomClickListener(object : MasterBarangAdapter.OnCustomClickListener {
                    override fun onEditClicked(position: Int) {
                        showBottomSheetFormBarang(false, position)
                    }

                    override fun onDeleteClicked(position: Int) {
                        barangViewModel.deleteBarang(
                            BarangModel(listBarang.get(position).idBarang,
                                listBarang.get(position).name,
                                listBarang.get(position).stock
                            )
                        )
                        getAllBarang()
                    }
                })
            }
        })
    }

    private fun showBottomSheetFormBarang(isAdd: Boolean, position: Int) {
        val dialog = BottomSheetDialog(this)
        val bottomSheetFormBarangBinding = BottomSheetFormBarangBinding.inflate(layoutInflater, null, false)
        dialog.setCancelable(true)
        dialog.setContentView(bottomSheetFormBarangBinding.root)
        dialog.show()
        bottomSheetFormBarangBinding.tvError.visibility = View.INVISIBLE

        if (!isAdd) {
            bottomSheetFormBarangBinding.etName.setText(listBarang.get(position).name)
            bottomSheetFormBarangBinding.etStock.setText(listBarang.get(position).stock.toString())
        }

        bottomSheetFormBarangBinding.btnSubmit.setOnClickListener {
            val name    = bottomSheetFormBarangBinding.etName.text.toString()
            val stock   = bottomSheetFormBarangBinding.etStock.text.toString()
            if (name.isBlank() || stock.isBlank()) {
                bottomSheetFormBarangBinding.tvError.visibility    = View.VISIBLE
                bottomSheetFormBarangBinding.tvError.text          = getString(R.string.form_not_complete)
            } else {
                if (isAdd) {
                    barangViewModel.insertBarang(
                        BarangModel(name = name,
                            stock = stock.toInt()
                        )
                    )
                } else {
                    barangViewModel.updateBarang(
                        BarangModel(listBarang.get(position).idBarang,
                            name,
                            stock.toInt(),
                        )
                    )
                }
                bottomSheetFormBarangBinding.etName.setText("")
                bottomSheetFormBarangBinding.etStock.setText("")
                dialog.dismiss()
                getAllBarang()
            }
        }
    }

}