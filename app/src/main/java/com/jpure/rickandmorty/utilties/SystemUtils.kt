package com.jpure.rickandmorty.utilties

import android.annotation.TargetApi
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager

/**
 * @author Pure Jiang
 * @date 2021/6/30.
 */
class SystemUtils {
    companion object {
        /**
         * 全屏
         * @param window 传入activity的window
         */
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public fun hideSystemUi(window: Window) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
            window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_IMMERSIVE // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN // Hide the nav bar and status bar
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN)
            window.decorView.setOnSystemUiVisibilityChangeListener {
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
                window.decorView.systemUiVisibility =
                    (View.SYSTEM_UI_FLAG_IMMERSIVE // Set the content to appear under the system bars so that the
                            // content doesn't resize when the system bars hide and show.
                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN // Hide the nav bar and status bar
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN)
            }
        }
    }
}