package dev.android.play.customErrorHandling

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.lifecycleScope
import dev.android.play.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * https://medium.com/@emirhanklvr/how-to-replace-the-standard-crash-dialog-with-a-custom-error-screen-31f087f1a01d
 */
class ErrorScreenActivity : AppCompatActivity() {
    lateinit var buttonCrash: AppCompatButton
    lateinit var textBug: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error_screen)

        buttonCrash = findViewById(R.id.buttonCrash)
        textBug = findViewById(R.id.textBug)

        GlobalExceptionHandler.getThrowableFromIntent(intent).let {
            Log.e("CrashActivity", "Error Data", it)
        }

        buttonCrash.setOnClickListener {
            lifecycleScope.launch {
                textBug.text = "Reporting..."
                delay(2000)
                textBug.text = "Reported..."
                delay(1000)
                finishAffinity()
            }
        }
    }
}