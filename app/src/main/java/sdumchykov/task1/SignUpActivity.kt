package sdumchykov.task1

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import sdumchykov.task1.databinding.ActivitySignUpBinding


class SignUpActivity : BaseActivity<ActivitySignUpBinding>(ActivitySignUpBinding::inflate) {

    private val watcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(
            s: CharSequence?, start: Int, count: Int, after: Int
        ) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            with(binding) {
                val lessThanEightSymbols = textInputPassword.text?.length!! < 8
                val notContainsDigits = !textInputPassword.text?.contains(Regex("\\d"))!!
                val notContainsCharacters =
                    !textInputPassword.text?.contains(Regex("[a-zA-Z]+"))!!

                if (lessThanEightSymbols || notContainsDigits || notContainsCharacters) {
                    textInputPassword.error =
                        resources.getString(R.string.error_message_password)
                } else {
                    textInputPassword.error = null
                }

                if (!textInputEmail.text?.contains(Regex(".+@.+\\..+"))!!) {
                    textInputEmail.error = resources.getString(R.string.error_message_email)
                } else {
                    textInputEmail.error = null
                }

                val emailError = textInputEmail.error.isNullOrEmpty()
                val passwordError = textInputPassword.error.isNullOrEmpty()

                buttonRegister.isEnabled = emailError && passwordError
            }
        }

        override fun afterTextChanged(s: Editable?) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            buttonRegisterDisable(buttonRegister)

            textInputAddTextChangedListener(textInputEmail)
            textInputAddTextChangedListener(textInputPassword)

            buttonRegisterSetOnClickListener(buttonRegister, textInputEmail, textInputPassword)

            startMainActivity()

            if (savedInstanceState != null) {
                textInputEmail.setText(savedInstanceState.getString("email"))
                textInputPassword.setText(savedInstanceState.getString("password"))
            }
        }
    }

    private fun startMainActivity() {
        val cachedData = getPreferences(MODE_PRIVATE)

        if (cachedData.getString("Email", "")?.length!! > 0) {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)

            intent.putExtra("email", cachedData.getString("Email", ""))

            startActivity(intent)
            finish()
        }
    }

    private fun buttonRegisterSetOnClickListener(
        buttonRegister: Button,
        textInputEmail: TextInputEditText,
        textInputPassword: TextInputEditText
    ) {
        buttonRegister.setOnClickListener {
            if (binding.checkBoxRememberMe.isChecked) {
                val cachedData = getPreferences(MODE_PRIVATE)
                val editor = cachedData.edit()

                editor.putString("Email", textInputEmail.text.toString())
                editor.putString("Password", textInputPassword.text.toString())

                editor.apply()

                val toast = Toast.makeText(
                    applicationContext,
                    "${cachedData.getString("Email", "Not found")}\n" + "${
                        cachedData.getString(
                            "Password",
                            "Not found"
                        )
                    }",
                    Toast.LENGTH_LONG
                )
                toast.setGravity(Gravity.TOP, 0, 140)
                toast.show()
            }

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", textInputEmail.text.toString())
            startActivity(intent)
        }
    }

    private fun textInputAddTextChangedListener(textInputEmail: TextInputEditText) {
        textInputEmail.addTextChangedListener(watcher)
    }

    private fun buttonRegisterDisable(buttonRegister: Button) {
        buttonRegister.isEnabled = false
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

        outState.putString("email", binding.textInputEmail.text.toString())
        outState.putString("password", binding.textInputPassword.text.toString())

    }
}