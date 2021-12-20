package com.example.easycode.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.easycode.R
import com.example.easycode.databinding.FragmentLoginBinding

class LoginFragment: Fragment(), View.OnClickListener {

    private var viewModel: LoginViewModel? = null
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        viewModel =
            activity?.let { ViewModelProvider(it).get(LoginViewModel::class.java) }

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnContinuar.setOnClickListener(this)


        viewModel?.passErrorMLD?.observe(viewLifecycleOwner, Observer {
            binding.passInput.error = it
        })

        viewModel?.mailErrorMLD?.observe(viewLifecycleOwner, Observer {
            binding.mailInput.error = it
        })

        viewModel?.passActivity?.observe(viewLifecycleOwner, Observer {
            if(it){
                //TODO: Lanzar nueva activity main
            }
        })

        return binding.root
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnContinuar -> {
                viewModel?.login()
            }
        }
    }

}