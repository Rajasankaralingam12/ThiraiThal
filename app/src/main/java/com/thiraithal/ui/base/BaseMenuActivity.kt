package com.thiraithal.ui.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import com.thiraithal.R

abstract class BaseMenuActivity : AppCompatActivity(){

    protected lateinit var drawerLayout: DrawerLayout
    protected lateinit var toolbar: Toolbar
    protected lateinit var nvDrawer: NavigationView

    protected abstract fun getLayoutResId(): Int

    protected abstract fun addDrawerListener(navigationView: NavigationView)

    protected abstract fun activityCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        activityCreated()


    }

    protected fun setUpActionBar(){

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
    }
    protected fun setInitialFragment(fragment: Fragment){
        toolbar.setTitle("Wallpapers")
        val fragmentManager : FragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment, "Home").commit()
    }

    protected fun setupDrawerContent(navigationView: NavigationView) {

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        addDrawerListener(navigationView)

    }

    protected fun replaceFragment(menuItem : MenuItem, fragment : Fragment){
        val fragmentManager : FragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit()
        menuItem.setChecked(true);
        toolbar.setTitle(menuItem.getTitle())
        drawerLayout.closeDrawers()
    }
}