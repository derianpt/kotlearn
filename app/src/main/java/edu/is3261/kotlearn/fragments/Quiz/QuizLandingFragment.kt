package edu.is3261.kotlearn.fragments.Quiz


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import edu.is3261.kotlearn.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class QuizLandingFragment : Fragment(){

    private val mOnQuizLandingSelection = View.OnClickListener { item ->
        when (item.id){
            R.id.but_introduction, R.id.image_introduction-> {
                createQuizIntroductionFragment()
                return@OnClickListener
            }
            R.id.but_basics, R.id.image_basics-> {
                createQuizBasicsFragment()
                return@OnClickListener
            }
            R.id.but_classesAndObjects, R.id.image_classesAndObjects-> {
                createQuizClassesAndObjectsFragment()
                return@OnClickListener
            }
        }
        return@OnClickListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_landing, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?){
        super.onActivityCreated(savedInstanceState)

        //Introduction Button and Image
        val butIntroduction = activity?.findViewById<Button>(R.id.but_introduction)
        butIntroduction?.setOnClickListener(mOnQuizLandingSelection)
        val imageIntroduction = activity?.findViewById<ImageView>(R.id.image_introduction)
        imageIntroduction?.setOnClickListener(mOnQuizLandingSelection)

        //Basics Button and Image
        val butBasics = activity?.findViewById<Button>(R.id.but_basics)
        butBasics?.setOnClickListener(mOnQuizLandingSelection)
        val imageBasics = activity?.findViewById<ImageView>(R.id.image_basics)
        imageBasics?.setOnClickListener(mOnQuizLandingSelection)

        //Classes and Objects Button and Image
        val butClassesAndObjects = activity?.findViewById<Button>(R.id.but_classesAndObjects)
        butClassesAndObjects?.setOnClickListener(mOnQuizLandingSelection)
        val imageClassesAndObjects = activity?.findViewById<ImageView>(R.id.image_classesAndObjects)
        imageClassesAndObjects?.setOnClickListener(mOnQuizLandingSelection)
    }

    fun createQuizIntroductionFragment(){
        fragmentManager!!.beginTransaction()
                .replace(R.id.fragmentholder, QuizIntroductionFragment())
                .addToBackStack(null)
                .commit()
    }
    fun createQuizBasicsFragment(){
        fragmentManager!!.beginTransaction()
                .replace(R.id.fragmentholder, QuizBasicsFragment())
                .addToBackStack(null)
                .commit()
    }
    fun createQuizClassesAndObjectsFragment(){
        fragmentManager!!.beginTransaction()
                .replace(R.id.fragmentholder, QuizClassesAndObjectsFragment())
                .addToBackStack(null)
                .commit()
    }

}
