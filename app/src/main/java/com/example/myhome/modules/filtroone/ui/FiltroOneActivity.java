package com.example.myhome.modules.filtroone.ui

import android.view.View
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityFiltroOneBinding
import com.example.myhome.modules.filtro.ui.FiltroActivity
import com.example.myhome.modules.filtroone.`data`.model.FiltroOneRowModel
import com.example.myhome.modules.filtroone.`data`.viewmodel.FiltroOneVM
import com.example.myhome.modules.propiedadeslistausuario.ui.PropiedadesListaUsuarioActivity
import com.example.myhome.modules.propiedadeslistausuarioone.ui.PropiedadesListaUsuarioOneActivity
import kotlin.Int
import kotlin.String
import kotlin.Unit

class FiltroOneActivity : BaseActivity<ActivityFiltroOneBinding>(R.layout.activity_filtro_one) {
  private val viewModel: FiltroOneVM by viewModels<FiltroOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val filtroOneAdapter = FiltroOneAdapter(viewModel.filtroOneList.value?:mutableListOf())
    binding.recyclerFiltroOne.adapter = filtroOneAdapter
    filtroOneAdapter.setOnItemClickListener(
    object : FiltroOneAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : FiltroOneRowModel) {
        onClickRecyclerFiltroOne(view, position, item)
      }
    }
    )
    viewModel.filtroOneList.observe(this) {
      filtroOneAdapter.updateData(it)
    }
    binding.filtroOneVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.linearRowarrowleft.setOnClickListener {
      val destIntent = PropiedadesListaUsuarioOneActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.etLimpiarFiltros.setOnClickListener {
      val destIntent = FiltroActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
    binding.btnAplicarFiltrosOne.setOnClickListener {
      val destIntent = PropiedadesListaUsuarioActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  fun onClickRecyclerFiltroOne(
    view: View,
    position: Int,
    item: FiltroOneRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "FILTRO_ONE_ACTIVITY"

  }
}
