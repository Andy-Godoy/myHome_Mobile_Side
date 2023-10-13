package com.example.myhome.modules.modificarpropiedadpartefour.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.modificarpropiedadpartefour.`data`.model.ListticketRowModel
import com.example.myhome.modules.modificarpropiedadpartefour.`data`.model.ModificarPropiedadParteFourModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class ModificarPropiedadParteFourVM : ViewModel(), KoinComponent {
  val modificarPropiedadParteFourModel: MutableLiveData<ModificarPropiedadParteFourModel> =
      MutableLiveData(ModificarPropiedadParteFourModel())

  var navArguments: Bundle? = null

  val listticketList: MutableLiveData<MutableList<ListticketRowModel>> =
      MutableLiveData(mutableListOf())
}
