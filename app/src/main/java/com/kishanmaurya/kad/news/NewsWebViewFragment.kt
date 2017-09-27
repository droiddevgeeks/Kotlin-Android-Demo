package com.kishanmaurya.kad.news

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.kishanmaurya.kad.R
import com.kishanmaurya.kad.commons.inflate
import kotlinx.android.synthetic.main.news_webview.*

/**
 * Created by kishanmaurya on 27/9/17.
 */
class NewsWebViewFragment : Fragment()
{

    private var mPageUrl: String? = null
    companion object
    {

        fun newInstance(url: String): NewsWebViewFragment
        {
            val args = Bundle()
            args.putString("NEWSURL", url)
            val fragment = NewsWebViewFragment()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mPageUrl = arguments.getString("NEWSURL")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) = container!!.inflate(R.layout.news_webview)
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        news_web_view.webViewClient  = WebViewClient()
        news_web_view.loadUrl(mPageUrl)


    }
}