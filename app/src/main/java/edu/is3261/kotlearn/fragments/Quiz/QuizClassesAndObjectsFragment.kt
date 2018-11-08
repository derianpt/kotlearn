package edu.is3261.kotlearn.fragments.Quiz


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton

import edu.is3261.kotlearn.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class QuizClassesAndObjectsFragment : Fragment() {

    lateinit var inflater : LayoutInflater

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_classes_and_objects, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val testYourselfButton = activity?.findViewById<Button>(R.id.butClassesAndObjectsTestYourself)
        testYourselfButton?.setOnClickListener {
            fragmentManager!!.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_left)
                    .replace(R.id.fragmentholder, QuizClassesAndObjectsFragment())
                    .addToBackStack(null)
                    .commit()
        }
        val backButton = activity?.findViewById<ImageButton>(R.id.classesAndObjectsBackButton)
        backButton?.setOnClickListener{
            fragmentManager!!.popBackStack()
        }
    }


}
