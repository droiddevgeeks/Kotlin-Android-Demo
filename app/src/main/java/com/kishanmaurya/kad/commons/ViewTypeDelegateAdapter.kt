package com.kishanmaurya.kad.commons

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by kishanmaurya on 25/9/17.
 */
interface ViewTypeDelegateAdapter
{
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}