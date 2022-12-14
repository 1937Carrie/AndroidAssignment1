package sdumchykov.task1

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.SignInButton


class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

//        val signInButton: SignInButton = findViewById(R.id.sign_in_button)

//        setGooglePlusButtonText(signInButton, "GOOGLE")

    }

    private fun setGooglePlusButtonText(signInButton: SignInButton, buttonText: String) {
        for (i in 0 until signInButton.childCount) {
            val v: View = signInButton.getChildAt(i)

            if (v is TextView) {
                v.text = buttonText
                return
            }
        }
    }
}