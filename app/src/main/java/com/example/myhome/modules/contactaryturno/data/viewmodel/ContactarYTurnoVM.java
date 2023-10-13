package com.example.myhome.modules.contactaryturno.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.contactaryturno.`data`.model.ContactarYTurnoModel
import org.koin.core.KoinComponent

class ContactarYTurnoVM : ViewModel(), KoinComponent {
  val contactarYTurnoModel: MutableLiveData<ContactarYTurnoModel> =
      MutableLiveData(ContactarYTurnoModel())

  var navArguments: Bundle? = null
}
