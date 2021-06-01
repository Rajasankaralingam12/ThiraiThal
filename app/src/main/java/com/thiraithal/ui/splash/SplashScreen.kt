package com.thiraithal.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.thiraithal.R
import com.thiraithal.ui.home.HomeScreen

class SplashScreen : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        startApp()
    }

    /* This is used to hide the status bar and make
       the splash screen as a full screen activity.*/
    private fun startApp(){
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Handler().postDelayed({
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}