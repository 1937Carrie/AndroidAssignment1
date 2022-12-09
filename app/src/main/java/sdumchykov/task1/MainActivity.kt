package sdumchykov.task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout: ConstraintLayout = findViewById(R.id.constraintLayout)
        val textView = TextView(this)
        textView.setText("Hello I am Text View")
        textView.textSize = 30F
//        layout.addView(textView)

    }
}