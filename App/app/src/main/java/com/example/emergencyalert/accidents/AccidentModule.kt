package com.example.emergencyalert.accidents

import android.app.Application
import com.example.emergencyalert.accidents.services.AccidentService
import com.example.emergencyalert.util.GetAuthToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AccidentModule {
    @Provides
    @Singleton
    fun provideAccidentService():AccidentService{
        return AccidentService.create()
    }
//    @Provides
//    @Singleton
//    fun provideAuthToken(
//        app: Application
//    ): String {
//        return GetAuthToken().getAuthToken(context = app)
//    }
}