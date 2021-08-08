package com.example.projectkt.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersViewModel(application: Application):AndroidViewModel(application) {

    private val readAllData: LiveData<List<UsersModel>>

    private  val repository:UserRepository

    init {

        var userDao=UserDatabase.getDatabase(application).userDao()

        repository= UserRepository(userDao)
        readAllData=repository.readAllData
    }

    fun addUser(user:UsersModel){

        viewModelScope.launch ( Dispatchers.IO){

            repository.addUser(user)

        }

    }


}