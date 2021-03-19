package com.jpure.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jpure.rickandmorty.data.AppDatabase
import com.jpure.rickandmorty.data.entities.Role
import com.jpure.rickandmorty.data.remote.RickAndMortyService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Jp
 * @date 2021/2/25.
 */
class ListRepository @Inject constructor(
    private val service: RickAndMortyService,
    private val db: AppDatabase,
    private val pageConfig: PagingConfig
) {

    fun fetchRoleList(): Flow<PagingData<Role>> {
        return Pager(
            config = pageConfig,
            remoteMediator = RoleListRemoteMediator(service, db)
        ) {
            db.roleDao().getRole()
        }.flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 1
    }
}