package com.example.myhome.modules.modalfeedback.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.modalfeedback.`data`.model.ModalFeedbackModel
import org.koin.core.KoinComponent

class ModalFeedbackVM : ViewModel(), KoinComponent {
  val modalFeedbackModel: MutableLiveData<ModalFeedbackModel> =
      MutableLiveData(ModalFeedbackModel())

  var navArguments: Bundle? = null
}
