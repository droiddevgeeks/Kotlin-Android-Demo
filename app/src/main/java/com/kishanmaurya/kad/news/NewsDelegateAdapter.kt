package com.kishanmaurya.kad.news

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.kishanmaurya.kad.R
import com.kishanmaurya.kad.commons.*
import kotlinx.android.synthetic.main.news_item.view.*

/**
 * Created by kishanmaurya on 25/9/17.
 */
class NewsDelegateAdapter(val viewActions: ItemClickListener) : ViewTypeDelegateAdapter
{


    override fun onCreateViewHolder(parent: ViewGroup) = NewsViewHolder(parent)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
    {
        holder as NewsViewHolder
        holder.bind(item as NewsItem)
    }

    inner class NewsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.news_item))
    {
        fun bind(item: NewsItem) = with(itemView)
        {
            img_thumbnail.loadImg(item.thumbnail)
            description.text = item.title
            author.text = item.author
            time.text = "${item.created}"
            comments.text = "${item.numComments} comments"
            super.itemView.setOnClickListener { viewActions.onItemSelected(item.url) }
        }
    }
}