package edu.is3261.kotlearn.fragments.TwitterFeed


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twitter.sdk.android.tweetui.SearchTimeline
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter
import edu.is3261.kotlearn.R

class HashtagTimelineFragment : Fragment() {

    private lateinit var hashtagTimeline: SearchTimeline

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_twitter_hashtag_timeline, container, false)

        // Lookup the swipe refresh container view
        val swipeContainer = view.findViewById<SwipeRefreshLayout>(R.id.hashtag_timeline_swipe_refresh)

        // init hashtag timeline. don't put this in initHashtagTimeline because we should not reapeatedly do this.
        hashtagTimeline = SearchTimeline.Builder().query("#kotlin").build()

        // present data on view
        initHashtagTimeline(view)

        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener {
            // This method performs the actual data-refresh operation.
            initHashtagTimeline(view)
        }

        return view
    }

    private fun initHashtagTimeline(view: View) {
        var isNight = TwitterParentFragment.isItNightNow(this)

        // Adapter for recycler view
        var viewAdapter: TweetTimelineRecyclerViewAdapter
        if (isNight) {
            viewAdapter = TweetTimelineRecyclerViewAdapter.Builder(this.context)
                    .setTimeline(hashtagTimeline)
                    .setViewStyle(R.style.tw__TweetDarkStyle)
                    .build()
        } else {
            viewAdapter = TweetTimelineRecyclerViewAdapter.Builder(this.context)
                    .setTimeline(hashtagTimeline)
                    .build()
        }

        // configure the recycler view contains our timeline.
        view.findViewById<RecyclerView>(R.id.hashtag_timeline_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager for this recycler view
            layoutManager = LinearLayoutManager(context)


            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
        view.findViewById<SwipeRefreshLayout>(R.id.hashtag_timeline_swipe_refresh).isRefreshing = false
    }


}
