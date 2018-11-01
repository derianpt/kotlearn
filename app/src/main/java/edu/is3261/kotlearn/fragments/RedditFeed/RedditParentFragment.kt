package edu.is3261.kotlearn.fragments.RedditFeed

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import edu.is3261.kotlearn.R
import edu.is3261.kotlearn.adapters.RedditTabsAdapter

class RedditParentFragment : Fragment() {

    private val logTag = "redditparent"
    lateinit var thisView: View
    lateinit var fragmentAdapter: RedditTabsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        thisView = inflater.inflate(R.layout.fragment_reddit_parent, container, false)

        // create a new fragment adapter to handle child fragments
        fragmentAdapter = RedditTabsAdapter(childFragmentManager)

        // get the view pager used for swiping
        val viewPager = thisView.findViewById<ViewPager>(R.id.reddit_parent_viewpager)

        // attach the fragment adapter to the view pager.
        viewPager.adapter = fragmentAdapter

        // set up tabs using view pager
        val tabLayout = thisView.findViewById<TabLayout>(R.id.reddit_parent_tabs)
        tabLayout.setupWithViewPager(viewPager, true)

        // set up "Sort by" spinner
        val sortBySpinner: Spinner = thisView.findViewById(R.id.reddit_parent_sort_by_spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
                this.context,
                R.array.sort_by_array,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            sortBySpinner.adapter = adapter
        }
        // add onItemSelected listener for spinner
        var sortBySpinnerListener = SortBySpinnerListener()
        // set default selection with no animation, so that onItemSelectedListener will not be called.
        sortBySpinner.setSelection(0,false)
        sortBySpinner.onItemSelectedListener = sortBySpinnerListener

        return thisView
    }

    inner class SortBySpinnerListener: AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
            // An item was selected.
            var selected = parent.selectedItem
            thisView.findViewById<TextView>(R.id.reddit_parent_sort_by_result).text = selected.toString()
            fragmentAdapter.notifyDataSetChanged()
            Log.d(logTag, "3. selected: $selected")
        }

        override fun onNothingSelected(parent: AdapterView<*>) {
            thisView.findViewById<TextView>(R.id.reddit_parent_sort_by_result).text = ""
            fragmentAdapter.notifyDataSetChanged()
            Log.d(logTag, "4")
        }
    }
}