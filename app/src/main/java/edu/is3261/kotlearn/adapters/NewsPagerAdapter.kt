package edu.is3261.kotlearn.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import edu.is3261.kotlearn.fragments.NewsFeed.HashtagTimelineFragment
import edu.is3261.kotlearn.fragments.NewsFeed.UserTimelineFragment

class NewsPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                UserTimelineFragment()
            }
            1 -> {
                HashtagTimelineFragment()
            }
            else -> {
                return UserTimelineFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "by @kotlin"
            1 -> "#kotlin"
            else -> "invalid news tab"
        }
    }
}