package com.jpure.rickandmorty.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jpure.rickandmorty.data.entities.Role
import com.jpure.rickandmorty.utilties.fromJsonToObjNotExe

/**
 * @author Jp
 * @date 2021/2/26.
 */
open class Converters {
    @TypeConverter
    fun fromSources(value: String?): MutableMap<String, MutableList<Role>>? {
        return fromJsonToObjNotExe(value,  object : TypeToken<MutableMap<String, MutableList<Role>>>(){}.type)
    }

    @TypeConverter
    fun sourcesToString(values:MutableMap<String, MutableList<Role>>?): String? {
        return Gson().toJson(values)
    }

    @TypeConverter
    fun fromList(value: String?):MutableList<String>? {
        return fromJsonToObjNotExe(value, object : TypeToken<MutableList<String>>(){}.type)
    }

    @TypeConverter
    fun listToString(values:MutableList<String>?): String? {
        return Gson().toJson(values)
    }
}