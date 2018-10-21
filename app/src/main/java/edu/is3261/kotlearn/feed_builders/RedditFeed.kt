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
import edu.is3261.kotlearn.adapters.MyAdapter
import net.dean.jraw.RedditClient
import net.dean.jraw.http.OkHttpNetworkAdapter
import net.dean.jraw.http.UserAgent
import net.dean.jraw.models.Listing
import net.dean.jraw.models.Submission
import net.dean.jraw.models.SubredditSort
import net.dean.jraw.models.TimePeriod
import net.dean.jraw.oauth.Credentials
import net.dean.jraw.oauth.OAuthHelper
import java.io.PrintWriter
import java.io.StringWriter
import java.util.*
import kotlin.collections.ArrayList


class RedditFeed(var context: Context, var view: View) : AsyncTask<Void, Void, String>() {

    lateinit var apiResult: ArrayList<RedditPost>
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun doInBackground(vararg params: Void?): String {
        val redditClient = initRedditClient()

        if (redditClient != null) {
            apiResult = pullSubredditInfo(redditClient)
            if (apiResult.isEmpty()) {
                Log.d("apiResult", apiResult.toString())
                return "api result is empty!!"
            }
            Log.d("apiResult", apiResult.toString())
            return "success"
        } else {
            return "reddit client couldn't be initialized!"
        }
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        if (!result.equals("success")) {
            Toast.makeText(context, result, Toast.LENGTH_SHORT)
            return
        }

        viewManager = LinearLayoutManager(context)
        viewAdapter = MyAdapter(apiResult)
        recyclerView = view.findViewById<RecyclerView>(R.id.social_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
        view.findViewById<SwipeRefreshLayout>(R.id.social_swipe_refresh).isRefreshing = false
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
                    Credentials.userlessApp("wvK0dL-fBig2-A", UUID.randomUUID()))
        } catch (e: Exception) {
            val sw = StringWriter()
            e.printStackTrace(PrintWriter(sw))
            Log.d("clientCreateStack", sw.toString())
            null
        }
        return redditClient
    }

    // pull the relevant info from reddit API, returning a list of RedditPost objects
    fun pullSubredditInfo(redditClient: RedditClient): ArrayList<RedditPost> {
        val kotlinSubreddit = redditClient.subreddit("kotlin")
        val subredditFrontPage = kotlinSubreddit.posts()
                .limit(10)
                .sorting(SubredditSort.HOT)
                .timePeriod(TimePeriod.WEEK)
                .build()
        val posts: Listing<Submission> = subredditFrontPage.next()
        var feedList = ArrayList<RedditPost>()
        for (post in posts) {
//            if (post.hasThumbnail()){
//                Log.d("thumbnail", post.thumbnail)
//            }else{
//                Log.d("thumbnail", "NO THUMBNAIL FOR ${post.title}")
//            }
//
//            if (post.embeddedMedia != null){
//                Log.d("embedded", post.embeddedMedia.toString())
//            }else{
//                Log.d("embedded", "NO EMBEDDED FOR ${post.title}")
//            }

            var currPost = RedditPost(post.title, "By ${post.author}", calculateAgo(post.created),
                    "https://reddit.com${post.permalink}")
            feedList.add(currPost)
        }
        return feedList
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
}
