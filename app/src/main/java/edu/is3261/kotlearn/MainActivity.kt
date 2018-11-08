package edu.is3261.kotlearn

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig
import edu.is3261.kotlearn.fragments.Quiz.QuizLandingFragment
import edu.is3261.kotlearn.fragments.RedditFeed.RedditParentFragment
import edu.is3261.kotlearn.fragments.TwitterFeed.TwitterParentFragment


class MainActivity : AppCompatActivity() {

    val manager = supportFragmentManager
    private val mOnNavigationItemSelectorException = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.bottom_navigation_social -> {
                createRedditFeedFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_navigation_news -> {
                createTwitterFeedFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_navigation_quiz -> {
                createQuizLandingFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_navigation_about -> {

            }
        }
        false
    }
    lateinit var toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v("", "started")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            createRedditFeedFragment()
        }
        toolbar = supportActionBar!!
        toolbar.hide()
        // changing color of ActionBar
//        toolbar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#003366")))
//        toolbar.setIcon(R.drawable.basics)
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectorException)
        // initialise twitter client
        initTwitter()
    }

    fun createRedditFeedFragment() {
        val transaction = manager.beginTransaction()
        val fragment = RedditParentFragment()
        transaction.replace(R.id.fragmentholder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun createTwitterFeedFragment() {
        val transaction = manager.beginTransaction()
        val fragment = TwitterParentFragment()
        transaction.replace(R.id.fragmentholder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun createQuizLandingFragment() {
        val transaction = manager.beginTransaction()
        val fragment = QuizLandingFragment()
        transaction.replace(R.id.fragmentholder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun initTwitter() {
        // initialize twitter feed for later use! Takes CONSUMER_KEY and CONSUMER_SECRET defined in
        // secrets.xml
        val authConfig = TwitterAuthConfig(resources.getString(R.string.TWITTER_CONSUMER_KEY),
                resources.getString(R.string.TWITTER_CONSUMER_SECRET))
        val config = TwitterConfig.Builder(this)
                .logger(DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(authConfig)
                .debug(true)
                .build()
        Twitter.initialize(config)
    }
}
