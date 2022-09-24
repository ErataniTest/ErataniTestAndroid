package com.gunawan.eratani.repository.remote

import com.gunawan.eratani.repository.remote.model.ReqAddUser
import com.gunawan.eratani.repository.remote.model.RespAddUser
import com.gunawan.eratani.repository.remote.model.RespGetUsers
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {
    @GET("users")
    fun getUsers(): Observable<List<RespGetUsers>>

    @POST("users")
    fun addUser(@Body request: ReqAddUser): Observable<RespAddUser>
}