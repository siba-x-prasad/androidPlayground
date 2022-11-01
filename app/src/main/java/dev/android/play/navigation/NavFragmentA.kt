package dev.android.play.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.android.play.databinding.FragmentNavABinding

class NavFragmentA : Fragment(), View.OnClickListener {


    private lateinit var binding: FragmentNavABinding

    private var data: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey("others_to_a")) {
                data = it.getString("others_to_a", "")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNavABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNavigateToB.setOnClickListener(this)
        binding.btnNavigateToC.setOnClickListener(this)
        binding.btnNavigateToD.setOnClickListener(this)

        binding.tvData.text = data
    }

    override fun onClick(v: View) {
        val dataToPass = binding.etPassData.text.toString()
        when (v.id) {

            binding.btnNavigateToB.id -> {
                val action = NavFragmentADirections.actionNavFragmentAToNavFragmentB(dataToPass)
                findNavController().navigate(action)
            }
            binding.btnNavigateToC.id -> {
                val action = NavFragmentADirections.actionNavFragmentAToNavFragmentC(dataToPass)
                findNavController().navigate(action)
            }
            binding.btnNavigateToD.id -> {
                val action = NavFragmentADirections.actionNavFragmentAToNavFragmentD(dataToPass)
                findNavController().navigate(action)
            }
        }
    }

}