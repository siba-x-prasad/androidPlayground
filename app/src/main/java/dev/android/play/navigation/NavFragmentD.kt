package dev.android.play.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.android.play.databinding.FragmentNavDBinding

class NavFragmentD : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentNavDBinding

    private var data: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey("others_to_d")) {
                data = it.getString("others_to_d", "")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNavDBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNavigateToB.setOnClickListener(this)
        binding.btnNavigateToC.setOnClickListener(this)
        binding.btnNavigateToA.setOnClickListener(this)

        binding.tvData.text = data
    }

    override fun onClick(v: View) {
        val dataToPass = binding.etPassData.text.toString()
        when (v.id) {

            binding.btnNavigateToB.id -> {
                val action = NavFragmentDDirections.actionNavFragmentDToNavFragmentA(dataToPass)
                findNavController().navigate(action)
            }
            binding.btnNavigateToC.id -> {
                val action = NavFragmentDDirections.actionNavFragmentDToNavFragmentB(dataToPass)
                findNavController().navigate(action)
            }
            binding.btnNavigateToA.id -> {
                val action = NavFragmentDDirections.actionNavFragmentDToNavFragmentC(dataToPass)
                findNavController().navigate(action)
            }
        }
    }
}