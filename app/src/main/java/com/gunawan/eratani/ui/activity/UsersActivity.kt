package com.gunawan.eratani.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.gunawan.eratani.R
import com.gunawan.eratani.databinding.ActivityUsersBinding
import com.gunawan.eratani.databinding.BottomSheetAddUserBinding
import com.gunawan.eratani.ui.adapter.UsersAdapter
import com.gunawan.eratani.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUsersBinding
    private lateinit var usersAdapter: UsersAdapter
    private val usersViewModel: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tbUsers.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.tbUsers.title           = getString(R.string.users)
        binding.pbUsers.visibility      = View.VISIBLE
        binding.hsvUsers.visibility     = View.GONE
        usersViewModel.ldGetUsers       = MutableLiveData()
        usersViewModel.ldAddUser        = MutableLiveData()
        usersViewModel.ldMsg            = MutableLiveData()

        binding.tbUsers.setNavigationOnClickListener {
            finish()
        }

        binding.ivAddUser.setOnClickListener {
            showBottomSheetAddUser()
        }

        getUsers()
        getUsersMsg()
    }

    private fun getUsers() {
        usersViewModel.ldGetUsers   = MutableLiveData()
        usersViewModel.ldMsg        = MutableLiveData()
        usersViewModel.getUsers()
        usersViewModel.ldGetUsers.observe(this, {
            binding.pbUsers.visibility      = View.GONE
            binding.hsvUsers.visibility     = View.VISIBLE
            if (!it.isNullOrEmpty()) {
                usersAdapter = UsersAdapter(it)
                binding.rvUsers.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this@UsersActivity)
                    adapter = usersAdapter
                }
            }
        })
    }

    @SuppressLint("ShowToast")
    private fun getUsersMsg() {
        usersViewModel.ldMsg.observe(this, {
            binding.pbUsers.visibility      = View.GONE
            binding.hsvUsers.visibility     = View.GONE
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun showBottomSheetAddUser() {
        val arrGender = arrayOf("male", "female")
        val arrStatus = arrayOf("active", "inactive")
        var gender = ""
        var status = ""

        val dialog = BottomSheetDialog(this)
        val bottomSheetAddUserBinding = BottomSheetAddUserBinding.inflate(layoutInflater, null, false)
        dialog.setCancelable(true)
        dialog.setContentView(bottomSheetAddUserBinding.root)
        dialog.show()
        bottomSheetAddUserBinding.etName.setText("")
        bottomSheetAddUserBinding.etEmail.setText("")
        bottomSheetAddUserBinding.tvError.visibility    = View.INVISIBLE
        bottomSheetAddUserBinding.pbSubmit.visibility   = View.GONE
        bottomSheetAddUserBinding.tvSubmit.visibility   = View.VISIBLE

        val genderAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrGender)
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bottomSheetAddUserBinding.spGender.setAdapter(genderAdapter)
        bottomSheetAddUserBinding.spGender.setSelection(0)
        bottomSheetAddUserBinding.spGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (parent != null) {
                    gender = parent.getItemAtPosition(position).toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        val statusAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrStatus)
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bottomSheetAddUserBinding.spStatus.setAdapter(statusAdapter)
        bottomSheetAddUserBinding.spStatus.setSelection(0)
        bottomSheetAddUserBinding.spStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (parent != null) {
                    status = parent.getItemAtPosition(position).toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        bottomSheetAddUserBinding.llSubmit.setOnClickListener {
            val name    = bottomSheetAddUserBinding.etName.text.toString()
            val email   = bottomSheetAddUserBinding.etEmail.text.toString()

            if (name.isBlank() || email.isBlank()) {
                bottomSheetAddUserBinding.tvError.visibility    = View.VISIBLE
                bottomSheetAddUserBinding.tvError.text          = getString(R.string.form_not_complete)
            } else {
                dialog.setCanceledOnTouchOutside(false)
                bottomSheetAddUserBinding.pbSubmit.visibility   = View.VISIBLE
                bottomSheetAddUserBinding.tvSubmit.visibility   = View.GONE
                usersViewModel.ldAddUser    = MutableLiveData()
                usersViewModel.ldMsg        = MutableLiveData()
                usersViewModel.addUser(name, email, gender, status)
                usersViewModel.ldAddUser.observe(this, {
                    binding.pbUsers.visibility      = View.GONE
                    binding.hsvUsers.visibility     = View.VISIBLE
                    dialog.dismiss()

                    binding.pbUsers.visibility      = View.VISIBLE
                    binding.hsvUsers.visibility     = View.GONE
                    getUsers()
                    getUsersMsg()
                })
            }
        }
    }

}