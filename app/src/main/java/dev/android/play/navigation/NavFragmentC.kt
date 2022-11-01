package dev.android.play.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.android.play.databinding.FragmentNavCBinding


class NavFragmentC : Fragment(), View.OnClickListener {


    private lateinit var binding: FragmentNavCBinding
    private var data: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey("others_to_c")) {
                data = it.getString("others_to_c", "")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNavCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNavigateToB.setOnClickListener(this)
        binding.btnNavigateToA.setOnClickListener(this)
        binding.btnNavigateToD.setOnClickListener(this)
        binding.tvData.text = data
    }

    override fun onClick(v: View) {
        val dataToPass = binding.etPassData.text.toString()
        when (v.id) {

            binding.btnNavigateToB.id -> {
                val action = NavFragmentCDirections.actionNavFragmentCToNavFragmentA(dataToPass)
                findNavController().navigate(action)
            }
            binding.btnNavigateToA.id -> {
                val action = NavFragmentCDirections.actionNavFragmentCToNavFragmentB(dataToPass)
                findNavController().navigate(action)
            }
            binding.btnNavigateToD.id -> {
                val action = NavFragmentCDirections.actionNavFragmentCToNavFragmentD(dataToPass)
                findNavController().navigate(action)
            }
        }
    }

}