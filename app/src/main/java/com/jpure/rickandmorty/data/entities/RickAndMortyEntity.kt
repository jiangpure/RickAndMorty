package com.jpure.rickandmorty.data.entities

import androidx.room.*

/**
 * @author Jp
 * @date 2021/2/25.
 */
data class RickAndMortyEntity(
    val info: Info,
    val results: List<Role>
)

@Entity
data class Role(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    //添加data类需要加@Embedded注解
    @Embedded val origin:Location,
    @Embedded val location:Origin,
    val image: String,
    val episode:List<String>,
    val url:String,
    val created:String,
    val page:Int,
    val remoteName:String
)

data class Info(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String
)
data class Location(
    //指定数据库中的字段名@ColumnInfo(name = "xxx")
    @ColumnInfo(name = "location_name") val name: String,
    @ColumnInfo(name = "location_url") val url: String
)
data class Origin(
    @ColumnInfo(name = "origin_name") val name: String,
    @ColumnInfo(name = "origin_url") val url: String
)