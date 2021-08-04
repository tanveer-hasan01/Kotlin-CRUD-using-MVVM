package com.example.projectkt.ViewModel

import android.media.midi.MidiOutputPort
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectkt.ModelData.ModelUser
import com.example.projectkt.ModelData.UserResponse
import com.example.projectkt.Network.RetroInstance
import com.example.projectkt.Network.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel:ViewModel() {

    lateinit var recyclerListData:MutableLiveData<ModelUser>
    lateinit var deleteUserLiveData: MutableLiveData<UserResponse?>
    init {

        recyclerListData= MutableLiveData()
        deleteUserLiveData = MutableLiveData()
    }


    fun getDeleteUserObservable(): MutableLiveData<UserResponse?> {
        return  deleteUserLiveData
    }

    fun getUserListObserveable():MutableLiveData<ModelUser>{

        return recyclerListData
    }



    fun deleteUser(user_id: String?) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.deleteUser(user_id!!)
        call.enqueue(object : Callback<UserResponse?> {
            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                deleteUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if(response.isSuccessful) {
                    deleteUserLiveData.postValue(response.body())
                } else {
                    deleteUserLiveData.postValue(null)
                }
            }
        })
    }


    fun getUsersList() {

        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getUsersList()
        call.enqueue(object : Callback<ModelUser>{
            override fun onFailure(call: Call<ModelUser>, t: Throwable) {
                recyclerListData.postValue(null)
            }

            override fun onResponse(call: Call<ModelUser>, response: Response<ModelUser>) {
                if(response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }
        })
    }


    fun searchUser(searchText: String) {

        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.searchUsers(searchText)
        call.enqueue(object : Callback<ModelUser>{
            override fun onFailure(call: Call<ModelUser>, t: Throwable) {
                recyclerListData.postValue(null)
            }

            override fun onResponse(call: Call<ModelUser>, response: Response<ModelUser>) {
                if(response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }
        })
    }

}