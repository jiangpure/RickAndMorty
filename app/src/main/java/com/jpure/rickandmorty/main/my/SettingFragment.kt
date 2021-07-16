package com.jpure.rickandmorty.main.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.preference.PreferenceFragmentCompat
import com.jpure.rickandmorty.R

/**
 * @author Pure Jiang
 * @date 2021/5/12.
 */
class SettingFragment: PreferenceFragmentCompat() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //这里重写根据PreferenceFragmentCompat 的布局 ，往他的根布局插入了一个toolbar
        val containerView = view.findViewById<FrameLayout>(android.R.id.list_container)
        containerView.let {
            val linearLayout = it.parent as? LinearLayout
            linearLayout?.run {
                val toolbarView =  LayoutInflater.from(activity).inflate(R.layout.include_toolbar, null)
                toolbarView.findViewById<Toolbar>(R.id.settingToolbar).apply{
                    //添加到第一个
                    title = getString(R.string.public_setting)
                    setNavigationOnClickListener { view.findNavController().navigateUp() }
                }
                addView(toolbarView, 0)
            }
        }
    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting_default, rootKey)
    }
}