package com.jpure.rickandmorty.data

import androidx.paging.PagingConfig
import com.jpure.rickandmorty.data.remote.RickAndMortyService
import com.jpure.rickandmorty.data.repository.RickAndMortyRepository

/**
 * @author Pure Jiang
 * @date 2021/2/25.
 */
object RepositoryFactory {
    fun makeListRepository(service: RickAndMortyService, db:AppDatabase): RickAndMortyRepository =
        RickAndMortyRepository(service, db, pagingConfig)

    val pagingConfig = PagingConfig(
        // 每页显示的数据的大小
        pageSize = 20,

        // 开启占位符
        enablePlaceholders = true,

        // 预刷新的距离，距离最后一个 item 多远时加载数据
        // 默认为 pageSize
        prefetchDistance = 1,

        // 初始化加载数量，默认为 pageSize * 3
        initialLoadSize = 20
    )
}