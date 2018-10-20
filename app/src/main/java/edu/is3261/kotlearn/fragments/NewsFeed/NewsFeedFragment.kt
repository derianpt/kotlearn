package edu.is3261.kotlearn.fragments.NewsFeed

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter
import com.twitter.sdk.android.tweetui.UserTimeline

import edu.is3261.kotlearn.R

class NewsFeedFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var userTimeline: UserTimeline

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_news_feed, container, false)

        // Lookup the swipe refresh container view
        val swipeContainer = view.findViewById<SwipeRefreshLayout>(R.id.news_swipe_refresh)

        Toast.makeText(context,getString(R.string.social_feed_placeholder), Toast.LENGTH_SHORT).show()

        // init user timeline
        userTimeline = UserTimeline.Builder().screenName("kotlin").build()

        // present data on view
        initTwitterFeed(view)

        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener {
            Toast.makeText(context,getString(R.string.social_feed_placeholder), Toast.LENGTH_SHORT).show()
            // This method performs the actual data-refresh operation.
            initTwitterFeed(view)
        }

        return view
    }

    private fun initTwitterFeed(view: View){
        viewManager = LinearLayoutManager(context)
        viewAdapter = TweetTimelineRecyclerViewAdapter.Builder(this.context)
                .setTimeline(userTimeline)
                .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                .build();
//       viewAdapter = TweetTimelineListAdapter.Builder(this.context)
//               .setTimeline(userTimeline)
//               .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
//               .build()
        recyclerView = view.findViewById<RecyclerView>(R.id.news_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager


            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
        view.findViewById<SwipeRefreshLayout>(R.id.news_swipe_refresh).isRefreshing = false
        Toast.makeText(context, context?.getString(R.string.social_feed_done), Toast.LENGTH_SHORT).show()
    }
}

