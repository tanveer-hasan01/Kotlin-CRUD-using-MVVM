package com.example.projectkt.Dependency_injection

import android.app.Application
import android.content.Context
import com.example.projectkt.MySharedPreference
import com.example.projectkt.activity.AddUserActivity
import com.example.projectkt.onBoarding.screens.Lastscreen
import com.example.projectkt.onBoarding.screens.SplashFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class AppModule {


    @Binds
    abstract fun bindContext(application: Application): Context



    @ContributesAndroidInjector
    abstract fun mainActivityInjector(): AddUserActivity

    @ContributesAndroidInjector
    abstract fun splashInjector(): SplashFragment

    @ContributesAndroidInjector
    abstract fun LastscreenInjector(): Lastscreen



    companion object{

        @Singleton
        @Provides
        fun provideSharedPreferences(application: Application): MySharedPreference {
            return MySharedPreference.getPreferencesInstance(application = application);
        }
    }
}