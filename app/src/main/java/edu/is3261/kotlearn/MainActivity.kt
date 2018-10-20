package edu.is3261.kotlearn

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.twitter.sdk.android.core.Twitter
import edu.is3261.kotlearn.fragments.NewsFeed.NewsFeedFragment
import edu.is3261.kotlearn.fragments.Quiz.QuizLandingFragment
import edu.is3261.kotlearn.fragments.SocialFeed.SocialFeedFragment
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.TwitterConfig



class MainActivity : AppCompatActivity() {

    val manager = supportFragmentManager
    private val mOnNavigationItemSelectorException = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.bottom_navigation_social -> {
                createSocialFeedFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_navigation_news -> {
                createNewsFeedFragment()
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
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            createSocialFeedFragment()
        }
        toolbar = supportActionBar!!
        // changing color of ActionBar
        toolbar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#003366")))
        toolbar.setIcon(R.drawable.basics)
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectorException)

        // initialize twitter feed for later use! Takes CONSUMER_KEY and CONSUMER_SECRET defined in
        // secrets.xml
        val authConfig = TwitterAuthConfig(resources.getString(R.string.twitter_CONSUMER_KEY), resources.getString(R.string.twitter_CONSUMER_SECRET))
        Log.d("mainActivity", "authConfig: ${authConfig.consumerKey} , ${authConfig.consumerSecret}")
        val config = TwitterConfig.Builder(this)
                .logger(DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(authConfig)
                .debug(true)
                .build()
        Twitter.initialize(config)
    }

    fun createSocialFeedFragment() {
        val transaction = manager.beginTransaction()
        val fragment = SocialFeedFragment()
        transaction.replace(R.id.fragmentholder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun createNewsFeedFragment() {
        val transaction = manager.beginTransaction()
        val fragment = NewsFeedFragment()
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
}
