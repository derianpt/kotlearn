package edu.is3261.kotlearn.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import edu.is3261.kotlearn.fragments.About.*
import edu.is3261.kotlearn.fragments.TwitterFeed.HashtagTimelineFragment
import edu.is3261.kotlearn.fragments.TwitterFeed.UserTimelineFragment

class AboutSwipeAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager){
    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                AboutFirstFragment()
            }
            1 -> {
                AboutTwitterFragment()
            }
            2 -> {
                AboutRedditFragment()
            }
            3 -> {
                AboutQuizFragment()
            }
            else -> {
                AboutFirstFragment()
            }
        }
    }
}