package com.jpure.rickandmorty.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jpure.rickandmorty.data.entities.Episode
import com.jpure.rickandmorty.data.entities.Locations
import com.jpure.rickandmorty.data.entities.Role

/**
 * @author Pure Jiang
 * @date 2021/2/26.
 */
@Dao
interface EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(roleList: List<Episode>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(role: Episode)

    @Query("SELECT * FROM Episode")
    fun getAll(): PagingSource<Int, Episode>

    @Query("DELETE FROM Episode where name = :name")
    suspend fun clearEpisode(name: String)

    @Query("SELECT * FROM Episode where name LIKE '%' || :parameter || '%'")
    fun episodeByParameter(parameter: String): PagingSource<Int, Episode>

    @Query("SELECT * FROM Episode where id =:id")
    suspend fun getEpisodeById(id: Int): Episode?
}