package edu.is3261.kotlearn.fragments.Quiz.helpers

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import edu.is3261.kotlearn.fragments.Quiz.QuestionRecord
import edu.is3261.kotlearn.fragments.Quiz.QuestionTableInfo

class QuizDBHelper (context: Context) : SQLiteOpenHelper (context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        val DATABASE_VERSION=1
        val DATABASE_NAME="Questions.db"

        private val SQL_CREATE_ENTRIES =
                "CREATE TABLE " +
                        QuestionTableInfo.TABLE_NAME + " (" +
                        QuestionTableInfo.COLUMN_QUESTION + " TEXT," +
                        QuestionTableInfo.COLUMN_OPTIONA + " TEXT," +
                        QuestionTableInfo.COLUMN_OPTIONB + " TEXT," +
                        QuestionTableInfo.COLUMN_OPTIONC + " TEXT," +
                        QuestionTableInfo.COLUMN_OPTIOND + " TEXT," +
                        QuestionTableInfo.COLUMN_CORRECTANSWER + " TEXT," +
                        QuestionTableInfo.COLUMN_QUESTIONCAT + " TEXT)"
        private val SQL_DELETE_ENTRIES=
                "DROP TABLE IF EXISTS " +
                        QuestionTableInfo.TABLE_NAME
    }

    override fun onCreate (db: SQLiteDatabase?){
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade (db, oldVersion, newVersion)
    }

    fun initializeQuestions(){

        Log.d("initalizinggg", "****initializeQuestions")
        val IntroQ1 = QuestionRecord(
                "How do you define a function with an Int return type?",
                "fun sum (a: Int, b: Int): Int",
                "fun sum (a: Int) : String",
                "fun printSum (a: Int, b: Int) : Unit",
                "fun printSum (a: Int)",
                "fun sum (a: Int, b: Int): Int",
                "Introduction")
        this.addQuestion (IntroQ1)

        Log.d ("added", "question 1")
        val IntroQ2 = QuestionRecord(
                "Which of the following are mutable?",
                "var",
                "val",
                "",
                "",
                "var",
                "Introduction")
        this.addQuestion (IntroQ2)

        val IntroQ3 = QuestionRecord(
                "Select the operator that checks if an expression is an instance of a type.",
                "if",
                "am",
                "is",
                "check",
                "is",
                "Introduction"
        )
        this.addQuestion(IntroQ3)

        val IntroQ4 = QuestionRecord(
                "Which method checks if number is out of range?",
                "if (x in 1..y+1)",
                "if (x !in 0..5)",
                "if (x out 0..5)",
                "if (x in 1..6 step 3)",
                "if (x !in 0..5)",
                "Introduction"
        )
        this.addQuestion(IntroQ4)

        val IntroQ5 = QuestionRecord(
                "Select what you should NOT do for Kotlin coding conventions.",
                "Name functions, properties and local variables starting with lower case letters and use camel humps and no underscores",
                "Put nested classes next ot code that uses those classes",
                "Explicitly specify function return types and property types",
                "Sort method declarations alphabetically or by visibility",
                "Sort method declarations alphabetically or by visibility",
                "Introduction"
        )
        this.addQuestion(IntroQ5)


        val BasicsQ1 = QuestionRecord(
                "Which of the following is true about numbers?",
                "Kotlin handles numbers in the exact same way as Java",
                "There is no implicit widening conversions for numbers",
                "Built in types representing numbers in Kotlin is close to Java",
                "Characters are the same as numbers",
                "Built in types representing numbers in Kotlin is close to Java",
                "Basics"
        )
        this.addQuestion(BasicsQ1)

        val BasicsQ2 = QuestionRecord(
                "The following packages are imported into every Kotlin file by default EXCEPT: ",
                "kotlin.annotations.*",
                "kotlin.sequences.*",
                "kotlin.comparisons.*",
                "None of the above",
                "None of the above",
                "Basics"
        )
        this.addQuestion(BasicsQ2)

        val BasicsQ3 = QuestionRecord(
                "Which of the following is NOT an example of control flow?",
                "if",
                "during",
                "when",
                "for",
                "during",
                "Basics"
        )
        this.addQuestion(BasicsQ3)
    }


    fun addQuestion (course: QuestionRecord): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put (QuestionTableInfo.COLUMN_QUESTION, course.question)
        values.put (QuestionTableInfo.COLUMN_OPTIONA, course.optionA)
        values.put (QuestionTableInfo.COLUMN_OPTIONB, course.optionB)
        values.put (QuestionTableInfo.COLUMN_OPTIONC, course.optionC)
        values.put (QuestionTableInfo.COLUMN_OPTIOND, course.optionD)
        values.put (QuestionTableInfo.COLUMN_CORRECTANSWER, course.correctAnswer)
        values.put (QuestionTableInfo.COLUMN_QUESTIONCAT, course.questionCat)

        val newRowId = db.insert (QuestionTableInfo.TABLE_NAME, null, values)
        return true
    }

    fun readQuestionsByQuestionCategory(questionCat: String): ArrayList<QuestionRecord>{
        Log.d ("readingg", "readQuestionsByQuestionCategory")
        val questions = ArrayList<QuestionRecord>()
        val db= writableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery ("select * from " +
                    QuestionTableInfo.TABLE_NAME, null)
        } catch (e: SQLiteException){
            db.execSQL (SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var question: String
        var optionA: String
        var optionB: String
        var optionC: String
        var optionD: String
        var correctAnswer: String
        var questionCat: String

        if (cursor!!.moveToFirst()){
            while (cursor.isAfterLast == false){

                question = cursor.getString(
                        cursor.getColumnIndex(QuestionTableInfo.COLUMN_QUESTION))
                optionA = cursor.getString(
                        cursor.getColumnIndex(QuestionTableInfo.COLUMN_OPTIONA))
                optionB = cursor.getString(
                        cursor.getColumnIndex(QuestionTableInfo.COLUMN_OPTIONB))
                optionC = cursor.getString(
                        cursor.getColumnIndex(QuestionTableInfo.COLUMN_OPTIONC))
                optionD = cursor.getString(
                        cursor.getColumnIndex(QuestionTableInfo.COLUMN_OPTIOND))
                correctAnswer = cursor.getString(
                        cursor.getColumnIndex(QuestionTableInfo.COLUMN_CORRECTANSWER))
                questionCat = cursor.getString(
                        cursor.getColumnIndex(QuestionTableInfo.COLUMN_QUESTIONCAT))


                if (questionCat.equals("Introduction")) {
                    questions.add(QuestionRecord(question, optionA, optionB, optionC, optionD, correctAnswer, questionCat))
                }
                cursor.moveToNext()
            }
        }
        return questions
    }
}
