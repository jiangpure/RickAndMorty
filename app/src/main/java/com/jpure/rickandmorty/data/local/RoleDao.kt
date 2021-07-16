package com.jpure.rickandmorty.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jpure.rickandmorty.data.entities.Role

/**
 * @author Pure Jiang
 * @date 2021/2/26.
 */
@Dao
interface RoleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(roleList: List<Role>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(role: Role)

    @Query("SELECT * FROM Role")
    fun getAll(): PagingSource<Int, Role>

    @Query("DELETE FROM Role where remoteName = :name")
    suspend fun clearRole(name: String)

    @Query("SELECT * FROM Role where name LIKE '%' || :parameter || '%'")
    fun roleByParameter(parameter: String): PagingSource<Int, Role>

    @Query("SELECT * FROM Role where id =:id")
    suspend fun getRoleById(id: Int): Role?
}