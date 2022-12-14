package sdumchykov.task1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import sdumchykov.task1.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTextToTextName()
        setURIToImageInstagram()
    }

    private fun setURIToImageInstagram() {
        binding.imageInstagram.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/p/BDdr32ZrvgP/")
                )
            )
        }
    }

    private fun setTextToTextName() {
        val signupEmail = intent.getStringExtra("email")
        val splitted = signupEmail?.substring(0, signupEmail.indexOf('@'))?.split(Regex("\\W"))

        if (splitted?.size!! > 1) {
            val firstName = splitted[0].replaceFirstChar { it.uppercase() }
            val secondName = splitted[1].replaceFirstChar { it.uppercase() }

            binding.textViewName.text = "$firstName $secondName"
        } else {
            binding.textViewName.text = signupEmail.substring(0, signupEmail.indexOf('@'))
        }
    }

}