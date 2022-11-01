package dev.android.play.handleBackpress

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import dev.android.play.R

/**
 * https://developer.android.com/reference/androidx/activity/OnBackPressedDispatcher
 */
class BackPressActivity : AppCompatActivity() {
    lateinit var container: FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_back_press)
        container = findViewById(R.id.container)

        supportFragmentManager.beginTransaction().add(R.id.container, BackPressFragment())
            .commitNow()

    }
}