package com.example.projectkt.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(usersModel: UsersModel)


    @Update
    fun updateUser(usersModel: UsersModel)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData():LiveData<List<UsersModel>>


}