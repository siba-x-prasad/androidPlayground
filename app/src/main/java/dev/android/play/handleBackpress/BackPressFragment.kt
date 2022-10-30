package dev.android.play.handleBackpress

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import dev.android.play.R

/**
 * https://developer.android.com/reference/androidx/activity/OnBackPressedDispatcher
 */
class BackPressFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Toast.makeText(requireActivity(), "Handled Backpress Here", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_back_press, container, false)
    }
}