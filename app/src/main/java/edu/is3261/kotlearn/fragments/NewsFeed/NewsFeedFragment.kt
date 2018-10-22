package edu.is3261.kotlearn.fragments.NewsFeed

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.is3261.kotlearn.R
import edu.is3261.kotlearn.adapters.NewsPagerAdapter

class NewsFeedFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_news_feed, container, false)

        // create a new fragment adapter to handle child fragments
        val fragmentAdapter = NewsPagerAdapter(childFragmentManager)

        // get the view pager used for swiping
        val viewPager = view.findViewById<ViewPager>(R.id.news_feed_viewpager)
        // attach the fragment adapter to the view pager.
        viewPager.adapter = fragmentAdapter

        // set up tabs using swipe content from view pager
        val tabLayout = view.findViewById<TabLayout>(R.id.news_feed_tabs)
        tabLayout.setupWithViewPager(viewPager, true)

        return view
    }
}

