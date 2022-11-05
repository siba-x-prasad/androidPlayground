package dev.android.play.miscellaneous.observablecomparison

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.android.play.databinding.FragmentObservableComparisonBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * https://www.youtube.com/watch?v=6Jc6-INantQ
 */

@AndroidEntryPoint
class ObservableComparisonFragment : Fragment() {

    private lateinit var binding: FragmentObservableComparisonBinding

    private val viewModel by viewModels<ObservableComparisonViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentObservableComparisonBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservable()
    }

    private fun setObservable() {
        viewModel.liveData.observe(this.viewLifecycleOwner) {
            binding.tvLiveData.text = it
        }

        lifecycleScope.launchWhenStarted {
            viewModel.stateFlow.collectLatest {
                binding.tvStateFlow.text = it
            }
        }

        lifecycleScope.launch {
            viewModel.triggerFlow().collectLatest {
                binding.tvFlow.text = it
            }
        }

        lifecycleScope.launch {
            viewModel.mutableSharedFlow.collectLatest {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
            }
        }

    }

}