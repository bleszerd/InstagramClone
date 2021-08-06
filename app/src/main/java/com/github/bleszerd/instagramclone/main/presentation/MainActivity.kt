package com.github.bleszerd.instagramclone.main.presentation

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.content.res.AppCompatResources
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.common.view.AbstractActivity
import com.github.bleszerd.instagramclone.databinding.ActivityMainBinding
import com.github.bleszerd.instagramclone.main.camera.presentation.CameraFragment
import com.github.bleszerd.instagramclone.main.home.presentation.HomeFragment
import com.github.bleszerd.instagramclone.main.profile.presentation.ProfileFragment
import com.github.bleszerd.instagramclone.main.search_presentation.SearchFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AbstractActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    MainView {
    private lateinit var binding: ActivityMainBinding

    lateinit var homeFragment: Fragment
    lateinit var profileFragment: Fragment
    lateinit var cameraFragment: Fragment
    lateinit var searchFragment: Fragment
    lateinit var active: Fragment

    companion object {
        const val ACT_SOURCE: String = "act_source"
        const val LOGIN_ACTIVITY = 0
        const val REGISTER_ACTIVITY = 1


        fun launch(context: Context, source: Int) {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(ACT_SOURCE, source)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setLightStatusBar()

        configureToolbar()

        setContentView(binding.root)
    }

    private fun configureToolbar() {
        val toolbar = binding.activityMainToolbarToolbar
        setSupportActionBar(toolbar)

        if (supportActionBar != null) {
            val cameraDrawable =
                AppCompatResources.getDrawable(applicationContext, R.drawable.ic_insta_camera)

            supportActionBar!!.title = ""
            supportActionBar!!.setHomeAsUpIndicator(cameraDrawable)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setLightStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 and above
            window.statusBarColor = findColor(R.color.gray)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4 to 5.0
            val localLayoutParams = window.attributes
            localLayoutParams.flags =
                (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or localLayoutParams.flags)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0 can modify the status bar text color and icon later
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    override fun getContext(): Context {
        return applicationContext
    }

    override fun onInject() {
        homeFragment = HomeFragment.newInstance(this)
        profileFragment = ProfileFragment.newInstance(this)
        cameraFragment = CameraFragment()
        searchFragment = SearchFragment()

        active = homeFragment

        val fm = supportFragmentManager
        fm.beginTransaction()
            .add(R.id.mainActivityFragmentFragmentHost, profileFragment)
            .hide(profileFragment)
            .commit()

        fm.beginTransaction()
            .add(R.id.mainActivityFragmentFragmentHost, cameraFragment)
            .hide(cameraFragment)
            .commit()

        fm.beginTransaction()
            .add(R.id.mainActivityFragmentFragmentHost, searchFragment)
            .hide(searchFragment)
            .commit()

        fm.beginTransaction()
            .add(R.id.mainActivityFragmentFragmentHost, homeFragment)
            .hide(homeFragment)
            .commit()

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        binding.mainActivityBottomNavigationBottomBar.setOnNavigationItemSelectedListener(this)

        val fm = supportFragmentManager
        val extras = intent.extras
        if (extras != null) {
            val source = extras.getInt(ACT_SOURCE)
            when (source) {
                REGISTER_ACTIVITY -> {
                    fm.beginTransaction().hide(active).show(profileFragment).commit()
                    active = profileFragment
                    scrollToolbarEnabled(true)
                }
                LOGIN_ACTIVITY -> {

                }
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fm = supportFragmentManager
        when (item.itemId) {
            R.id.menuBottomHomeButton -> {
                fm.beginTransaction()
                    .hide(active)
                    .show(homeFragment)
                    .commit()

                active = homeFragment
                scrollToolbarEnabled(false)
                return true
            }

            R.id.menuBottomSearchButton -> {
                fm.beginTransaction()
                    .hide(active)
                    .show(searchFragment)
                    .commit()

                active = searchFragment
                return true
            }

            R.id.menuBottomCameraButton -> {
                fm.beginTransaction()
                    .hide(active)
                    .show(cameraFragment)
                    .commit()

                active = cameraFragment
                return true
            }

            R.id.menuBottomProfileButton -> {
                fm.beginTransaction()
                    .hide(active)
                    .show(profileFragment)
                    .commit()

                active = profileFragment
                scrollToolbarEnabled(true)
                return true
            }
        }

        return false
    }

    override fun scrollToolbarEnabled(enabled: Boolean) {
        val toolbar = binding.activityMainToolbarToolbar
        val appBarLayout = binding.mainActivityAppBarLayoutAppBar

        val params = toolbar.layoutParams as AppBarLayout.LayoutParams
        val appBarLayoutParams = appBarLayout.layoutParams as CoordinatorLayout.LayoutParams

        if (enabled) {
            params.scrollFlags =
                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS

            appBarLayoutParams.behavior = AppBarLayout.Behavior()
            appBarLayout.layoutParams = appBarLayoutParams
        } else {
            params.scrollFlags = 0
            appBarLayoutParams.behavior = null
            appBarLayout.layoutParams = appBarLayoutParams
        }

    }

}