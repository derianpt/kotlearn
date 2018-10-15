package edu.is3261.kotlearn.fragments.SocialFeed

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.is3261.kotlearn.R
import edu.is3261.kotlearn.adapters.MyPagerAdapter
import kotlinx.android.synthetic.main.fragment_social_parent.*

class SocialFeedParentFragment : Fragment() {

    private val logTag = "socialfeedparent"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_social_parent, container, false)

        val viewPager = view.findViewById<ViewPager>(R.id.social_parent_viewpager)

        val fragmentAdapter = MyPagerAdapter(childFragmentManager)
        Log.d(logTag, fragmentAdapter.toString())
        Log.d(logTag, fragmentAdapter.getPageTitle(0).toString())
        viewPager.adapter = fragmentAdapter

        val tabs = view.findViewById<TabLayout>(R.id.social_parent_tabs)
        if (tabs == null) {
            Log.d(logTag, "THE TAB IS NULL???")
        } else {
            Log.d(logTag, "Before: ${tabs.tabCount}")
            tabs.setupWithViewPager(this.social_parent_viewpager)
            Log.d(logTag, "After: ${tabs.tabCount}")
        }

        return view
    }
}