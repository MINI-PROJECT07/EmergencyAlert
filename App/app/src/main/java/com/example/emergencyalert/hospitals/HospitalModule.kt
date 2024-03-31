package com.example.emergencyalert.hospitals

import android.app.Application
import com.example.emergencyalert.hospitals.services.HospitalService
import com.example.emergencyalert.util.GetAuthToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HospitalModule {
    @Provides
    @Singleton
    fun provideHospitalService(
    ): HospitalService {
        return HospitalService.create()
    }

    @Provides
    @Singleton
    fun provideAuthToken(
        app: Application
    ): String {
        return GetAuthToken().getAuthToken(context = app)
    }
}