package com.jpure.rickandmorty.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jpure.rickandmorty.data.entities.RemoteKeys


/**
 * @author Jp
 * @date 2021/3/4.
 */
@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remoteKey: RemoteKeys)

    @Query("SELECT * FROM RemoteKeys where remoteName = :name ")
    suspend fun getRemoteKeys(name: String): RemoteKeys?

    @Query("DELETE FROM RemoteKeys where remoteName = :name")
    suspend fun clearRemoteKeys(name: String)
}