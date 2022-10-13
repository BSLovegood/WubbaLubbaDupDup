package lovegoodbs.quizz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlin.system.exitProcess

private const val TAG = "GameActivity"
class GameActivity : AppCompatActivity() {

    lateinit var button_true:Button
    lateinit var button_false:Button

    public var click:Boolean = false
    public var click2:Boolean=false

    private lateinit var questionTextView: TextView
    private lateinit var answers: TextView

    lateinit var pointsTextView: TextView

    public var game_points: Int = 0
    public var game_point: Int=0

    private val questionBank = listOf(
        Question(R.string.question1, false, R.string.answer1),
        Question(R.string.question2, false, R.string.answer2),
        Question(R.string.question3, true, R.string.answer3),
        Question(R.string.question4, true, R.string.answer4),
        Question(R.string.question5, false, R.string.answer5)
    )

    private var currentIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate() called")
        setContentView(R.layout.activity_game)

        questionTextView = findViewById(R.id.textQuestion)
        answers = findViewById(R.id.textAnswers)
        pointsTextView=findViewById(R.id.textPoints)
        button_true=findViewById(R.id.button_true)
        button_false=findViewById(R.id.button_false)


        updateQuestion()

        game_point=100/questionBank.size

        pointsTextView.setText(game_points.toString())
    }

    fun onClickFalse(view: View) {
        //Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()
        if (click) {
            checkAnswer(false)
            click=false
            click2=true
            answers.setText(questionBank[currentIndex].textAnsId)
        }
    }

    fun onClickTrue(view: View) {
        //Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show()
        if (click) {
            checkAnswer(true)
            click=false
            click2=true
            answers.setText(questionBank[currentIndex].textAnsId)
        }
    }

    fun onClickNext(view: View) {

       // currentIndex++
        if(click2) {
            currentIndex = (currentIndex + 1) % questionBank.size

            if (currentIndex == 0) {
                showInfoAlert()
                updateQuestion()
            } else {
                updateQuestion()
            }
            click2=false
        }
    }


    private fun updateQuestion() {

        val questionResId = questionBank[currentIndex].textResId
        questionTextView.setText((questionResId))
        click = true
        answers.setText("")

        //val answerResId = questionBank[currentIndex].textAnsId
        //  answers.setText((answerResId))
    }

    private fun checkAnswer(userAnswer : Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        if (userAnswer == correctAnswer) {
            game_points += game_point
            pointsTextView.setText(game_points.toString())
        }


        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

    private fun showInfoAlert(){
        var builder =AlertDialog.Builder(this)
        builder.setTitle("GAME OVER")
        builder.setMessage("Your Score is ${game_points}! Do you want to Restart?")
        builder.setPositiveButton("Yes") { dialog, which ->
            //Toast.makeText(applicationContext,
               // android.R.string.yes, Toast.LENGTH_SHORT).show()
            game_points=0
            pointsTextView.setText(game_points.toString())
        }

        builder.setNegativeButton("No") { dialog, which ->
            //Toast.makeText(applicationContext,
               // android.R.string.no, Toast.LENGTH_SHORT).show()
            exitProcess(0)
        }

        builder.show()
    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy() called")
    }
}