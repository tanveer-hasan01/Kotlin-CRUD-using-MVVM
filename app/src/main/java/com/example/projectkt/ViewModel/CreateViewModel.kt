package com.example.projectkt.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectkt.ModelData.ModelUser
import com.example.projectkt.ModelData.User
import com.example.projectkt.ModelData.UserResponse
import com.example.projectkt.Network.RetroInstance
import com.example.projectkt.Network.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateViewModel: ViewModel() {

    lateinit var createViewModelLiveData: MutableLiveData<UserResponse?>

    init {
        createViewModelLiveData=MutableLiveData()
    }



    fun getCreateNewUserObservable():MutableLiveData<UserResponse?>{
        return createViewModelLiveData
    }



  fun  createUser(user: User){

      val retroInstance=RetroInstance.getRetroInstance().create(RetroService::class.java)
      val call =retroInstance.createUser(user)
      call.enqueue(object : Callback<UserResponse?> {
          override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
              createViewModelLiveData.postValue(null)
          }

          override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
              if(response.isSuccessful) {
                  createViewModelLiveData.postValue(response.body())
              } else {
                  createViewModelLiveData.postValue(null)
              }
          }
      })


      }





}