package sdumchykov.task1

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private var stockBackground = true
    private var maestroCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signupEmail = intent.getStringExtra("email")
        val textName = findViewById<TextView>(R.id.text_name)
        val splitted = signupEmail?.split(Regex("\\W"))
        val firstName = splitted?.get(0)?.replaceFirstChar { it.uppercase() }
        val secondName = splitted?.get(1)?.replaceFirstChar { it.uppercase() }

        textName.setText("$firstName $secondName")
    }

}