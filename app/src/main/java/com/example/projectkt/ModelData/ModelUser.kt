package com.example.projectkt.ModelData

data class ModelUser (val data: List<User>)
data class User (val id: String?,val name: String?,val status:String?,val gender: String?)
data class UserResponse(val code:Int?,val meta:String?,val data: User)