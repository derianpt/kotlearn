package edu.is3261.kotlearn.fragments.About


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import edu.is3261.kotlearn.R
import edu.is3261.kotlearn.adapters.AboutSwipeAdapter

class AboutParentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about_parent, container, false)

        // create a new fragment adapter to handle child fragments
        val fragmentAdapter = AboutSwipeAdapter(childFragmentManager)

        // get the view pager used for swiping
        val viewPager = view.findViewById<ViewPager>(R.id.news_feed_viewpager)

        // attach the fragment adapter to the view pager.
        viewPager.adapter = fragmentAdapter

        return view
    }


}
