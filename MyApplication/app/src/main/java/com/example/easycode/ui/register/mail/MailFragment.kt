package com.example.easycode.ui.register.mail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.easycode.R
import com.example.easycode.databinding.FragmentMailBinding
import com.example.easycode.ui.register.RegisterViewModel

class MailFragment : Fragment(), View.OnClickListener {

    private var viewModel: RegisterViewModel? = null
    lateinit var binding: FragmentMailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_mail, container, false)

        viewModel =
            activity?.let { ViewModelProvider(it).get(RegisterViewModel::class.java) }

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnContinuar.setOnClickListener(this)

        viewModel?.nameErrorMLD?.observe(viewLifecycleOwner, Observer {
            binding.nameInput.error = it
        })

        viewModel?.passErrorMLD?.observe(viewLifecycleOwner, Observer {
            binding.passInput.error = it
        })

        viewModel?.mailErrorMLD?.observe(viewLifecycleOwner, Observer {
            binding.mailInput.error = it
        })

        return binding.root
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnContinuar -> {
                viewModel?.validateData()
                if(viewModel!!.passActivity.value == true){
                    Toast.makeText(context, "Its toast!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}