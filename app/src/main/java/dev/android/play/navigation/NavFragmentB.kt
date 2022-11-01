package dev.android.play.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.android.play.databinding.FragmentNavBBinding


class NavFragmentB(enabled: Boolean) : Fragment(), View.OnClickListener {

    lateinit var binding: FragmentNavBBinding

    private var data: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey("others_to_b")) {
                data = it.getString("others_to_b", "")
            }
        }

        // backpress callback
        val backPressCallBack = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(backPressCallBack)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNavBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNavigateToA.setOnClickListener(this)
        binding.btnNavigateToC.setOnClickListener(this)
        binding.btnNavigateToD.setOnClickListener(this)

        binding.tvData.text = data
    }

    override fun onClick(v: View) {
        val dataToPass = binding.etPassData.text.toString()
        when (v.id) {

            binding.btnNavigateToA.id -> {
                val action = NavFragmentBDirections.actionNavFragmentBToNavFragmentA(dataToPass)
                findNavController().navigate(action)
            }
            binding.btnNavigateToC.id -> {
                val action = NavFragmentBDirections.actionNavFragmentBToNavFragmentC(dataToPass)
                findNavController().navigate(action)
            }
            binding.btnNavigateToD.id -> {
                val action = NavFragmentBDirections.actionNavFragmentBToNavFragmentD(dataToPass)
                findNavController().navigate(action)
            }
        }
    }

}