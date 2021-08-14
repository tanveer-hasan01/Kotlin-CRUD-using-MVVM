package com.example.projectkt

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.HasAndroidInjector


class BaseApplication :DaggerApplication(),HasAndroidInjector {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent

    }


}