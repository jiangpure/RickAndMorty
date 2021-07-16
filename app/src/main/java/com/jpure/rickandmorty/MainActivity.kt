package com.jpure.rickandmorty

import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.jpure.rickandmorty.databinding.ActivityMainBinding
import com.nice.baselibrary.base.utils.showNormalToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview


/**
 * @author Pure Jiang
 * @date 2021/2/23.
 */

@FlowPreview
@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    private var mExitTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val nav = Navigation.findNavController(this@MainActivity, R.id.nav_host)
                if (nav.currentDestination != null && nav.currentDestination?.id != R.id.main_fragment) {
                    //如果当前界面不是主页，那么直接调用返回即可
                    nav.navigateUp()
                } else {
                    if (System.currentTimeMillis() - mExitTime > 2000) {
                        mExitTime = System.currentTimeMillis()
                        showNormalToast("再按一次退出程序")
                    } else {
                        finish()
                    }
                }
            }
        })
    }
}