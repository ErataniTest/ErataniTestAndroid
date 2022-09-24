package com.gunawan.eratani.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.gunawan.eratani.R
import com.gunawan.eratani.databinding.ActivitySearchBinding
import com.gunawan.eratani.databinding.ActivityUsersBinding
import com.gunawan.eratani.ui.adapter.SearchAdapter
import com.gunawan.eratani.ui.adapter.UsersAdapter
import com.gunawan.eratani.viewmodel.UsersViewModel

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var searchAdapter: SearchAdapter
    private val listText = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tbSearch.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.tbSearch.title = getString(R.string.search)

        listText.add("Aku")
        listText.add("aku")
        listText.add("saya")
        listText.add("SAYA")
        listText.add("makan")
        listText.add("minum")
        listText.add("belajar")
        listText.add("buku")
        listText.add("kota")
        listText.add("tinggal")
        listText.add("rumah")
        listText.add("tidur")
        listText.add("minta")
        listText.add("Minta")
        listText.add("mau")
        listText.add("MAU")
        listText.add("juga")
        listText.add("dalam")
        listText.add("luar")
        listText.add("LUAR")


        binding.tbSearch.setNavigationOnClickListener {
            finish()
        }

        binding.ibSearch.setOnClickListener {
            val textSearch = binding.etSearch.text.toString()
            if (textSearch.isNotBlank()) {
                val tempListText = listText
                val listResult: MutableList<String> = mutableListOf<String>()
                val splitSearch = textSearch.split(" ")
                for (i in splitSearch) {
                    var j = 0
                    while (j < tempListText.size) {
                        if (i == tempListText[j]) {
                            listResult.add(tempListText[j])
                            tempListText.removeAt(j)     // 1. this line have error
                        } else j++
                    }
                }
                searchAdapter = SearchAdapter(listResult)
                binding.rvUsers.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this@SearchActivity)
                    adapter = searchAdapter
                }


            }
        }

    }
}