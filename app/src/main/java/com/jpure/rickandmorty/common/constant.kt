package com.jpure.rickandmorty.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

/**
 * @author Pure Jiang
 * @date 2021/2/26.
 */
const val DATABASE_NAME = "ram-db"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "other")