package com.jpure.rickandmorty.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.jpure.rickandmorty.data.AppDatabase
import com.jpure.rickandmorty.data.entities.Locations
import com.jpure.rickandmorty.data.entities.Role
import com.jpure.rickandmorty.data.remote.RickAndMortyService
import com.jpure.rickandmorty.ext.RmLoadResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * @author Jp
 * @date 2021/2/25.
 */
class RickAndMortyRepository @Inject constructor(
    private val service: RickAndMortyService,
    private val db: AppDatabase,
    private val pageConfig: PagingConfig
) {

    fun fetchRoleList(): Flow<PagingData<Role>> {
        return Pager(
            config = pageConfig,
            remoteMediator = RoleListRemoteMediator(service, db)
        ) {
            db.roleDao().getAll()
        }.flow
    }
    fun fetchLocationsList(): Flow<PagingData<Locations>> {
        return Pager(
            config = pageConfig,
            remoteMediator = LocationListRemoteMediator(service, db)
        ) {
            db.locationsDao().getAll()
        }.flow
    }

    fun fetchRoleInfo(id:Int):Flow<RmLoadResult<Role>>{
        return flow {
            try {
                val dao = db.roleDao()
                var role = dao.getRoleById(id)
                if (role == null) {
                    role = service.getRoleInfo(id)
                    dao.insert(role)
                }
                //发射数据
                emit(RmLoadResult.Success(role))
            }catch (e:Exception){
                //发射异常原因
                emit(RmLoadResult.Failure(e.cause))
            }
            //回到io线程
        }.flowOn(Dispatchers.IO)
    }
}