package com.example.easycode.ui.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.easycode.ui.register.RegisterActivity

class SplashActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }

}