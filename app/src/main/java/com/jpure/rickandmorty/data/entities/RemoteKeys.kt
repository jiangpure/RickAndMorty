package com.jpure.rickandmorty.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Pure Jiang
 * @date 2021/3/4.
 */
@Entity
data class RemoteKeys(
    @PrimaryKey
    val remoteName: String,
    val nextKey: Int?
)