package dev.android.play.miscellaneous.activityresult

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import dev.android.play.R

class ResultFragment : Fragment() {

    lateinit var etInputToPassFromFragment: AppCompatEditText
    lateinit var buttonStartActivityResultFromFragment: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etInputToPassFromFragment = view.findViewById(R.id.etInputToPassFromFragment)
        buttonStartActivityResultFromFragment =
            view.findViewById(R.id.buttonStartActivityResultFromFragment)

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val value = it.data?.getStringExtra("input")
                etInputToPassFromFragment.setText("Got Result : $value")
            }
        }

        buttonStartActivityResultFromFragment.setOnClickListener {
            val intent = Intent(requireActivity(), ActivityResultB::class.java)
            launcher.launch(intent)
        }

    }
}