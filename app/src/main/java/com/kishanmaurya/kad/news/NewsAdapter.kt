package com.kishanmaurya.kad.news

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.kishanmaurya.kad.commons.AdapterConstants
import com.kishanmaurya.kad.commons.ItemClickListener
import com.kishanmaurya.kad.commons.ViewType
import com.kishanmaurya.kad.commons.ViewTypeDelegateAdapter

/**
 * Created by kishanmaurya on 25/9/17.
 */
class NewsAdapter(listener: ItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object : ViewType
    {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init
    {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.NEWS, NewsDelegateAdapter(listener))
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int)
    {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder!!, items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = delegateAdapters.get(viewType).onCreateViewHolder(parent!!)
    override fun getItemCount() = items.size
    override fun getItemViewType(position: Int) = items[position].getViewType()

    fun addNews(news: List<NewsItem>)
    {
        // first remove loading and notify
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        // insert news and the loading at the end of the list
        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1)
    }

    fun clearAndAddNews(news: List<NewsItem>)
    {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    fun getNews(): List<NewsItem> = items.filter { it.getViewType() == AdapterConstants.NEWS }.map { it as NewsItem }
    private fun getLastPosition() = if(items.lastIndex == -1) 0 else items.lastIndex
}