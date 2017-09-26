package com.kishanmaurya.kad.news

import android.os.Parcel
import android.os.Parcelable
import com.kishanmaurya.kad.commons.AdapterConstants
import com.kishanmaurya.kad.commons.ViewType
import com.kishanmaurya.kad.commons.createParcel

/**
 * Created by kishanmaurya on 25/9/17.
 */
data class NewsItem(
        val author: String,
        val title: String,
        val numComments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String) : ViewType, Parcelable
{

    // JvmField is used for visibility of CREATOR in java files
    companion object
    {
        @JvmField
        @Suppress("unused")
        val CREATOR = createParcel { NewsItem(it) }

    }

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeInt(numComments)
        parcel.writeLong(created)
        parcel.writeString(thumbnail)
        parcel.writeString(url)
    }
    override fun describeContents() = 0
    override fun getViewType() = AdapterConstants.NEWS
}


data class News(
        val after: String,
        val before: String,
        val newsList: List<NewsItem>) : Parcelable
{
    companion object {
        @JvmField @Suppress("unused")
        val CREATOR = createParcel { News(it) }
    }

    protected constructor(parcelIn: Parcel) : this(
            parcelIn.readString(),
            parcelIn.readString(),
            mutableListOf<NewsItem>().apply {
                parcelIn.readTypedList(this, NewsItem.CREATOR)
            }
    )

    override fun writeToParcel(dest: Parcel, p1: Int)
    {
        dest.writeString(after)
        dest.writeString(before)
        dest.writeTypedList(newsList)
    }

    override fun describeContents() = 0
}