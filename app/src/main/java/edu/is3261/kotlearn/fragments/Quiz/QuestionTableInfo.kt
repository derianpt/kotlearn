package edu.is3261.kotlearn.fragments.Quiz

import android.provider.BaseColumns

class QuestionTableInfo : BaseColumns {

    companion object {
        val TABLE_NAME="questions"
        val COLUMN_ID = "id"
        val COLUMN_QUESTION = "question"
        val COLUMN_OPTIONA = "optionA"
        val COLUMN_OPTIONB = "optionB"
        val COLUMN_OPTIONC = "optionC"
        val COLUMN_OPTIOND = "optionD"
        val COLUMN_CORRECTANSWER = "correctAnswer"
        val COLUMN_QUESTIONCAT = "questionCat"
    }
}