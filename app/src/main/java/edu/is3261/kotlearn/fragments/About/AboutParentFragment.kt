package edu.is3261.kotlearn.fragments.About


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatDelegate
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch

import edu.is3261.kotlearn.R

class AboutParentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about_parent, container, false)

        var dayNightSwitch = view.findViewById<Switch>(R.id.d_n_switch)

        // if currently at night, toggle should be on.
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            dayNightSwitch?.isChecked = true
        }

        // add listener to toggle night mode
        dayNightSwitch?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Log.d("main", "night")
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                activity?.recreate()
            } else {
                Log.d("main", "day")
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                activity?.recreate()
            }
        }

        return view
    }


}
