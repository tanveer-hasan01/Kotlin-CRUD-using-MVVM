package com.example.projectkt.room

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<UsersModel>> = userDao.readAllData()

   suspend fun addUser(usersModel: UsersModel){

       userDao.addUser(usersModel)

    }

}