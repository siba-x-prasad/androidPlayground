package dev.android.play.roomdatabase.ui.add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import dev.android.play.R

@AndroidEntryPoint
class AddUserFragment : Fragment() {

    companion object {
        fun newInstance() = AddUserFragment()
    }

    private lateinit var viewModel: AddUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[AddUserViewModel::class.java]

        viewModel.postTestData()
        viewModel.setTestData()

        viewModel.checkRunBlocking()

        viewModel.testData.observe(viewLifecycleOwner, {
            Log.i("AddUserFragment", it)
        })
    }

}