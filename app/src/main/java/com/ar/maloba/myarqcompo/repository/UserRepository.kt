package com.ar.maloba.myarqcompo.repository

import android.arch.lifecycle.LiveData
import com.ar.maloba.myarqcompo.repository.dao.dao.UserDao
import com.ar.maloba.myarqcompo.repository.dao.entity.UserEntity
import com.ar.maloba.myarqcompo.utils.AppExecutors
import com.ar.maloba.myarqcompo.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRepository
@Inject
constructor(private val appExecutors: AppExecutors,
            private val userDao: UserDao,
            private val sampleApi: SampleApi) {

    //Requesting just Remote Data Sourcing
    fun getAllUsers(): LiveData<Resource<List<UserEntity>>> {
        return object : ProcessedNetworkResource<UserGetAllResponse, List<UserEntity>>() {
            override fun createCall(): LiveData<ApiResponse<UserGetAllResponse>> =
                sampleApi.getUsers()

            override fun processResponse(response: UserGetAllResponse): List<UserEntity>? =
                response.data.map {
                    UserEntity(it.id, it.firstName, it.lastName)
                }
        }.asLiveData()
    }

    fun saveUserOnFromServer(userRequest: UserRequest): LiveData<Resource<UserEntity>> {
        return object : ProcessedNetworkResource<UserPostResponse, UserEntity>() {
            override fun createCall(): LiveData<ApiResponse<UserPostResponse>> =
                sampleApi.postUsers(userRequest)

            override fun processResponse(response: UserPostResponse): UserEntity? =
                UserEntity(response.data.insertId, userRequest.firstName, userRequest.lastName)
        }.asLiveData()
    }

    fun getUsers(userId: Int): LiveData<Resource<UserEntity>> =
        object : DetailNetworkResource<UserEntity, UserGetResponse>(appExecutors) {
            override fun saveCallResult(item: UserGetResponse) {
                //MAPPING
                val userEntity = UserEntity(item.data.id, item.data.firstName!!, item.data.lastName!!)

                //Save
                userDao.insertUser(userEntity)
            }

            override fun shouldFetch(data: UserEntity?): Boolean =
                data == null

            override fun loadFromDb(): LiveData<UserEntity> =
                userDao.getUser(userId)

            override fun createCall(): LiveData<ApiResponse<UserGetResponse>> =
                sampleApi.getUsers(userId)

        }.asLiveData()
}