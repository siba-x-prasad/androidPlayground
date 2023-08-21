package swasi.android.play.app

import android.app.Application
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AndroidPlaygroundApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}