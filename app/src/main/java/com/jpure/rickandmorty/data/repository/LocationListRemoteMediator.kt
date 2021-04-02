package com.jpure.rickandmorty.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.jpure.rickandmorty.data.AppDatabase
import com.jpure.rickandmorty.data.entities.Locations
import com.jpure.rickandmorty.data.entities.RemoteKeys
import com.jpure.rickandmorty.data.remote.RickAndMortyService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class LocationListRemoteMediator(
    private val service: RickAndMortyService,
    private val db: AppDatabase
) : RemoteMediator<Int, Locations>() {
    companion object {
        private val TAG = "RoleListRemoteMediator"
        private val remoteLocationsList = "locationsList"
    }
    private var mCount = 0
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Locations>
    ): MediatorResult {
        try {

            /**
             * 在这个方法内将会做三件事
             *
             * 1. 参数 LoadType 有个三个值，关于这三个值如何进行判断
             *      LoadType.REFRESH
             *      LoadType.PREPEND
             *      LoadType.APPEND
             *
             * 2. 访问网络数据
             *
             * 3. 将网路插入到本地数据库中
             */

            val locationsDao = db.locationsDao()
            val remoteKeysDao = db.remoteKeysDao()
//            // 第一步： 判断 LoadType
//            val pageKey = when (loadType) {
//                // 首次访问 或者调用 PagingDataAdapter.refresh()
//                LoadType.REFRESH -> null
//
//                // 在当前加载的数据集的开头加载数据时
//                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
//
//                LoadType.APPEND -> { // 下拉加载更多时触发
//
//                    /**
//                     *
//                     * 这里主要获取下一页数据的开始位置，可以理解为从什么地方开始加载下一页数据
//                     * 这里有两种方式来获取下一页加载数据的位置
//                     * 方式一：这种方式比较简单，当前页面最后一条数据是下一页的开始位置
//                     * 方式二：比较麻烦，当前分页数据没有对应的远程 key，这个时候需要我们自己建表,
//                     */
//                    /**
//                     * 方式一：这种方式比较简单，当前页面最后一条数据是下一页的开始位置
//                     * 通过 load 方法的参数 state 获取当页面最后一条数据
//                     */
//                    val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(
//                            endOfPaginationReached = true)
//                    lastItem
//                }
//            }
            // The network load method takes an optional after=<user.id>
            // parameter. For every page after the first, pass the last user
            // ID to let it continue from where it left off. For REFRESH,
            // pass null to load the first page.
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                // In this example, you never need to prepend, since REFRESH
                // will always load the first page in the list. Immediately
                // return, reporting end of pagination.
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    )

                    // You must explicitly check if the last item is null when
                    // appending, since passing null to networkService is only
                    // valid for initial load. If lastItem is null it means no
                    // items were loaded after the initial REFRESH and there are
                    // no more items to load.

                    lastItem.page
                }
            }
            // Suspending network load via Retrofit. This doesn't need to be
            // wrapped in a withContext(Dispatcher.IO) { ... } block since
            // Retrofit's Coroutine CallAdapter dispatches on a worker
            // thread.
            // 第二步： 请问网络分页数据

            if(mCount+1==loadKey){
                 return MediatorResult.Success(endOfPaginationReached = false)
            }
            val page = loadKey ?: 0
            val results = service.getLocationsList(page).apply {
                mCount = info.pages
            }.results

            val item = results.map {
                Locations(
                    id = it.id,
                    name = it.name,
                    type = it.type,
                    url = it.url,
                    created = it.created,
                    page = page + 1,
                    remoteName = remoteLocationsList,
                    dimension = it.dimension,
                    residents = it.residents
                )
            }
            val endOfPaginationReached = results.isNullOrEmpty()

            // 第三步： 插入数据库
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeysDao.clearRemoteKeys(remoteLocationsList)
                    locationsDao.clearLocations(remoteLocationsList)
                }
                val nextKey = if (endOfPaginationReached) null else page + 1
                val entity = RemoteKeys(
                    remoteName = remoteLocationsList,
                    nextKey = nextKey
                )
                remoteKeysDao.insert(entity)
                locationsDao.insertAll(item)
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }


}