package com.kishanmaurya.kad.base

import android.support.v4.app.Fragment
import rx.subscriptions.CompositeSubscription

/**
 * Created by kishanmaurya on 25/9/17.
 */
open class RxBaseFragment : Fragment()
{

    protected var subscriptions = CompositeSubscription()

    override fun onResume()
    {
        super.onResume()
        subscriptions = CompositeSubscription()
    }

    override fun onPause()
    {
        super.onPause()
        if(!subscriptions.isUnsubscribed)
        {
            subscriptions.unsubscribe()
        }
        subscriptions.clear()
    }
}