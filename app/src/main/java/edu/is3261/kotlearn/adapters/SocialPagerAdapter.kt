package edu.is3261.kotlearn.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import edu.is3261.kotlearn.fragments.Quiz.QuizLandingFragment
import edu.is3261.kotlearn.fragments.SocialFeed.SocialFeedFragment

class SocialPagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm){
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                SocialFeedFragment()
            }
            1 -> {
                QuizLandingFragment()
            }
            else -> SocialFeedFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> {
                "r/Kotlin"
            }
            1 -> {
                "r/Android"
            }
            else -> "r/Kotlin"
        }
    }
}