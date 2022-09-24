package com.gunawan.eratani.repository.remote.model

import com.google.gson.annotations.SerializedName

data class ReqAddUser(

	@field:SerializedName("gender")
	var gender: String? = null,

	@field:SerializedName("name")
	var name: String? = null,

	@field:SerializedName("email")
	var email: String? = null,

	@field:SerializedName("status")
	var status: String? = null
)
