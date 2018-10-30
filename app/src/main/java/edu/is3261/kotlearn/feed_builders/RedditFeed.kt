package edu.is3261.kotlearn.feed_builders

import android.content.Context
import android.os.AsyncTask
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import edu.is3261.kotlearn.R
import edu.is3261.kotlearn.adapters.RedditFeedAdapter
import net.dean.jraw.RedditClient
import net.dean.jraw.http.OkHttpNetworkAdapter
import net.dean.jraw.http.UserAgent
import net.dean.jraw.models.Listing
import net.dean.jraw.models.Submission
import net.dean.jraw.models.SubredditSort
import net.dean.jraw.models.TimePeriod
import net.dean.jraw.oauth.Credentials
import net.dean.jraw.oauth.OAuthHelper
import net.dean.jraw.pagination.DefaultPaginator
import java.io.PrintWriter
import java.io.StringWriter
import java.util.*
import kotlin.collections.ArrayList


class RedditFeed(var context: Context, var view: View, var subreddit: String) : AsyncTask<Void, Void, DefaultPaginator<Submission>>() {

    private lateinit var feedList: ArrayList<RedditPost>
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun doInBackground(vararg params: Void?): DefaultPaginator<Submission> {
        val redditClient = initRedditClient()
        return pullSubredditInfo(redditClient!!)
    }

    override fun onPostExecute(result: DefaultPaginator<Submission>) {
        super.onPostExecute(result)

        viewManager = LinearLayoutManager(context)
        viewAdapter = RedditFeedAdapter(feedList)
        if (subreddit.equals("kotlin")) {
            recyclerView = view.findViewById(R.id.kotlin_subreddit_recycler_view)
        } else {
            recyclerView = view.findViewById(R.id.android_subreddit_recycler_view)
        }
        recyclerView.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
        if (subreddit.equals("kotlin")) {
            view.findViewById<SwipeRefreshLayout>(R.id.kotlin_subreddit_swipe_refresh).isRefreshing = false
        } else {
            view.findViewById<SwipeRefreshLayout>(R.id.android_subreddit_swipe_refresh).isRefreshing = false
        }

        Toast.makeText(context, context.getString(R.string.loaded_feed), Toast.LENGTH_SHORT).show()
    }

    // initialize reddit client by requesting for access token for userless app.
    fun initRedditClient(): RedditClient? {
        val userAgent = UserAgent("android", "edu.is3261.kotlearn.fragments.SocialFeed",
                "v0.1", "kotlearn")

        // This is what really sends HTTP requests to Reddit API
        val adapter = OkHttpNetworkAdapter(userAgent)

        // Authenticate and get a RedditClient instance
        val redditClient = try {
            OAuthHelper.automatic(adapter,
                    Credentials.userlessApp(context.resources.getString(R.string.REDDIT_APP_ID), UUID.randomUUID()))
        } catch (e: Exception) {
            val sw = StringWriter()
            e.printStackTrace(PrintWriter(sw))
            Log.d("clientCreateStack", sw.toString())
            null
        }
        return redditClient
    }

    // pull the relevant info from reddit API, returning a paginator
    fun pullSubredditInfo(redditClient: RedditClient): DefaultPaginator<Submission> {
        val kotlinSubreddit = redditClient.subreddit(subreddit)
        val subredditPaginator = kotlinSubreddit.posts()
                .limit(10)
                .sorting(SubredditSort.TOP)
                .timePeriod(TimePeriod.WEEK)
                .build()
        feedList = getNextPageAsArrayListOfPosts(subredditPaginator)
        return subredditPaginator
    }

    // calculates the time difference between current date and created date. Returns the appropriate formatted string.
    fun calculateAgo(created: Date): String {
        var curr = Calendar.getInstance().time
        var posted = created
        //in milliseconds
        val diff = curr.getTime() - posted.getTime()
        val diffMinutes = diff / (60 * 1000) % 60
        val diffHours = diff / (60 * 60 * 1000) % 24
        val diffDays = diff / (24 * 60 * 60 * 1000)

        // if difference is lesser than an hour, view minutes
        if (diffHours.compareTo(1) < 0) {
            return "${diffMinutes} minutes ago"
        }
        // else if difference is lesser than a day, view hours
        if (diffDays.compareTo(1) < 0) {
            return "${diffHours} hours ago"
        }
        // else view days
        return "${diffDays} days ago"
    }

    // getNextPageAsArrayListOfPosts extracts the next page from this paginator, initializes all
    // submissions as posts and return an arraylist of RedditPosts
    fun getNextPageAsArrayListOfPosts(subredditPaginator: DefaultPaginator<Submission>): ArrayList<RedditPost> {
        val submissions: Listing<Submission> = subredditPaginator.next()
        val posts: ArrayList<RedditPost> = ArrayList()
        for (submission in submissions) {
            var currPost = RedditPost(submission.title, "By ${submission.author}", calculateAgo(submission.created),
                    "https://reddit.com${submission.permalink}")
            posts.add(currPost)
        }
        return posts
    }
}
