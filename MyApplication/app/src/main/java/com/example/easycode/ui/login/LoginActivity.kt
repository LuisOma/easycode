package com.example.easycode.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.easycode.R
import com.example.easycode.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    var viewModel: LoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_login
        )
        supportActionBar?.hide()

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.executePendingBindings()

        viewModel!!.navController = Navigation.findNavController(
            this, R.id.loginContainer
        )
    }
}