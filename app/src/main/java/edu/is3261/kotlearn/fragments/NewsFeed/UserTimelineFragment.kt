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
import kotlinx.android.synthetic.main.fragment_user_timeline.view.*

class UserTimelineFragment : Fragment() {

    private lateinit var userTimeline: UserTimeline

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_timeline, container, false)

        // Lookup the swipe refresh container view
        val swipeContainer = view.findViewById<SwipeRefreshLayout>(R.id.user_timeline_swipe_refresh)

        Toast.makeText(context, getString(R.string.loading_feed), Toast.LENGTH_SHORT).show()

        // init user timeline. don't put this in initUserTimeline because we should not reapeatedly do this.
        userTimeline = UserTimeline.Builder().screenName("kotlin").build()

        // present data on view
        initUserTimeline(view)

        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener {
            Toast.makeText(context, getString(R.string.loading_feed), Toast.LENGTH_SHORT).show()
            // This method performs the actual data-refresh operation.
            initUserTimeline(view)
        }

        return view
    }

    private fun initUserTimeline(view: View) {
        var viewAdapter = TweetTimelineRecyclerViewAdapter.Builder(this.context)
                .setTimeline(userTimeline)
                .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                .build();
        view.findViewById<RecyclerView>(R.id.user_timeline_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager for this recycler view
            layoutManager = LinearLayoutManager(context)

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
        view.findViewById<SwipeRefreshLayout>(R.id.user_timeline_swipe_refresh).isRefreshing = false
        Toast.makeText(context, context?.getString(R.string.loaded_feed), Toast.LENGTH_SHORT).show()
    }

}
