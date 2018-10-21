package edu.is3261.kotlearn.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import edu.is3261.kotlearn.fragments.Quiz.QuizLandingFragment
import edu.is3261.kotlearn.fragments.RedditFeed.AndroidSubredditFragment
import edu.is3261.kotlearn.fragments.RedditFeed.KotlinSubredditFragment

class RedditPagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm){
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                KotlinSubredditFragment()
            }
            1 -> {
                AndroidSubredditFragment()
            }
            else -> KotlinSubredditFragment()
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