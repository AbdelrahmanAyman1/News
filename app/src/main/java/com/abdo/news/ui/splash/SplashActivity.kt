package com.abdo.news.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.abdo.news.R
import com.abdo.news.ui.home.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            startMainActivity()
        },3000)
    }

    private fun startMainActivity() {
        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}