package com.example.easycode.ui.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.easycode.R
import com.example.easycode.databinding.ActivityRegisterBinding
import com.example.easycode.ui.main.MainViewModel

class RegisterActivity  : AppCompatActivity() {

    var viewModel: RegisterViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityRegisterBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_register
        )
        supportActionBar?.hide()

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.executePendingBindings()

        viewModel!!.navController = Navigation.findNavController(
            this, R.id.regContainer
        )
    }
}