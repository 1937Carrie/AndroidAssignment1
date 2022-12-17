package sdumchykov.task1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signupEmail = intent.getStringExtra("email")
        val textName = findViewById<TextView>(R.id.text_name)
        val splitted = signupEmail?.substring(0, signupEmail.indexOf('@'))?.split(Regex("\\W"))

        if (splitted?.size!! > 1) {
            val firstName = splitted[0].replaceFirstChar { it.uppercase() }
            val secondName = splitted[1].replaceFirstChar { it.uppercase() }

            textName.text = "$firstName $secondName"
        } else {
            textName.text = signupEmail.substring(0, signupEmail.indexOf('@'))
        }
    }

}