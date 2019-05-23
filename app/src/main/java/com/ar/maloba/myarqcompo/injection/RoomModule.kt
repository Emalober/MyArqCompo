package com.ar.maloba.myarqcompo.injection

import android.app.Application
import android.arch.persistence.room.Room
import com.ar.maloba.myarqcompo.repository.local.SampleRoomDatabase
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideDb(context: Application): SampleRoomDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            SampleRoomDatabase::class.java,
            "Sample.db")
            .build()

    @Singleton
    @Provides
    fun provideUserDao(sampleRoomDatabase: SampleRoomDatabase) = sampleRoomDatabase.userDao()

//    @Singleton
//    @Provides
//    fun provideAppExecutors() = AppExecutors(
//        Executors.newSingleThreadExecutor(),
//        Executors.newFixedThreadPool(THREAD_COUNT),
//        AppExecutors.MainThreadExecutor())

    companion object {
        const val THREAD_COUNT = 3
    }

}