package com.kishanmaurya.kad.news

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.kishanmaurya.kad.R
import com.kishanmaurya.kad.commons.ViewType
import com.kishanmaurya.kad.commons.ViewTypeDelegateAdapter
import com.kishanmaurya.kad.commons.inflate

/**
 * Created by kishanmaurya on 25/9/17.
 */
class LoadingDelegateAdapter : ViewTypeDelegateAdapter
{
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = TurnsViewHolder(parent)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
    {

    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.news_item_loading))
    {
    }
}