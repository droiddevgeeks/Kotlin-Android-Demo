@file:JvmName("ExtensionsUtils")

package com.kishanmaurya.kad.commons

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.kishanmaurya.kad.R
import com.squareup.picasso.Picasso

/**
 * Created by kishanmaurya on 25/9/17.
 */

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false) = LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun ImageView.loadImg(imageUrl: String)
{
    if(TextUtils.isEmpty(imageUrl))
    {
        Picasso
                .with(context)
                .load(R.mipmap.ic_launcher)
                .into(this)
    }
    else
    {
        Picasso
                .with(context)
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(this)
    }
}

inline fun <reified T : Parcelable> createParcel(
        crossinline createFromParcel: (Parcel) -> T?): Parcelable.Creator<T> = object : Parcelable.Creator<T>
        {
            override fun createFromParcel(source: Parcel): T? = createFromParcel(source)
            override fun newArray(size: Int): Array<out T?> = arrayOfNulls(size)
        }

fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()