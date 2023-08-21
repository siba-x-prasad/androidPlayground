package swasi.android.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding>(private val mViewModelClass: Class<VM>) :
    AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun getViewModel(): VM

    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DB
    }

    /**
     * If you want to inject Dependency Injection
     * on your activity, you can override this.
     */
    open fun onInject() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        initViewModel(getViewModel())

        super.onCreate(savedInstanceState)

        onInject()
    }

    /**
     *
     *  You need override this method.
     *  And you need to set viewModel to binding: binding.viewModel = viewModel
     *
     */
    abstract fun initViewModel(viewModel: VM)
}