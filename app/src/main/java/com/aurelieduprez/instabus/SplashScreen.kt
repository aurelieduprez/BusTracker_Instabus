package com.aurelieduprez.instabus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log

private val splashTimer = 3000L;
private lateinit var myHandler : Handler


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        myHandler = Handler()
        myHandler.postDelayed({
            gotoMainActivity()
        }, splashTimer)
    }

    private fun gotoMainActivity(){
        val mainActivityIntent = Intent(applicationContext, MainActivity::class.java)
        startActivity(mainActivityIntent)

        finish()
    }

}
