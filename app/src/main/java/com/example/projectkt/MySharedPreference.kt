package com.example.projectkt

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import javax.inject.Inject

class MySharedPreference @Inject constructor(private val application :Context) {

   init {

       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
           sharedPreferences = EncryptedSharedPreferences.create(
               application,
               "MySharedPref",
               getMasterKey(),
               EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
               EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
           )

       } else {
           sharedPreferences =
               application.getSharedPreferences(
                   "MySharedPref",
                   Context.MODE_PRIVATE
               )
       }
       val editor = sharedPreferences!!.edit()
       editor.apply()
   }

    private fun getMasterKey(): MasterKey {
        return MasterKey.Builder(application)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }


    companion object {
        private var myPreferences: MySharedPreference? = null
        private var sharedPreferences: SharedPreferences? = null
        private var editor: SharedPreferences.Editor? = null

        fun getPreferencesInstance(application: Context): MySharedPreference {
            if (myPreferences == null) myPreferences = MySharedPreference(application)
            return myPreferences!!
        }
    }

    fun setName(printer: String?) {
        sharedPreferences?.edit()?.putString("name", printer)?.apply()
    }

    fun getName(): String? {
        return sharedPreferences?.getString("name", null)
    }


}