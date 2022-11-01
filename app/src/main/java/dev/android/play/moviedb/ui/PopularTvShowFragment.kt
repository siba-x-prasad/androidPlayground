package dev.android.play.moviedb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.android.play.databinding.FragmentPopularTvshowBinding

@AndroidEntryPoint
class PopularTvShowFragment : Fragment() {

    private val viewModel by viewModels<PopularTvShowViewModel>()
    private var _binding: FragmentPopularTvshowBinding? = null
    private var isLoading = false

    private val adapter: PopularTvShowAdapter by lazy {
        PopularTvShowAdapter()
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularTvshowBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.viewModel = viewModel

        setUpUI()

        /*lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                when (it) {
                    is PopularTvNewsFlowUiState.Loading -> {

                    }
                    is PopularTvNewsFlowUiState.Success -> {
                        adapter.updateItems(it.popularTvShows.results)
                    }
                    is PopularTvNewsFlowUiState.Error -> {
                        Toast.makeText(requireActivity(), it.error, Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }
        }*/
    }

    private fun setUpUI() {
//        (activity as MovieDbActivity).supportActionBar?.title = getString(R.string.popular_tv_show)
//        (activity as MovieDbActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
//        binding.viewModel = viewModel
        _binding?.recyclerViewBeer?.adapter = adapter
        initScrollListener()
    }

    private fun initScrollListener() {
        binding.recyclerViewBeer.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val gridLayoutManager = recyclerView.layoutManager as GridLayoutManager?
                if (!isLoading) {
                    if (gridLayoutManager != null && gridLayoutManager.findLastCompletelyVisibleItemPosition() >= ((adapter.itemCount / 2) - 2)) {
                        viewModel.getPopularTvShows()
                    }
                }
            }
        })
    }

}