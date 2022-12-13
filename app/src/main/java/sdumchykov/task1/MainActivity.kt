package sdumchykov.task1

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    @SuppressLint("ResourceAsColor")
    fun onClickEditProfile(view: View) {
        val bottomView: View = findViewById(R.id.view_bottom_container)
        val background: Drawable = bottomView.background
        bottomView.setBackgroundColor(0xFFAAAA)

    }

}