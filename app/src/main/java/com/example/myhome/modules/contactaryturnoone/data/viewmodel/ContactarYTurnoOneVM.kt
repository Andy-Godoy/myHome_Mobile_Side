package com.example.myhome.modules.contactaryturnoone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.contactaryturnoone.`data`.model.ContactarYTurnoOneModel
import org.koin.core.KoinComponent

class ContactarYTurnoOneVM : ViewModel(), KoinComponent {
  val contactarYTurnoOneModel: MutableLiveData<ContactarYTurnoOneModel> =
      MutableLiveData(ContactarYTurnoOneModel())

  var navArguments: Bundle? = null
}
