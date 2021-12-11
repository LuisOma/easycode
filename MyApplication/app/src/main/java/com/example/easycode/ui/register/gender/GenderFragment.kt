package com.example.easycode.ui.register.gender

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.easycode.R
import com.example.easycode.databinding.FragmentGenderBinding
import com.example.easycode.ui.register.RegisterConstants
import com.example.easycode.ui.register.RegisterViewModel

class GenderFragment : Fragment(), View.OnClickListener {

    private var viewModel: RegisterViewModel? = null
    private lateinit var binding: FragmentGenderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_gender, container, false)

        viewModel =
            activity?.let { ViewModelProvider(it).get(RegisterViewModel::class.java) }

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnContinuar.setOnClickListener(this)
        binding.btnFem.setOnClickListener(this)
        binding.btnMasc.setOnClickListener(this)

        return binding.root
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnContinuar -> {
                if(viewModel?.genderMLD?.value == null){
                    Toast.makeText(context,"Selecciona un genero", Toast.LENGTH_LONG).show()
                }else
                viewModel?.navNext(RegisterConstants.FRAGMENT_GENDER)}
            R.id.btnFem -> setFemClicked()
            R.id.btnMasc -> setMascClicked()
        }
    }

    private fun setMascClicked() {
        viewModel?.setGender(RegisterConstants.GENDER_MASC)
    }

    private fun setFemClicked() {
        viewModel?.setGender(RegisterConstants.GENDER_FEM)
    }


}