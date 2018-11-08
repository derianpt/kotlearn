package edu.is3261.kotlearn

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.support.v7.app.AppCompatDelegate.*
import android.util.Log
import android.view.Menu
import android.widget.Switch
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig
import edu.is3261.kotlearn.fragments.Quiz.QuizLandingFragment
import edu.is3261.kotlearn.fragments.RedditFeed.RedditParentFragment
import edu.is3261.kotlearn.fragments.TwitterFeed.TwitterParentFragment


class MainActivity : AppCompatActivity() {

    val manager = supportFragmentManager
    private val mOnNavigationItemSelector = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.bottom_navigation_reddit -> {
                createRedditFeedFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_navigation_twitter -> {
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

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            createRedditFeedFragment()
        }
        setContentView(R.layout.activity_main)

        // init action bar
        setSupportActionBar(findViewById(R.id.action_bar))

        // init bottom nav
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelector)

        // initialise twitter client
        initTwitter()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar, menu)
        if (menu == null) {
            Log.d("main", "menu is null anyway")
        }

        var menuItem = menu?.findItem(R.id.day_night_switch)
        menuItem?.setActionView(R.layout.day_night_switch)

        var dayNightSwitch: Switch? = menuItem?.actionView?.findViewById(R.id.d_n_switch)
        if (dayNightSwitch == null) {
            Log.d("main", "day night switch is null")
        }

        // if currently at night, toggle should be on.
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            dayNightSwitch?.isChecked = true
        }

        // add listener to toggle night mode
        dayNightSwitch?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Log.d("main", "night")
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
                recreate()
            } else {
                Log.d("main", "day")
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
                recreate()
            }
        }

        return true
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
