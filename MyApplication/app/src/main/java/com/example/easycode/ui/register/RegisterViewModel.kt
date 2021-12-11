package com.example.easycode.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.easycode.R
import com.example.easycode.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    var navController: NavController? = null
     val genderMLD = MutableLiveData<Int>()
     val goalMLD = MutableLiveData<Int>()
     val levelMLD = MutableLiveData<Int>()
     val weightMLD = MutableLiveData<String>()
     val ageMLD = MutableLiveData<String>()
     val weightErrorMLD = MutableLiveData<String>()
     val ageErrorMLD = MutableLiveData<String>()
    val nameMLD = MutableLiveData<String>()
    val nameErrorMLD = MutableLiveData<String>()
    val mailMLD = MutableLiveData<String>()
    val mailErrorMLD = MutableLiveData<String>()
    val passMLD = MutableLiveData<String>()
    val passErrorMLD = MutableLiveData<String>()
    var passActivity = MutableLiveData<Boolean>()


    fun setGender(value: Int) {
        genderMLD.value = value
    }

    fun setGoal(value: Int) {
        goalMLD.value = value
    }

    fun setLevel(value: Int) {
        levelMLD.value = value
    }

    fun setWeight(value: String) {
        if (value.isNullOrEmpty()) {
            weightErrorMLD.value = "Por favor inserta un peso válido"
        } else {
            navNext(RegisterConstants.FRAGMENT_WEIGHT)
            weightMLD.value = value

        }
    }

    fun setAge(value: String) {
        if (value.isNullOrEmpty()) {
            ageErrorMLD.value = "Por favor inserta una edad válida"
        } else {
            ageMLD.value = value
            navNext(RegisterConstants.FRAGMENT_AGE)
        }
    }

    fun getWeightError() = weightErrorMLD
    fun getAgeError() = ageErrorMLD

    fun navNext(fragmentId: Int) {
        if (fragmentId == RegisterConstants.FRAGMENT_MAIL) {
            callService()
        } else {
            val action =
                when (fragmentId) {
                    RegisterConstants.FRAGMENT_GENDER -> R.id.action_genderFragment_to_goalFragment
                    RegisterConstants.FRAGMENT_GOAL -> R.id.action_goalFragment_to_levelFragment
                    RegisterConstants.FRAGMENT_LEVEL -> R.id.action_levelFragment_to_weightFragment
                    RegisterConstants.FRAGMENT_WEIGHT -> R.id.action_weightFragment_to_ageFragment
                    RegisterConstants.FRAGMENT_AGE -> R.id.action_ageFragment_to_mailFragment
                    else -> 0
                }
            navController?.navigate(action)
        }
    }

    private fun callService() {
        GlobalScope.launch {
            val rqt = UserRepository.register(nameMLD.value?:"",
                mailMLD.value?:"",
                passMLD.value?:"",
                genderMLD.value?:1,
                goalMLD.value?:1,
                levelMLD.value?:1,
                weightMLD.value?:"",
                ageMLD.value?:""
                )
            launch(Dispatchers.Main) {
            }
            if(rqt.state.toString() == "200"){
                passActivity.postValue(true)
                } else {
                nameErrorMLD.value = "Escribe un nombre"
            }
        }
    }

    fun validateData() {
        if(nameMLD.value.isNullOrEmpty()){
            nameErrorMLD.value = "Escribe un nombre"
        }
        else if(mailMLD.value.isNullOrEmpty()){
            mailErrorMLD.value = "Escribe un correo"
        }
        else if(passMLD.value.isNullOrEmpty()){
            passErrorMLD.value = "Escribe una contraseña"
        }
        else{
            nameErrorMLD.value = null
            mailErrorMLD.value = null
            passErrorMLD.value = null
            navNext(RegisterConstants.FRAGMENT_MAIL)
        }
    }

}