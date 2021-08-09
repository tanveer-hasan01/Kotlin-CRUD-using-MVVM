package com.example.projectkt.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(usersModel: UsersModel)


    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData():LiveData<List<UsersModel>>


}