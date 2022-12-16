package sdumchykov.task1

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText


class SignUpActivity : AppCompatActivity() {
    private val watcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            val textInputPassword = findViewById<TextInputEditText>(R.id.textInputPassword)

            val lessThanEightSymbols = textInputPassword.text?.length!! < 8
            val notContainsDigits = !textInputPassword.text?.contains(Regex("\\d"))!!
            val notContainsCharacters = !textInputPassword.text?.contains(Regex("[a-zA-Z]+"))!!

            if (lessThanEightSymbols || notContainsDigits || notContainsCharacters) {
                textInputPassword.error = "Enter valid password"
            }

            val textInputEmail = findViewById<TextInputEditText>(R.id.textInputEmail)
            if (!textInputEmail.text?.contains(Regex(".+@.+\\..+"))!!) {
                textInputEmail.error = "Enter valid email"
            }

            if (textInputEmail.error.isNullOrBlank() && textInputPassword.error.isNullOrBlank()) {
                val buttonRegister = findViewById<Button>(R.id.buttonRegister)
                buttonRegister.isEnabled = true
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sign_up)

        val buttonRegister = findViewById<Button>(R.id.buttonRegister)
        buttonRegister.isEnabled = false

        val textInputEmail = findViewById<TextInputEditText>(R.id.textInputEmail)
        textInputEmail.addTextChangedListener(watcher)
        val textInputPassword = findViewById<TextInputEditText>(R.id.textInputPassword)
        textInputPassword.addTextChangedListener(watcher)

        buttonRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", textInputEmail.text.toString())
            startActivity(intent)
        }
    }

    fun onClickRegister(view: View) {

    }
}