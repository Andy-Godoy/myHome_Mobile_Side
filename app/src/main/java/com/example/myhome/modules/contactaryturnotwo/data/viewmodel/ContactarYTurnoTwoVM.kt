package com.example.myhome.modules.contactaryturnotwo.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.contactaryturnotwo.`data`.model.ContactarYTurnoTwoModel
import org.koin.core.KoinComponent

class ContactarYTurnoTwoVM : ViewModel(), KoinComponent {
  val contactarYTurnoTwoModel: MutableLiveData<ContactarYTurnoTwoModel> =
      MutableLiveData(ContactarYTurnoTwoModel())

  var navArguments: Bundle? = null
}
