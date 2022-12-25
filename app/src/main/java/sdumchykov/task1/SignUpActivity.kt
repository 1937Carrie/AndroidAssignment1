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
            val lessThanEightSymbols = binding.textInputPassword.text?.length!! < 8
            val notContainsDigits = !binding.textInputPassword.text?.contains(Regex("\\d"))!!
            val notContainsCharacters =
                !binding.textInputPassword.text?.contains(Regex("[a-zA-Z]+"))!!
            /*TODO користувачу тре більше інфи про те, чому його пароль не валідний.
               Додати символів, або цифр, або короктий. Фідбека нема.
               Якщо з емейл є стандарти, то стандарт паролів у кожного софта може бути свій.*/
            /*TODO усі стрінги у ресурси*/
            if (lessThanEightSymbols || notContainsDigits || notContainsCharacters) {
                binding.textInputPassword.error = "Min 8 symbols. Letters and digits are required"
            } else {
                binding.textInputPassword.error = null
            }

            if (!binding.textInputEmail.text?.contains(Regex(".+@.+\\..+"))!!) {
                binding.textInputEmail.error = "Enter valid email"
            } else {
                binding.textInputEmail.error = null
            }

            val emailError = binding.textInputEmail.error.isNullOrEmpty()
            val passwordError = binding.textInputPassword.error.isNullOrEmpty()

            binding.buttonRegister.isEnabled = emailError && passwordError
        }

        override fun afterTextChanged(s: Editable?) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*TODO with(binding){} замість 1000 біндінгов :)*/
        buttonRegisterDisable(binding.buttonRegister)

        textInputAddTextChangedListener(binding.textInputEmail)
        textInputAddTextChangedListener(binding.textInputPassword)

        buttonRegisterSetOnClickListener(
            binding.buttonRegister, binding.textInputEmail, binding.textInputPassword
        )

        startMainActivity()

        if (savedInstanceState != null) {
            binding.textInputEmail.setText(savedInstanceState.getString("email"))
            binding.textInputPassword.setText(savedInstanceState.getString("password"))
        }
    }

    private fun startMainActivity() {
        /*TODO pref what? :)*/
        /*TODO можливо доречно зробити глобальну змінну, якщо ти її використовуєш декілька разів.*/
        val pref = getPreferences(MODE_PRIVATE)

        if (pref.getString("Email", "")?.length!! > 0) {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)

            intent.putExtra("email", pref.getString("Email", ""))

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
                /*TODO pref what? :)*/
                val pref = getPreferences(MODE_PRIVATE)
                val editor = pref.edit()

                editor.putString("Email", textInputEmail.text.toString())
                editor.putString("Password", textInputPassword.text.toString())

                editor.apply()

                val toast = Toast.makeText(
                    applicationContext,
                    "${pref.getString("Email", "Not found")}\n" + "${
                        pref.getString(
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