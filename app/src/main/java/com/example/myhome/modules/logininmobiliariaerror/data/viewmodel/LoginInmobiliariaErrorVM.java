package com.example.myhome.modules.logininmobiliariaerror.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.logininmobiliariaerror.`data`.model.LoginInmobiliariaErrorModel
import org.koin.core.KoinComponent

class LoginInmobiliariaErrorVM : ViewModel(), KoinComponent {
  val loginInmobiliariaErrorModel: MutableLiveData<LoginInmobiliariaErrorModel> =
      MutableLiveData(LoginInmobiliariaErrorModel())

  var navArguments: Bundle? = null
}
