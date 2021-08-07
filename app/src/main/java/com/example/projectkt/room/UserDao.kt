package com.example.projectkt.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.selects.select


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user:UsersModel)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAll(): LiveData<List<UsersModel>>
}