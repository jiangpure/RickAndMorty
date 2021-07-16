package com.jpure.rickandmorty.ext

import android.graphics.Typeface
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jpure.rickandmorty.R
import com.jpure.rickandmorty.data.entities.Role

/**
 * @author Pure Jiang
 * @date 2021/3/5.
 */
/**
 * 加载网络图片
 * @param view imgView
 * @param imageUrl 需要加载的url
 */
@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .skipMemoryCache(false)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.mipmap.ic_launcher_round)
            .into(view)
    }
}

@BindingAdapter("parseStatus")
fun roleStatus(view: TextView, status:String){
    if(status == "Alive"){
        view.setTextColor(ContextCompat.getColor(view.context, R.color.green))
    }
    view.text = status
}

/**
 * int的转换，避免dataBinding中text绑定int导致的闪退
 */
@BindingAdapter("setInt")
fun int2Str(view: TextView, id:Int){
    view.text = "$id"
}
