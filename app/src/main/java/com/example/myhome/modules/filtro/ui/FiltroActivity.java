package com.example.myhome.modules.filtro.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityFiltroBinding
import com.example.myhome.modules.filtro.`data`.model.FiltroRowModel
import com.example.myhome.modules.filtro.`data`.viewmodel.FiltroVM
import com.example.myhome.modules.propiedadeslistausuarioone.ui.PropiedadesListaUsuarioOneActivity
import kotlin.Int
import kotlin.String
import kotlin.Unit

class FiltroActivity : BaseActivity<ActivityFiltroBinding>(R.layout.activity_filtro) {
  private val viewModel: FiltroVM by viewModels<FiltroVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val filtroAdapter = FiltroAdapter(viewModel.filtroList.value?:mutableListOf())
    binding.recyclerFiltro.adapter = filtroAdapter
    filtroAdapter.setOnItemClickListener(
    object : FiltroAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : FiltroRowModel) {
        onClickRecyclerFiltro(view, position, item)
      }
    }
    )
    viewModel.filtroList.observe(this) {
      filtroAdapter.updateData(it)
    }
    binding.filtroVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.linearRowarrowleft.setOnClickListener {
      val destIntent = PropiedadesListaUsuarioOneActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  fun onClickRecyclerFiltro(
    view: View,
    position: Int,
    item: FiltroRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "FILTRO_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, FiltroActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
