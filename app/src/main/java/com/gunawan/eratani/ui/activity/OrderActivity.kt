package com.gunawan.eratani.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.gunawan.eratani.R
import com.gunawan.eratani.databinding.ActivityOrderBinding
import com.gunawan.eratani.databinding.BottomSheetFormOrderBinding
import com.gunawan.eratani.repository.local.model.BarangModel
import com.gunawan.eratani.repository.local.model.BarangOrderModel
import com.gunawan.eratani.repository.local.model.OrderModel
import com.gunawan.eratani.ui.adapter.OrderAdapter
import com.gunawan.eratani.viewmodel.BarangViewModel
import com.gunawan.eratani.viewmodel.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class OrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding
    private lateinit var orderAdapter: OrderAdapter
    private var listBarangOrder: MutableList<BarangOrderModel> = ArrayList()
    private val barangViewModel: BarangViewModel by viewModels()
    private val orderViewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tbOrder.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.tbOrder.title = getString(R.string.order)

        binding.tbOrder.setNavigationOnClickListener {
            finish()
        }

        binding.btnSubmit.setOnClickListener {
            AlertDialog.Builder(this, androidx.appcompat.R.style.AlertDialog_AppCompat).apply {
                setMessage(getString(R.string.confirm_checkout))
                setPositiveButton(getString(R.string.yes)) { _, _ ->
                    val current = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    val dateFormatted = current.format(formatter)
                    for (i in listBarangOrder.indices) {
                        if (listBarangOrder[i].totalOrder > 0) {
                            orderViewModel.insertOrder(
                                OrderModel(
                                    idBarang = listBarangOrder[i].idBarang!!,
                                    qty = listBarangOrder[i].totalOrder,
                                    tglOrder = dateFormatted
                                )
                            )
                            barangViewModel.updateBarang(
                                BarangModel(
                                    idBarang = listBarangOrder[i].idBarang!!,
                                    name = listBarangOrder[i].name,
                                    stock = listBarangOrder[i].stock
                                )
                            )
                        }
                    }
                    Toast.makeText(this@OrderActivity, R.string.message_finish_order, Toast.LENGTH_SHORT).show()
                    finish()
                }
                setNegativeButton(getString(R.string.no)) {_, _ -> }
                setCancelable(true)
            }.create().show()
        }

        getAllBarang()
    }

    private fun getAllBarang() {
        barangViewModel.getAllBarang()?.observe(this, {
            var listBarang = it as MutableList<BarangModel>

            if (listBarang.size > 0) {
                for (i in listBarang.indices) {
                    listBarangOrder.add(BarangOrderModel(
                        listBarang[i].idBarang,
                        listBarang[i].name,
                        listBarang[i].stock,
                        0
                    ))
                }

                orderAdapter = OrderAdapter(listBarangOrder)
                binding.rvOrder.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this@OrderActivity)
                    adapter = orderAdapter
                    orderAdapter.setOnCustomClickListener(object : OrderAdapter.OnCustomClickListener {
                        override fun onBtnOrderClicked(position: Int) {
                            showBottomSheetFormOrder(position)
                        }

                        override fun onEditOrderClicked(position: Int) {
                            showBottomSheetFormOrder(position)
                        }
                    })
                }
            }



        })
    }

    private fun showBottomSheetFormOrder(position: Int) {
        val dialog = BottomSheetDialog(this)
        val bottomSheetFormOrderBinding = BottomSheetFormOrderBinding.inflate(layoutInflater, null, false)
        dialog.setCancelable(true)
        dialog.setContentView(bottomSheetFormOrderBinding.root)
        dialog.show()
        bottomSheetFormOrderBinding.tvError.visibility = View.INVISIBLE
        bottomSheetFormOrderBinding.etTotalOrder.setText(listBarangOrder[position].totalOrder.toString())

        bottomSheetFormOrderBinding.btnSave.setOnClickListener {
            val totalOrder   = bottomSheetFormOrderBinding.etTotalOrder.text.toString()
            if (totalOrder.isBlank() || totalOrder.toInt() == 0) {
                bottomSheetFormOrderBinding.tvError.visibility    = View.VISIBLE
                bottomSheetFormOrderBinding.tvError.text          = getString(R.string.form_not_complete)
            } else if (totalOrder.toInt() > listBarangOrder[position].stock) {
                bottomSheetFormOrderBinding.tvError.visibility    = View.VISIBLE
                bottomSheetFormOrderBinding.tvError.text          = getString(R.string.order_more_than_stock)
            } else {
                listBarangOrder.set(position,
                    BarangOrderModel(
                        listBarangOrder[position].idBarang,
                        listBarangOrder[position].name,
                        listBarangOrder[position].stock - totalOrder.toInt(),
                        totalOrder.toInt()
                    )
                )
                bottomSheetFormOrderBinding.etTotalOrder.setText("")
                dialog.dismiss()
                orderAdapter.notifyItemChanged(position)
            }
        }

        bottomSheetFormOrderBinding.btnDelete.setOnClickListener {
            val totalOrder   = bottomSheetFormOrderBinding.etTotalOrder.text.toString()
            listBarangOrder.set(position,
                BarangOrderModel(
                    listBarangOrder[position].idBarang,
                    listBarangOrder[position].name,
                    listBarangOrder[position].stock + totalOrder.toInt(),
                    0
                )
            )
            bottomSheetFormOrderBinding.etTotalOrder.setText("")
            dialog.dismiss()
            orderAdapter.notifyItemChanged(position)
        }
    }

}