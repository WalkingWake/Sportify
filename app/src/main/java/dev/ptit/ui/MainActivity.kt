package dev.ptit.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import dev.ptit.R
import dev.ptit.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpBottomNavigation()
        window.navigationBarColor = ContextCompat.getColor(this, R.color.white)
    }

    private fun setUpBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView: BottomNavigationView = binding.bottomNavigationView
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    binding.ivScreenIcon.setImageResource(R.drawable.app_icon)
                    binding.tvScreenTitle.text = getString(R.string.tab_home_title)
                    binding.bottomNavigationView.visibility = View.VISIBLE
                    binding.clToolbar.visibility = View.VISIBLE
                }

                R.id.newsFragment -> {
                    binding.ivScreenIcon.setImageResource(R.drawable.icon_selected_tab_news)
                    binding.tvScreenTitle.text = getString(R.string.tab_news_title)
                    binding.bottomNavigationView.visibility = View.VISIBLE
                    binding.clToolbar.visibility = View.VISIBLE
                }

                R.id.matchesFragment -> {
                    binding.ivScreenIcon.setImageResource(R.drawable.icon_selected_tab_matches)
                    binding.tvScreenTitle.text = getString(R.string.tab_matches_title)
                    binding.bottomNavigationView.visibility = View.VISIBLE
                    binding.clToolbar.visibility = View.VISIBLE
                }

                R.id.settingsFragment -> {
                    binding.ivScreenIcon.setImageResource(R.drawable.icon_selected_tab_settings)
                    binding.tvScreenTitle.text = getString(R.string.tab_settings_title)
                    binding.bottomNavigationView.visibility = View.VISIBLE
                    binding.clToolbar.visibility = View.VISIBLE
                }

                else -> {
                    binding.bottomNavigationView.visibility = View.GONE
                    binding.clToolbar.visibility = View.GONE
                }
            }
        }
    }
}