package edu.is3261.kotlearn.fragments.RedditFeed


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import edu.is3261.kotlearn.R
import edu.is3261.kotlearn.feed_builders.RedditFeed


class KotlinSubredditFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_reddit_kotlin_subreddit, container, false)

        val sortBy = this.parentFragment!!.view!!.findViewById<TextView>(R.id.reddit_parent_sort_by_result)!!.text.toString()
        Toast.makeText(context, getString(R.string.loading_feed), Toast.LENGTH_SHORT).show()

        // populate the feed by 1) downloading data from reddit and 2) populating swipeContainer
        // pass in context for toasting errors
        RedditFeed(this.activity!!.applicationContext, view, "kotlin", sortBy).execute()

        // Lookup the swipe refresh container view
        val swipeContainer = view.findViewById<SwipeRefreshLayout>(R.id.kotlin_subreddit_swipe_refresh)
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener {
            Log.i("swiperefresh", "onRefresh called from SwipeRefreshLayout")
            Toast.makeText(context, getString(R.string.loading_feed), Toast.LENGTH_SHORT).show()
            // This method performs the actual data-refresh operation.
            RedditFeed(this.activity!!.applicationContext, view, "kotlin", sortBy).execute()
        }
        return view
    }

}
