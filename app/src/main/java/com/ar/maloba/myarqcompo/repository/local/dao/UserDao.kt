package com.ar.maloba.myarqcompo.repository.dao.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.ar.maloba.myarqcompo.repository.dao.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM UserEntity WHERE id = :userId")
    fun getUser(userId: Int): LiveData<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userEntity: UserEntity)

    @Update
    fun updateUser(userEntity: UserEntity)

    @Delete
    fun deleteUser(userEntity: UserEntity)
}