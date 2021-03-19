package com.jpure.rickandmorty.utilties

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type


/**
 * @author JPlus
 * @date 2020/6/2.
 */

fun <T> fromJsonToObjNotExe(json: String?, type: Type): T? {
    var t: T? = null
    try {
        t = Gson().fromJson<T>(json, type)
    } catch (e: JsonParseException) {
        e.printStackTrace()
    }
    return t
}

fun <T> fromJsonToObjNotExe(json: String?, clazz: Class<T>): T? {
    var t: T? = null
    try {
        t = Gson().fromJson(json, clazz)
    } catch (e: JsonParseException) {
        e.printStackTrace()
    }
    return t
}
fun <T> fromJsonToObjNotExe(json: JsonElement?, clazz: Class<T>): T? {
    var t: T? = null
    try {
        t = Gson().fromJson(json, clazz)
    } catch (e: JsonParseException) {
        e.printStackTrace()
    }
    return t
}
fun <T> fromJsonToObjNotExe(json: JsonElement?, type: Type): T? {
    var t: T? = null
    try {
        t = Gson().fromJson<T>(json, type)
    } catch (e: JsonParseException) {
        e.printStackTrace()
    }
    return t
}