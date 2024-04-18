package com.example.emergencyalert.donors

import android.app.Application
import com.example.emergencyalert.donors.services.BloodDonorService
import com.example.emergencyalert.hospitals.services.HospitalService
import com.example.emergencyalert.util.GetAuthToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DonorModule {
    @Provides
    @Singleton
    fun provideBloodDonorService(
    ): BloodDonorService {
        return BloodDonorService.create()
    }
}