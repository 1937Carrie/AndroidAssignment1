package sdumchykov.task1

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private var stockBackground = true
    private var maestroCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    @SuppressLint("ResourceAsColor")
    fun onClickEditProfile(view: View) {
        val bottomView: View = findViewById(R.id.view_bottom_container)
        val profileImage: ImageView = findViewById(R.id.image_picture)

        val listOfColors = listOf(
            Color.YELLOW,
            Color.BLACK,
            Color.BLUE,
            Color.CYAN,
            Color.DKGRAY,
            Color.MAGENTA,
            Color.RED,
            Color.GREEN,
            Color.GRAY
        )
        val listOfMaestro = listOf(
            R.drawable.maestro0,
            R.drawable.maestro1,
            R.drawable.maestro2,
            R.drawable.maestro3,
            R.drawable.maestro4
        )

        val nextInt = Random.nextInt(listOfColors.size)
        val randomColor = listOfColors[nextInt]

        if (stockBackground) {
            stockBackground = false
            bottomView.setBackgroundColor(randomColor)

            val currentImage = listOfMaestro[maestroCounter++ % 5]
            profileImage.setImageResource(currentImage)

        } else {
            stockBackground = true
            profileImage.setImageResource(R.drawable.tmp_rounded_image)
            bottomView.setBackgroundColor(0xFFFAFAFA.toInt())
        }

    }

}