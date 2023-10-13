package com.example.myhome.modules.valoracionesycomentarios.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhome.modules.valoracionesycomentarios.`data`.model.ListellipseelevenRowModel
import com.example.myhome.modules.valoracionesycomentarios.`data`.model.ValoracionesYComentariosModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class ValoracionesYComentariosVM : ViewModel(), KoinComponent {
  val valoracionesYComentariosModel: MutableLiveData<ValoracionesYComentariosModel> =
      MutableLiveData(ValoracionesYComentariosModel())

  var navArguments: Bundle? = null

  val listellipseelevenList: MutableLiveData<MutableList<ListellipseelevenRowModel>> =
      MutableLiveData(mutableListOf())
}
