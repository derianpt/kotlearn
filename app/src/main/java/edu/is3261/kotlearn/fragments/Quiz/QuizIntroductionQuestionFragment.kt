package edu.is3261.kotlearn.fragments.Quiz


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import edu.is3261.kotlearn.R
import edu.is3261.kotlearn.fragments.Quiz.helpers.QuizDBHelper


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */

class QuizIntroductionQuestionFragment : Fragment() {

    var qid = 0
    var score = 0
    lateinit var currentQuestion : QuestionRecord
    lateinit var questionList : List<QuestionRecord>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_quiz_introduction_question, container, false)

        if (savedInstanceState != null) {
            qid = savedInstanceState.getInt("qid")
            score = savedInstanceState.getInt("score")
        }
        val questionText = view.findViewById<TextView>(R.id.tv_intro_question)
        val radioButton1 = view.findViewById<RadioButton>(R.id.radioIntroButton1)
        val radioButton2 = view.findViewById<RadioButton>(R.id.radioIntroButton2)
        val radioButton3 = view.findViewById<RadioButton>(R.id.radioIntroButton3)
        val radioButton4 = view.findViewById<RadioButton>(R.id.radioIntroButton4)
        val radioGroup1 = view.findViewById<RadioGroup>(R.id.radioIntroGroup1)
        val backButton = activity?.findViewById<ImageButton>(R.id.introBackButton)
        backButton?.setOnClickListener {
            fragmentManager!!.popBackStack()
        }
        val butNext = view.findViewById<Button>(R.id.but_intro_next)
        butNext.setOnClickListener {
            if (radioGroup1.getCheckedRadioButtonId() == -1)
            {
                val toast = Toast.makeText(activity, "Please select one!", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }
            else
            {
                val selected = view.findViewById<RadioButton>(radioGroup1.checkedRadioButtonId)
                if (currentQuestion.correctAnswer.equals(selected.text.toString())){
                    score++

                    //remove selection for next question
                    radioGroup1.clearCheck()

                    //if all questions not finished, keep going!
                    if (qid < 4){
                        currentQuestion = questionList.get(qid)
                        if (qid === 3){
                            butNext.setText("Finish")
                            butNext.setOnClickListener {
                                val toast = Toast.makeText(activity, "Congratulations! You have completed the level", Toast.LENGTH_SHORT)
                                toast.setGravity(Gravity.CENTER, 0, 0)
                                toast.show()
                                fragmentManager!!.beginTransaction()
                                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_left)
                                        .replace(R.id.fragmentholder, QuizBasicsFragment())
                                        .addToBackStack(null)
                                        .commit()

                            }
                        }
                        setQuestionView(questionText, radioButton1, radioButton2, radioButton3, radioButton4)
                    }
                } else {
                    val toast = Toast.makeText(activity, "Your answer is incorrect! Please try again!", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()

                    //so that there will be no negative score. very demoralizing.
                    if (score > 0) {
                        score--
                    }
                }
            }
        }

        setQuestionView(questionText, radioButton1, radioButton2, radioButton3, radioButton4)

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        var db = QuizDBHelper (context)
        db.initializeQuestions()
        questionList = db.readQuestionsByQuestionCategory("Introduction")
        currentQuestion = questionList.get(qid)

    }

    private fun setQuestionView(questionText:TextView, radioButton1:Button, radioButton2:Button, radioButton3: Button, radioButton4: Button){
        questionText.setText(currentQuestion.question)
        radioButton1.setText(currentQuestion.optionA)
        radioButton2.setText(currentQuestion.optionB)
        radioButton3.setText(currentQuestion.optionC)
        radioButton4.setText(currentQuestion.optionD)
        qid++
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("qid", qid)
        outState.putInt("score", score)
    }
}
