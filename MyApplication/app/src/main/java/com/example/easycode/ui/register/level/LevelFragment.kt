package com.example.easycode.ui.register.level

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.easycode.R
import com.example.easycode.databinding.FragmentLevelBinding
import com.example.easycode.ui.register.RegisterConstants
import com.example.easycode.ui.register.RegisterViewModel

class LevelFragment : Fragment(), View.OnClickListener {

    private var viewModel: RegisterViewModel? = null
    private lateinit var binding: FragmentLevelBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_level, container, false)

        viewModel =
            activity?.let { ViewModelProvider(it).get(RegisterViewModel::class.java) }

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnBeginner.setOnClickListener(this)
        binding.btnMiddle.setOnClickListener(this)
        binding.btnAdvanced.setOnClickListener(this)
        binding.btnContinuar.setOnClickListener(this)

        return binding.root
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnContinuar -> {
                if(viewModel?.levelMLD?.value == null){
                    Toast.makeText(context,"Selecciona un genero", Toast.LENGTH_LONG).show()
                }else
                viewModel?.navNext(RegisterConstants.FRAGMENT_LEVEL)}
            R.id.btnBeginner -> setBeginnerClicked()
            R.id.btnMiddle -> setMiddleClicked()
            R.id.btnAdvanced -> setAdvancedClicked()
        }
    }

    private fun setAdvancedClicked() {
        viewModel?.setLevel(RegisterConstants.LEVEL_ADV)
    }

    private fun setMiddleClicked() {
        viewModel?.setLevel(RegisterConstants.LEVEL_INTER)
    }

    private fun setBeginnerClicked() {
        viewModel?.setLevel(RegisterConstants.LEVEL_BEG)
    }
}