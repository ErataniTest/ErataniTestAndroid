package com.gunawan.eratani.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gunawan.eratani.repository.UsersRepository
import com.gunawan.eratani.repository.remote.model.RespAddUser
import com.gunawan.eratani.repository.remote.model.RespGetUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val repo: UsersRepository) : ViewModel() {
    private var disposables         = CompositeDisposable()
    var ldGetUsers                  = MutableLiveData<List<RespGetUsers>>()
    var ldAddUser                   = MutableLiveData<RespAddUser>()
    var ldMsg                       = MutableLiveData<String>()

    fun getUsers() {
        disposables.add(
            repo.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {}
                .doOnComplete {}
                .subscribe({ it ->
                    ldGetUsers.value = it
                }, {
                    ldMsg.value = it.message
                })
        )
    }

    fun addUser(name: String, email: String, gender: String, status: String) {
        disposables.add(
            repo.addUser(name, email, gender, status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {}
                .doOnComplete {}
                .subscribe({ it ->
                    ldAddUser.value = it
                }, {
                    ldMsg.value = it.message
                })
        )
    }

}