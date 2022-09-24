package com.gunawan.eratani.repository

import com.gunawan.eratani.repository.remote.APIService
import com.gunawan.eratani.repository.remote.model.ReqAddUser
import com.gunawan.eratani.repository.remote.model.RespAddUser
import com.gunawan.eratani.repository.remote.model.RespGetUsers
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepository @Inject constructor(private val api: APIService) {

    fun getUsers(): Observable<List<RespGetUsers>> {
        return api.getUsers()
    }

    fun addUser(name: String, email: String, gender: String, status: String): Observable<RespAddUser> {
        val request = ReqAddUser()
        request.name    = name
        request.email   = email
        request.gender  = gender
        request.status  = status
        return api.addUser(request)
    }

}