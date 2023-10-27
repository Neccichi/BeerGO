package com.beergo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.beergo.fragment_beer.BeerFragment
import com.beergo.fragment_mail.MailFragment
import com.beergo.fragment_profile.ProfileFragment
import com.beergo.fragment_search.SearchFragment
import com.beergo.fragment_settings.SettingsFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar



class MainActivity : AppCompatActivity() {
    lateinit var chipNavigationBar: ChipNavigationBar
    lateinit var loadingScreen: ImageView
    lateinit var container: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )
        window.decorView.setOnSystemUiVisibilityChangeListener { visibility ->
            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                window.decorView.systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                or View.SYSTEM_UI_FLAG_FULLSCREEN
                                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        )
            }
        }
        setContentView(R.layout.activity_main)
        chipNavigationBar = findViewById(R.id.chipNavigation)
        loadingScreen = findViewById(R.id.loadingScreen)
        container = findViewById(R.id.container)

        loadingScreen.bringToFront()
        Handler(Looper.getMainLooper()).postDelayed({
            val fadeOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out)
            loadingScreen.startAnimation(fadeOut)
            loadingScreen.visibility = View.GONE
        }, 1300)
        replaceFragment(BeerFragment())
        chipNavigationBar.setItemSelected(R.id.ic_beer, true)
        chipNavigationBar.setOnItemSelectedListener { id ->
            val fragment = when (id) {
                R.id.ic_beer -> BeerFragment()
                R.id.ic_mail -> MailFragment()
                R.id.ic_search -> SearchFragment()
                R.id.ic_profile -> ProfileFragment()
                R.id.ic_settings -> SettingsFragment()
                else -> BeerFragment()
            }
            replaceFragment(fragment)
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}