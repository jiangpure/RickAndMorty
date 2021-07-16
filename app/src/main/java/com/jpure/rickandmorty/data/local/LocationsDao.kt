package com.jpure.rickandmorty.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jpure.rickandmorty.data.entities.Locations
import com.jpure.rickandmorty.data.entities.Role

/**
 * @author Pure Jiang
 * @date 2021/2/26.
 */
@Dao
interface LocationsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(roleList: List<Locations>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(role: Locations)

    @Query("SELECT * FROM Locations")
    fun getAll(): PagingSource<Int, Locations>

    @Query("DELETE FROM Locations where name = :name")
    suspend fun clearLocations(name: String)

    @Query("SELECT * FROM Locations where name LIKE '%' || :parameter || '%'")
    fun locationsByParameter(parameter: String): PagingSource<Int, Locations>

    @Query("SELECT * FROM Locations where id =:id")
    suspend fun getLocationsById(id: Int): Locations?
}