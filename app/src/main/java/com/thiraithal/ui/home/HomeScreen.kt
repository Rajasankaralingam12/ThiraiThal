package com.thiraithal.ui.home

/*
* https://devcenter.heroku.com/articles/heroku-cli#download-and-install
* */
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.thiraithal.ui.wallpapers.homeWallpapers.WallpapersFragment
import com.thiraithal.R
import com.thiraithal.ui.videowallpapers.VideoWallpapersFragment
import com.thiraithal.ui.base.BaseMenuActivity
import com.thiraithal.ui.wallpapers.WallpaperPagerFragment
import com.thiraithal.ui.upload.UploadFragment
import com.thiraithal.ui.wallpapers.premium.PremiumFragment


class HomeScreen : BaseMenuActivity(){

    override fun getLayoutResId(): Int {
      return R.layout.activity_home_screen
    }

    override fun activityCreated() {
        nvDrawer  = findViewById(R.id.nvView)
        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)

        setUpActionBar()
        setupDrawerContent(nvDrawer)
        setInitialFragment(WallpaperPagerFragment())
    }

    override fun addDrawerListener(navigationView: NavigationView) {
        navigationView.itemIconTintList = null
        navigationView.setNavigationItemSelectedListener { menuItem ->
            selectDrawerItem(menuItem)
            true
        }
    }

    fun selectDrawerItem( menuItem : MenuItem) {
       lateinit var fragment : Fragment
        when(menuItem.getItemId()) {
            R.id.nav_wallpapers->{
                fragment =
                    WallpaperPagerFragment()
            }

            R.id.nav_video_wallpapers ->{
                fragment =
                    PremiumFragment()
            }
            R.id.nav_ring_tones->{
                fragment =
                    PremiumFragment()
            }
            R.id.nav_upload->{
                fragment =
                    UploadFragment()
            }
        }
        replaceFragment(menuItem, fragment)
    }
}