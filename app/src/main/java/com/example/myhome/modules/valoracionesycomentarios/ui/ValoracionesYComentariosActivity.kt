package com.example.myhome.modules.valoracionesycomentarios.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityValoracionesYComentariosBinding
import com.example.myhome.modules.perfilinmobiliariaone.ui.PerfilInmobiliariaOneActivity
import com.example.myhome.modules.valoracionesycomentarios.`data`.model.ListellipseelevenRowModel
import com.example.myhome.modules.valoracionesycomentarios.`data`.viewmodel.ValoracionesYComentariosVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class ValoracionesYComentariosActivity :
    BaseActivity<ActivityValoracionesYComentariosBinding>(R.layout.activity_valoraciones_y_comentarios)
    {
  private val viewModel: ValoracionesYComentariosVM by viewModels<ValoracionesYComentariosVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val listellipseelevenAdapter =
    ListellipseelevenAdapter(viewModel.listellipseelevenList.value?:mutableListOf())
    binding.recyclerListellipseeleven.adapter = listellipseelevenAdapter
    listellipseelevenAdapter.setOnItemClickListener(
    object : ListellipseelevenAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : ListellipseelevenRowModel) {
        onClickRecyclerListellipseeleven(view, position, item)
      }
    }
    )
    viewModel.listellipseelevenList.observe(this) {
      listellipseelevenAdapter.updateData(it)
    }
    binding.valoracionesYComentariosVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.linearPerfil.setOnClickListener {
      val destIntent = PerfilInmobiliariaOneActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  fun onClickRecyclerListellipseeleven(
    view: View,
    position: Int,
    item: ListellipseelevenRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "VALORACIONES_Y_COMENTARIOS_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, ValoracionesYComentariosActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
