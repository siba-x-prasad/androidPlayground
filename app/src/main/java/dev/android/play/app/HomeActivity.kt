package dev.android.play.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import dev.android.play.R

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        redirect(NavigationRoutes.XML_PARSING)
    }

    private fun startAppActivity() {
//        startActivity(Intent(this@HomeActivity, MovieDBActivity::class.java))
    }

    private fun redirect(destinationId: Int) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.home_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph_home)
        navGraph.setStartDestination(destinationId)
        navController.graph = navGraph
    }
}