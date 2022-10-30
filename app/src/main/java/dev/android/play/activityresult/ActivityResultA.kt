package dev.android.play.activityresult

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import dev.android.play.R

class ActivityResultA : AppCompatActivity() {

    lateinit var etInputFrom: AppCompatEditText
    lateinit var buttonStartActivityResult: AppCompatButton
    lateinit var fragmentContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        etInputFrom = findViewById(R.id.etInputFrom)
        buttonStartActivityResult = findViewById(R.id.buttonStartActivityResult)
        fragmentContainer = findViewById(R.id.fragmentContainer)

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val value = it.data?.getStringExtra("input")
                etInputFrom.setText("Got Result : $value")
            }
        }

        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, ResultFragment())
            .commit()

        val activityLauncher: AppActivityResult<Intent, ActivityResult> =
            AppActivityResult.registerActivityForResult(this)

        buttonStartActivityResult.setOnClickListener {
            val intent = Intent(this, ActivityResultB::class.java)
            launcher.launch(intent)
        }
    }

}