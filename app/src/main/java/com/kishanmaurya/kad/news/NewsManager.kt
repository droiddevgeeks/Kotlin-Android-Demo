package com.kishanmaurya.kad.news

import com.kishanmaurya.kad.api.RestAPI
import rx.Observable

/**
 * Created by kishanmaurya on 25/9/17.
 */
class NewsManager(private val api: RestAPI = RestAPI())
{

    fun getNews(after: String, limit: String = "10"): Observable<News>
    {
        return Observable.create {
            subscriber ->
            val callResponse = api.getNews(after, limit)
            val response = callResponse.execute()
            if(response.isSuccessful)
            {
                val dataResponse = response.body().data
                val news = dataResponse.children.map {
                    val item = it.data
                    NewsItem(item.author, item.title, item.num_comments, item.created, item.thumbnail, item.url)
                }

                val newsList = News(dataResponse.after ?: "", dataResponse.before ?: "", news)
                subscriber.onNext(newsList)
                subscriber.onCompleted()

            }
            else
            {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }

}