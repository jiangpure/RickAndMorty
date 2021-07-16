package com.jpure.rickandmorty.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Pure Jiang
 * @date 2021/3/30.
 */
data class LocationsEntity(
    val info: Info,
    val results: List<Locations>
)

@Entity
data class Locations(
    val created: String,
    val dimension: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String,
    val page:Int,
    val remoteName:String
)