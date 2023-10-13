package com.example.myhome.modules.resenas.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.resenas.`data`.model.ResenasModel
import com.example.myhome.modules.resenas.`data`.model.ResenasRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class ResenasVM : ViewModel(), KoinComponent {
  val resenasModel: MutableLiveData<ResenasModel> = MutableLiveData(ResenasModel())

  var navArguments: Bundle? = null

  val resenasList: MutableLiveData<MutableList<ResenasRowModel>> = MutableLiveData(mutableListOf())
}
