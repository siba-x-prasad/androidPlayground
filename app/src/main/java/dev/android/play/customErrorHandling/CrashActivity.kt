package dev.android.play.customErrorHandling

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import dev.android.play.R

class CrashActivity : AppCompatActivity() {
    lateinit var buttonCrash: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crash)
        buttonCrash = findViewById(R.id.buttonCrash)

        buttonCrash.setOnClickListener {
            throw Error("Hello, I am the error")
        }
    }
}