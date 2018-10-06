package edu.is3261.kotlearn.json

import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.TextView
import net.dean.jraw.RedditClient
import net.dean.jraw.http.OkHttpNetworkAdapter
import net.dean.jraw.http.UserAgent
import net.dean.jraw.models.*
import net.dean.jraw.oauth.Credentials
import net.dean.jraw.oauth.OAuthHelper
import java.io.PrintWriter
import java.io.StringWriter
import java.util.*


class RedditFeed(private var view: TextView) : AsyncTask<Void, Void, String>() {
    override fun doInBackground(vararg params: Void?): String {
        val redditClient = auth()

        if (redditClient != null) {
            val content = pullSubredditInfo(redditClient)
            if (content.isBlank() || content.isEmpty()) {
                return "content is blank or empty!!"
            }
            return content
        } else {
            return "reddit client couldn't be initialized!"
        }
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        view.text = result
    }

    fun auth(): RedditClient? {
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

    fun pullSubredditInfo(redditClient: RedditClient): String {
        val kotlinSubreddit = redditClient.subreddit("kotlin")
        val subredditFrontPage = kotlinSubreddit.posts()
                .limit(10)
                .sorting(SubredditSort.HOT)
                .timePeriod(TimePeriod.DAY)
                .build()
        val posts: Listing<Submission> = subredditFrontPage.next()
        var content = ""
        for (post in posts) {
            content = content.plus("Title: "+post.title).plus("\n")
            content = content.plus("Author: "+post.author).plus("\n")
            content = content.plus("URL:"+post.url).plus("\n")
            content = content.plus("\n\n")
        }
        return content
    }
}
