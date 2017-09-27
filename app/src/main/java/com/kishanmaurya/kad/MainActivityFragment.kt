package com.kishanmaurya.kad

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kishanmaurya.kad.base.RxBaseFragment
import com.kishanmaurya.kad.commons.InfiniteScrollListener
import com.kishanmaurya.kad.commons.ItemClickListener
import com.kishanmaurya.kad.commons.inflate
import com.kishanmaurya.kad.news.News
import com.kishanmaurya.kad.news.NewsAdapter
import com.kishanmaurya.kad.news.NewsManager
import kotlinx.android.synthetic.main.fragment_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : RxBaseFragment()
{
    private var newsList: News? = null
    private val newsManager by lazy { NewsManager() }
    private var listener : ItemClickListener? =null


    // companion is used to declare static variable
    companion object
    {
        private val NEWS_KEY = "newsData"
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) = container?.inflate(R.layout.fragment_main)

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        val linearLayout = LinearLayoutManager(context)
        news_list.layoutManager = linearLayout;
        initAdapter()
        news_list.setHasFixedSize(true)
        news_list.clearOnScrollListeners()
        news_list.addOnScrollListener(InfiniteScrollListener({ requestNews() }, linearLayout))

        news_list.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ requestNews() }, linearLayout))
        }


        if(savedInstanceState != null && savedInstanceState.containsKey(NEWS_KEY))
        {
            newsList = savedInstanceState.get(NEWS_KEY) as News
            (news_list.adapter as NewsAdapter).clearAndAddNews(newsList!!.newsList)
        }
        else
        {
            requestNews()
        }
    }

    override fun onAttach(context: Context?)
    {
        super.onAttach(context)
        if( context is ItemClickListener)
        {
            listener  = context
        }

    }


    private fun initAdapter()
    {
        if(news_list.adapter == null)
        {
            news_list.adapter = NewsAdapter(listener!!)
        }
    }


    private fun requestNews()
    {
        val subscription = newsManager.getNews(newsList?.after ?: "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrievedNews ->
                            newsList = retrievedNews
                            (news_list.adapter as NewsAdapter).addNews(retrievedNews.newsList)
                        },
                        { e ->
                            Snackbar.make(news_list, e.message ?: "", Snackbar.LENGTH_LONG).show()
                        }
                )

        subscriptions.add(subscription)
    }

    override fun onSaveInstanceState(outState: Bundle?)
    {
        super.onSaveInstanceState(outState)
        val saveNewsList = (news_list.adapter as NewsAdapter).getNews()
        if(newsList != null && saveNewsList.isNotEmpty())
        {
            outState!!.putParcelable(NEWS_KEY, newsList?.copy(newsList = saveNewsList))
        }
    }

    override fun onDetach()
    {
        super.onDetach()
        listener =null
    }

}
