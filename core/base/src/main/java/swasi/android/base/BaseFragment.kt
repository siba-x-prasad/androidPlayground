package swasi.android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding>() : Fragment() {

    lateinit var viewModel: VM
    open lateinit var mBinding: DB
    fun init(inflater: LayoutInflater, container: ViewGroup) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
    }

    open fun init() {}

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun getViewM(): VM
    open fun onInject() {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewM()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        init(inflater, container!!)
        init()
        super.onCreateView(inflater, container, savedInstanceState)
        return mBinding.root
    }

    open fun refresh() {}
}