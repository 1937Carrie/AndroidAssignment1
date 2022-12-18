package sdumchykov.task1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText


class SignUpActivity : AppCompatActivity() {
    private val watcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val textInputPassword = findViewById<TextInputEditText>(R.id.textInputPassword)

            val lessThanEightSymbols = textInputPassword.text?.length!! < 8
            val notContainsDigits = !textInputPassword.text?.contains(Regex("\\d"))!!
            val notContainsCharacters =
                !textInputPassword.text?.contains(Regex("[a-zA-Z]+"))!!

            if (lessThanEightSymbols || notContainsDigits || notContainsCharacters) {
                textInputPassword.error = "Enter valid password"
            } else {
                textInputPassword.error = null
            }

            val textInputEmail = findViewById<TextInputEditText>(R.id.textInputEmail)

            if (!textInputEmail.text?.contains(Regex(".+@.+\\..+"))!!) {
                textInputEmail.error = "Enter valid email"
            } else {
                textInputEmail.error = null
            }

            val buttonRegister = findViewById<Button>(R.id.buttonRegister)

            val emailError = textInputEmail.error.isNullOrEmpty()
            val passwordError = textInputPassword.error.isNullOrEmpty()

            buttonRegister.isEnabled = emailError && passwordError
        }

        override fun afterTextChanged(s: Editable?) {

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
            if (findViewById<CheckBox>(R.id.checkBoxRememberMe).isChecked) {
                val pref = getPreferences(Context.MODE_PRIVATE)
                val editor = pref.edit()

                editor.putString("Email", textInputEmail.text.toString())
                editor.putString("Password", textInputPassword.text.toString())

                editor.apply()

                val toast = Toast.makeText(
                    applicationContext,
                    "${pref.getString("Email", "Not found")}\n" +
                            "${pref.getString("Password", "Not found")}", Toast.LENGTH_LONG
                )
                toast.setGravity(Gravity.TOP, 0, 140)
                toast.show()
            }

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", textInputEmail.text.toString())
            startActivity(intent)
        }

        val pref = getPreferences(Context.MODE_PRIVATE)

        if (pref.getString("Email", "")?.length!! > 0) {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)

            intent.putExtra("email", pref.getString("Email", ""))
            startActivity(intent)
            finish()
        }

        if (savedInstanceState != null) {
            textInputEmail.setText(savedInstanceState.getString("email"))
            textInputPassword.setText(savedInstanceState.getString("password"))
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

        val textInputEmail = findViewById<TextInputEditText>(R.id.textInputEmail)
        val textInputPassword = findViewById<TextInputEditText>(R.id.textInputPassword)

        outState.putString("email", textInputEmail.text.toString())
        outState.putString("password", textInputPassword.text.toString())

    }
}