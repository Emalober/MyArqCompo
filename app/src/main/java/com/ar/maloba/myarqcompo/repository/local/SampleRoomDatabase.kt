package com.ar.maloba.myarqcompo.repository.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ar.maloba.myarqcompo.repository.dao.dao.UserDao
import com.ar.maloba.myarqcompo.repository.dao.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class SampleRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}