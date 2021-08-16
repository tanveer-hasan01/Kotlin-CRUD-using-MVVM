package com.example.projectkt.Dependency_injection
import com.example.projectkt.MySharedPreference
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class BaseApplication :DaggerApplication(),HasAndroidInjector {

    @Inject
    lateinit var sharedPreferences: MySharedPreference



    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent

    }


}