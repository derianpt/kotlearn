package edu.is3261.kotlearn.fragments.SocialFeed


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import edu.is3261.kotlearn.R
import edu.is3261.kotlearn.feed.RedditFeed

class SocialFeedFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_social_feed, container, false)

        // Lookup the swipe refresh container view
        val swipeContainer = view.findViewById<SwipeRefreshLayout>(R.id.social_swipe_refresh)

        Toast.makeText(context,getString(R.string.social_feed_placeholder), Toast.LENGTH_SHORT).show()

        // populate the feed by 1) downloading data from reddit and 2) populating swipeContainer
        // pass in context for toasting errors
        RedditFeed(this.activity!!.applicationContext, view).execute()

        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener {
            Log.i("swiperefresh", "onRefresh called from SwipeRefreshLayout")
            Toast.makeText(context,getString(R.string.social_feed_placeholder), Toast.LENGTH_SHORT).show()
            // This method performs the actual data-refresh operation.
            RedditFeed(this.activity!!.applicationContext, view).execute()
        }

        return view
    }
}
