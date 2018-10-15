package edu.is3261.kotlearn

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import edu.is3261.kotlearn.fragments.Quiz.QuizLandingFragment
import edu.is3261.kotlearn.fragments.SocialFeed.SocialFeedFragment
import edu.is3261.kotlearn.fragments.SocialFeed.SocialFeedParentFragment

class MainActivity : AppCompatActivity() {

    val manager = supportFragmentManager
    private val mOnNavigationItemSelectorException = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId){
            R.id.bottom_navigation_social -> {
                createSocialFeedFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_navigation_news -> {

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
        Log.v("","started")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState==null) {
            createSocialFeedFragment()
        }
        Log.v("","finished")
        toolbar = supportActionBar!!
        // changing color of ActionBar
        toolbar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#003366")))
        toolbar.setIcon(R.drawable.basics)
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener (mOnNavigationItemSelectorException)

        Log.v("","finished bottomNav")
    }

    fun createSocialFeedFragment(){
        val transaction = manager.beginTransaction()
        val fragment = SocialFeedParentFragment()
        transaction.replace(R.id.fragmentholder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        Log.v("", "social feed frag")
    }

    fun createQuizLandingFragment(){
        val transaction = manager.beginTransaction()
        val fragment = QuizLandingFragment()
        transaction.replace(R.id.fragmentholder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        Log.v("","quiz frag")
    }


}
