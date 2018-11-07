package edu.is3261.kotlearn.fragments.TwitterFeed

import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.is3261.kotlearn.R
import edu.is3261.kotlearn.adapters.TwitterTabsAdapter
import android.content.res.Configuration.UI_MODE_NIGHT_UNDEFINED
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_MASK



class TwitterParentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_twitter_parent, container, false)

        // create a new fragment adapter to handle child fragments
        val fragmentAdapter = TwitterTabsAdapter(childFragmentManager)

        // get the view pager used for swiping
        val viewPager = view.findViewById<ViewPager>(R.id.news_feed_viewpager)
        // attach the fragment adapter to the view pager.
        viewPager.adapter = fragmentAdapter

        // set up tabs using swipe content from view pager
        val tabLayout = view.findViewById<TabLayout>(R.id.news_feed_tabs)
        tabLayout.setupWithViewPager(viewPager, true)

        return view
    }

    companion object {
        fun IsItNightNow(fragment:Fragment): Boolean{
            val currentNightMode = fragment.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            when (currentNightMode) {
                // Night mode is not active, we're in day time
                Configuration.UI_MODE_NIGHT_NO -> {
                    return false
                }
                // Night mode is active, we're at night!
                Configuration.UI_MODE_NIGHT_YES -> {
                    return true
                }
                // We don't know what mode we're in, assume notnight
                Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                    return false
                }
            }
            return false
        }
    }

}
