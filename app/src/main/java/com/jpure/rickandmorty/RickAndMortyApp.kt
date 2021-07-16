package com.jpure.rickandmorty

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.nice.baselibrary.base.utils.LogUtils
import dagger.hilt.android.HiltAndroidApp

/**
 * @author Pure Jiang
 * @date 2021/3/4.
 */
@HiltAndroidApp
class RickAndMortyApp:Application() {

    override fun onCreate() {
        super.onCreate()
        LogUtils.init(this, true)
    }
}