package dev.android.play.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import dev.android.play.R
import dev.android.play.databinding.ActivityNavigationBinding
import kotlinx.coroutines.*

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        changeDefaultDestination()

        GlobalScope.launch {
            delay(5000)
            withContext(Dispatchers.Main) {
                redirectToAnotherScreenAfterSplash()
            }
        }

    }

    private fun changeDefaultDestination() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph_navigation)
        navGraph.setStartDestination(R.id.navFragmentD)
        navController.graph = navGraph
    }

    private fun redirectToSomeFragment() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val navOptions = NavOptions.Builder().setPopUpTo(
            R.id.navFragmentD,
            inclusive = true,
            saveState = false
        )

        navController.navigate(R.id.navFragmentD)
        changeDefaultDestination()
    }

    private fun redirectToAnotherScreenAfterSplash() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController
            .navigate(
                R.id.action_navFragmentSplash_to_navFragmentA,
                null,
                NavOptions.Builder()
                    .setPopUpTo(
                        R.id.navFragmentSplash,
                        true
                    ).build()
            )
    }

}