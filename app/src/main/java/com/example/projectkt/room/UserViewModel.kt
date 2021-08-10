package com.example.projectkt.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

     val readAllData: LiveData<List<UsersModel>>

    private val repository: UserRepository

    init {

        val userDao = UserDatabase.getDatabase(application).userDao()

        repository= UserRepository(userDao)
        readAllData= repository.readAllData
    }



    fun  addUser(usersModel: UsersModel){
        viewModelScope.launch(Dispatchers.IO){

            repository.addUser(usersModel)

        }
    }


    fun updateUser(usersModel: UsersModel){

        viewModelScope.launch(Dispatchers.IO){
            repository.updateUser(usersModel)

        }
    }


}