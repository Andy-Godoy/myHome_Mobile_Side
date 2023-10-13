package com.example.myhome.modules.avisogps.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.avisogps.`data`.model.AvisoGpsModel
import org.koin.core.KoinComponent

class AvisoGpsVM : ViewModel(), KoinComponent {
  val avisoGpsModel: MutableLiveData<AvisoGpsModel> = MutableLiveData(AvisoGpsModel())

  var navArguments: Bundle? = null
}
