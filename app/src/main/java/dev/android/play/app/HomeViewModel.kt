package dev.android.play.app

import android.content.Intent
import android.view.View
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.android.play.account.AccountActivity
import dev.android.play.utility.ObservableViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ObservableViewModel() {

    fun onClickStart(view: View) {
        view.context.startActivity(Intent(view.context, AccountActivity::class.java))
    }

}