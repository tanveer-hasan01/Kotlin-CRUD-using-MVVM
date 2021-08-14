package com.example.projectkt

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {

    companion object{

        @Singleton
        @Provides
        fun provideSharedPreferences(application: Application): MySharedPreference {
            return MySharedPreference.getPreferencesInstance(application = application);
        }
    }
}