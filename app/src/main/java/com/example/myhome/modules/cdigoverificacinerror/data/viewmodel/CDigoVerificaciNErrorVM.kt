package com.example.myhome.modules.cdigoverificacinerror.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.cdigoverificacinerror.`data`.model.CDigoVerificaciNErrorModel
import org.koin.core.KoinComponent

class CDigoVerificaciNErrorVM : ViewModel(), KoinComponent {
  val cDigoVerificaciNErrorModel: MutableLiveData<CDigoVerificaciNErrorModel> =
      MutableLiveData(CDigoVerificaciNErrorModel())

  var navArguments: Bundle? = null
}
