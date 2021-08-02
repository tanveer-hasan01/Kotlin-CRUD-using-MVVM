package com.example.projectkt.ViewModel

import android.media.midi.MidiOutputPort
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectkt.ModelData.ModelUser
import com.example.projectkt.Network.RetroInstance
import com.example.projectkt.Network.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel:ViewModel() {

    lateinit var recyclerListData:MutableLiveData<ModelUser>

    init {

        recyclerListData= MutableLiveData()

    }


    fun getUserListObserveable():MutableLiveData<ModelUser>{

        return recyclerListData
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