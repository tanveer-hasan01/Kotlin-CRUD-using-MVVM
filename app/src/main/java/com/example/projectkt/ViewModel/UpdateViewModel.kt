package com.example.projectkt.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectkt.ModelData.User
import com.example.projectkt.ModelData.UserResponse
import com.example.projectkt.Network.RetroInstance
import com.example.projectkt.Network.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateViewModel :ViewModel() {

    lateinit var updateUserLiveData: MutableLiveData<UserResponse?>
    lateinit var loadUserData: MutableLiveData<UserResponse?>
    init {
        updateUserLiveData = MutableLiveData()
        loadUserData = MutableLiveData()
    }


    fun getLoadUserObservable(): MutableLiveData<UserResponse?> {
        return  loadUserData
    }

    fun getUpdateObservable(): MutableLiveData<UserResponse?> {
        return  updateUserLiveData
    }



    fun getUserData(user_id: String?) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getUser(user_id!!)
        call.enqueue(object : Callback<UserResponse?> {
            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                loadUserData.postValue(null)
            }

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if(response.isSuccessful) {
                    loadUserData.postValue(response.body())
                } else {
                    loadUserData.postValue(null)
                }
            }
        })
    }


    fun updateUser(user_id: String, user: User) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.updateUser(user_id, user)
        call.enqueue(object : Callback<UserResponse?> {
            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                updateUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if(response.isSuccessful) {
                    updateUserLiveData.postValue(response.body())
                } else {
                    updateUserLiveData.postValue(null)
                }
            }
        })
    }


}