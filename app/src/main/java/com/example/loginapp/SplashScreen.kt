package com.example.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        var time : Long = 2500
        Handler().postDelayed({
            var intent = Intent(SplashScreen@this, LoggedIn::class.java)
            startActivity(intent)
            finish()
        }, time)
    }
}