package com.example.easycode.ui.register.weight

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
import com.example.easycode.databinding.FragmentWeightBinding
import com.example.easycode.ui.register.RegisterConstants
import com.example.easycode.ui.register.RegisterViewModel

class WeightFragment : Fragment(), View.OnClickListener {

    private var viewModel: RegisterViewModel? = null
    private lateinit var binding: FragmentWeightBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_weight, container, false)

        viewModel =
            activity?.let { ViewModelProvider(it).get(RegisterViewModel::class.java) }

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnContinuar.setOnClickListener(this)

        viewModel?.getWeightError()?.observe(viewLifecycleOwner, Observer {
            if(!it.isNullOrEmpty()){
                Toast.makeText(context,it,Toast.LENGTH_LONG).show()
            }
        })

        return binding.root
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnContinuar -> {
                viewModel?.setWeight(binding.edtWeight.text.toString())
            }
        }
    }
}