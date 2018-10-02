package edu.is3261.kotlearn

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_social_feed.*

class SocialFeed : AppCompatActivity() {

    lateinit var toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_feed)

        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
    }
}
