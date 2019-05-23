package com.ar.maloba.myarqcompo.repository.dao.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class UserEntity(@PrimaryKey(autoGenerate = true) val id: Int, val name: String)