package com.example.easycode.ui.register.goal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.easycode.R
import com.example.easycode.databinding.FragmentGoalBinding
import com.example.easycode.ui.register.RegisterConstants
import com.example.easycode.ui.register.RegisterViewModel

class GoalFragment : Fragment(), View.OnClickListener {

    private var viewModel: RegisterViewModel? = null
    lateinit var binding: FragmentGoalBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_goal, container, false)

        viewModel =
            activity?.let { ViewModelProvider(it).get(RegisterViewModel::class.java) }

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnLoss.setOnClickListener(this)
        binding.btnKeep.setOnClickListener(this)
        binding.btnGain.setOnClickListener(this)
        binding.btnContinuar.setOnClickListener(this)

        return binding.root
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnContinuar -> {
                if(viewModel?.goalMLD?.value == null){
                    Toast.makeText(context,"Selecciona un objetivo", Toast.LENGTH_LONG).show()
                }else
                viewModel?.navNext(RegisterConstants.FRAGMENT_GOAL)}
            R.id.btnLoss -> setLossClicked()
            R.id.btnKeep -> setKeepClicked()
            R.id.btnGain -> setGainClicked()
        }
    }

    private fun setGainClicked() {
        viewModel?.setGoal(RegisterConstants.GOAL_GAIN)
    }

    private fun setKeepClicked() {
        viewModel?.setGoal(RegisterConstants.GOAL_KEEP)

    }

    private fun setLossClicked() {
        viewModel?.setGoal(RegisterConstants.GOAL_LOSS)
    }


}