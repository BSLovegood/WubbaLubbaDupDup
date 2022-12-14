package lovegoodbs.quizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    fun onClickStartGame(view: View) {
        val intent= Intent(this, GameActivity::class.java)
        startActivity(intent)
    }


    fun onClickExit(view: View) {
        exitProcess(0)
    }
}