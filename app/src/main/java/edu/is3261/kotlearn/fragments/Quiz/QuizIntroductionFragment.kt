package edu.is3261.kotlearn.fragments.Quiz


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import edu.is3261.kotlearn.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class QuizIntroductionFragment : Fragment() {

    lateinit var inflater : LayoutInflater

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_introduction, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val testYourselfButton = activity?.findViewById<Button>(R.id.butTestYourself)
        testYourselfButton?.setOnClickListener {
            fragmentManager!!.beginTransaction()
                    .replace(R.id.fragmentholder, QuizIntroductionQuestionFragment())
                    .addToBackStack(null)
                    .commit()
        }
    }


}
