package edu.is3261.kotlearn.fragments.RedditFeed

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.is3261.kotlearn.R
import edu.is3261.kotlearn.adapters.RedditPagerAdapter

class RedditParentFragment : Fragment() {

    private val logTag = "socialfeedparent"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_reddit_parent, container, false)

        val fragmentAdapter = RedditPagerAdapter(childFragmentManager)

        val viewPager = view.findViewById<ViewPager>(R.id.social_parent_viewpager)

        viewPager.adapter = fragmentAdapter

        val tabLayout = view.findViewById<TabLayout>(R.id.social_parent_tabs)

        tabLayout.setupWithViewPager(viewPager, true)

        return view

    }
}