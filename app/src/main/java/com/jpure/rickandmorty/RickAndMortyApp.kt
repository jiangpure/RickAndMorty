package com.jpure.rickandmorty

import android.app.Application
import com.nice.baselibrary.base.utils.LogUtils
import dagger.hilt.android.HiltAndroidApp

/**
 * @author Jp
 * @date 2021/3/4.
 */
@HiltAndroidApp
class RickAndMortyApp:Application() {
    override fun onCreate() {
        super.onCreate()
        LogUtils.init(this, true)
    }
}