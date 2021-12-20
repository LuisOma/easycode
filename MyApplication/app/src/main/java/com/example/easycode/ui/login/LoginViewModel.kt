package com.example.easycode.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.easycode.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    var navController: NavController? = null
    val mailMLD = MutableLiveData<String>()
    val mailErrorMLD = MutableLiveData<String>()
    val passMLD = MutableLiveData<String>()
    val passErrorMLD = MutableLiveData<String>()
    var passActivity = MutableLiveData<Boolean>()
    val lottieVisible = MutableLiveData<Boolean>(false)


    fun login(){
        if(mailMLD.value.isNullOrEmpty()){
            mailErrorMLD.value = "Escribe un correo"
        }
        else if(passMLD.value.isNullOrEmpty()){
            passErrorMLD.value = "Escribe una contraseña"
        }else{
            mailErrorMLD.value = null
            passErrorMLD.value = null
            callService()
        }
    }

    private fun callService() {
        lottieVisible.value = true
        GlobalScope.launch {
            val rqt = UserRepository.login(
                mailMLD.value?:"",
                passMLD.value?:""
            )
            launch(Dispatchers.Main) {
                lottieVisible.value = false
                if(rqt.state.toString() == "200"){
                    passActivity.value = true
                } else {
                    //TODO: cachar error
                }
            }

        }
    }
}