package dev.android.play.activityresult

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import dev.android.play.R

class ActivityResultB : AppCompatActivity() {

    lateinit var buttonStartActivityResult: AppCompatButton
    lateinit var etInputToPass: AppCompatEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_b)

        buttonStartActivityResult = findViewById(R.id.buttonStartActivityResult)
        etInputToPass = findViewById(R.id.etInputToPass)

        buttonStartActivityResult.setOnClickListener {

            val text = etInputToPass.text.toString()

            val intent = Intent()
            intent.putExtra("input", text)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}