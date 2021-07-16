package com.jpure.rickandmorty

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.jpure.rickandmorty.common.dataStore
import com.jpure.rickandmorty.databinding.ActivitySplashBinding
import com.jpure.rickandmorty.utilties.SystemUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

/**
 * @author Pure Jiang
 * @date 2021/4/28.
 */
@FlowPreview
@AndroidEntryPoint
@ExperimentalCoroutinesApi
class SplashActivity : AppCompatActivity() {

    private val EXAMPLE_COUNTER = intPreferencesKey("first")

    override fun onCreate(savedInstanceState: Bundle?) {
        SystemUtils.hideSystemUi(window);
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)

        // DataStore的方式进行程序打开次数的存储和读取
        lifecycleScope.launchWhenCreated {
            incrementCounter()
            dataStore.data
                .map { preferences ->
                    // No type safety.
                    preferences[EXAMPLE_COUNTER] ?: 0
                }.flowOn(Dispatchers.IO).first {
                if(it>0){
                    jumpToMain(2000)
                }else{
                    jumpToMain(5000)
                }
                true
            }
        }

    }

    private fun jumpToMain(delayTime: Long) {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }, delayTime)
    }

    /**
     * 写入程序打开次数
     */
    private suspend fun incrementCounter() {
        this.dataStore.edit { trans ->
            val currentCounterValue = trans[EXAMPLE_COUNTER] ?: 0
            trans[EXAMPLE_COUNTER] = currentCounterValue + 1
        }
    }
}