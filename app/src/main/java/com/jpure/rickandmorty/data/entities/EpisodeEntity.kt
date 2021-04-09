package com.jpure.rickandmorty.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Jp
 * @date 2021/3/30.
 */
data class EpisodeEntity(
    val info: Info,
    val results: List<Episode>
)

@Entity
data class Episode(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    val url: String,
    val page:Int,
    val remoteName:String
)