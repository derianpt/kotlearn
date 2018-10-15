package edu.is3261.kotlearn.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import edu.is3261.kotlearn.fragments.Quiz.QuizLandingFragment
import edu.is3261.kotlearn.fragments.SocialFeed.SocialFeedFragment

class MyPagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm){
    private val logTag = "fragmentPagerAdapter"

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                Log.d(logTag, "tab 0 Fragment")
                SocialFeedFragment()
            }
            1 -> {
                Log.d(logTag, "tab 1 Fragment")
                QuizLandingFragment()
            }
            else -> SocialFeedFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> {
                Log.d(logTag, "tab 0 title")
                "Reddit"
            }
            1 -> {
                Log.d(logTag, "tab 1 title")
                "Stackoverflow"
            }
            else -> "what"
        }
    }
}